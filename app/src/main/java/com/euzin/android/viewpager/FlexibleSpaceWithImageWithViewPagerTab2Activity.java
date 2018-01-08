/*
 * Copyright 2014 Soichiro Kashima
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.euzin.android.viewpager;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.OverScroller;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ksoichiro.android.observablescrollview.CacheFragmentStatePagerAdapter;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.github.ksoichiro.android.observablescrollview.Scrollable;
import com.github.ksoichiro.android.observablescrollview.TouchInterceptionFrameLayout;
import com.google.samples.apps.iosched.ui.widget.SlidingTabLayout;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BaseField;
import com.itextpdf.text.pdf.PdfWriter;
import com.nineoldandroids.view.ViewHelper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * <p>This uses TouchInterceptionFrameLayout to move Fragments.</p>
 * <p>
 * <p>There is an unsolved problem: it doesn't scroll smoothly
 * when the flexible space is changing.<br>
 * If it's a big problem to you, please also check
 * FlexibleSpaceWithImageWithViewPagerTabActivity.</p>
 * <p>
 * <p>SlidingTabLayout and SlidingTabStrip are from google/iosched:<br>
 * https://github.com/google/iosched</p>
 */
public class FlexibleSpaceWithImageWithViewPagerTab2Activity extends AppCompatActivity implements ObservableScrollViewCallbacks {

    private static final float MAX_TEXT_SCALE_DELTA = 0.3f;
    private static final int INVALID_POINTER = -1;
    private static final String NUMBER_OF_RECEIVER = "pdfCreating";
    private static final String NUMBER_OF_RECEIVER_NEXT = "nextPage";
    private static final String NUMBER_OF_RECEIVER_NEXT_GENIKES = "nextPageGenikes";
    private static final String NUMBER_OF_RECEIVER_NEXT_GENIKES_BACK = "nextPageGenikesBack";
    private static final String NUMBER_OF_RECEIVER_MIDDLE_BACK = "middleBack";

    private BroadcastReceiver mBroadcastReceiver;
    private IntentFilter mFilter;
    private static final int MAIN_LOADER = 477;
    private LinearLayout def, def2, def3, def4;
    private EditText mEponimo, mOnoma;

    private View mImageView;
    private View mOverlayView;
    private TextView mTitleView;
    private TouchInterceptionFrameLayout mInterceptionLayout;
    private ViewPager mPager;
    private NavigationAdapter mPagerAdapter;
    private VelocityTracker mVelocityTracker;
    private OverScroller mScroller;
    private float mBaseTranslationY;
    private int mMaximumVelocity;
    private int mActivePointerId = INVALID_POINTER;
    private int mSlop;
    private int mFlexibleSpaceHeight;
    private int mTabHeight;
    private boolean mScrolled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flexiblespacewithimagewithviewpagertab2);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        ViewCompat.setElevation(findViewById(R.id.header), getResources().getDimension(R.dimen.toolbar_elevation));
        mPagerAdapter = new NavigationAdapter(getSupportFragmentManager());
        mPager = (ViewPager) findViewById(R.id.pager);

        //set below method so the pager remembers 2 previous or next pages because default is 1 and when you try
        //to make pdf it will crash if you have more than 2 pages
        mPager.setOffscreenPageLimit(2);

        mPager.setAdapter(mPagerAdapter);
        mImageView = findViewById(R.id.image);
        mOverlayView = findViewById(R.id.overlay);
        // Padding for ViewPager must be set outside the ViewPager itself
        // because with padding, EdgeEffect of ViewPager become strange.
        mFlexibleSpaceHeight = getResources().getDimensionPixelSize(R.dimen.flexible_space_image_height);
        mTabHeight = getResources().getDimensionPixelSize(R.dimen.tab_height);
        findViewById(R.id.pager_wrapper).setPadding(0, mFlexibleSpaceHeight, 0, 0);
        mTitleView = (TextView) findViewById(R.id.title);
        setTitle(null);

        SlidingTabLayout slidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        slidingTabLayout.setCustomTabView(R.layout.tab_indicator, android.R.id.text1);
        slidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.accentForSlide));
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setViewPager(mPager);
        ((FrameLayout.LayoutParams) slidingTabLayout.getLayoutParams()).topMargin = mFlexibleSpaceHeight - mTabHeight;

        ViewConfiguration vc = ViewConfiguration.get(this);
        mSlop = vc.getScaledTouchSlop();
        mMaximumVelocity = vc.getScaledMaximumFlingVelocity();
        mInterceptionLayout = (TouchInterceptionFrameLayout) findViewById(R.id.container);
        mInterceptionLayout.setScrollInterceptionListener(mInterceptionListener);
        mScroller = new OverScroller(getApplicationContext());
        ScrollUtils.addOnGlobalLayoutListener(mInterceptionLayout, new Runnable() {
            @Override
            public void run() {
                // Extra space is required to move mInterceptionLayout when it's scrolled.
                // It's better to adjust its height when it's laid out
                // than to adjust the height when scroll events (onMoveMotionEvent) occur
                // because it causes lagging.
                // See #87: https://github.com/ksoichiro/Android-ObservableScrollView/issues/87
                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mInterceptionLayout.getLayoutParams();
                lp.height = getScreenHeight() + mFlexibleSpaceHeight /*+mFlexibleSpaceHeight*85/100*/;
                mInterceptionLayout.requestLayout();

                updateFlexibleSpace();
            }
        });

        mBroadcastReceiver = new broadcastReceived();
        mFilter = new IntentFilter();
        mFilter.addAction(NUMBER_OF_RECEIVER);
        mFilter.addAction(NUMBER_OF_RECEIVER_NEXT);
        mFilter.addAction(NUMBER_OF_RECEIVER_NEXT_GENIKES);
        mFilter.addAction(NUMBER_OF_RECEIVER_NEXT_GENIKES_BACK);
        mFilter.addAction(NUMBER_OF_RECEIVER_MIDDLE_BACK);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mBroadcastReceiver);
        //destry loader to avoid make again pdf when app resumes
        getSupportLoaderManager().destroyLoader(MAIN_LOADER);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mBroadcastReceiver, mFilter);
    }

    protected int getScreenHeight() {
        return findViewById(android.R.id.content).getHeight();
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
    }

    @Override
    public void onDownMotionEvent() {
    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
    }

    private TouchInterceptionFrameLayout.TouchInterceptionListener mInterceptionListener = new TouchInterceptionFrameLayout.TouchInterceptionListener() {
        @Override
        public boolean shouldInterceptTouchEvent(MotionEvent ev, boolean moving, float diffX, float diffY) {
            if (!mScrolled && mSlop < Math.abs(diffX) && Math.abs(diffY) < Math.abs(diffX)) {
                // Horizontal scroll is maybe handled by ViewPager
                return false;
            }

            Scrollable scrollable = getCurrentScrollable();
            if (scrollable != null) {
                mScrolled = false;
                return false;
            }

            // If interceptionLayout can move, it should intercept.
            // And once it begins to move, horizontal scroll shouldn't work any longer.
            int flexibleSpace = mFlexibleSpaceHeight - mTabHeight;
            int translationY = (int) ViewHelper.getTranslationY(mInterceptionLayout);
            boolean scrollingUp = 0 < diffY;
            boolean scrollingDown = diffY < 0;
            if (scrollingUp) {
                if (translationY < 0) {
                    mScrolled = true;
                    return true;
                }
            } else if (scrollingDown) {
                if (-flexibleSpace < translationY) {
                    mScrolled = true;
                    return true;
                }
            }
            mScrolled = false;
            return false;
        }

        @Override
        public void onDownMotionEvent(MotionEvent ev) {
            mActivePointerId = ev.getPointerId(0);
            mScroller.forceFinished(true);
            if (mVelocityTracker == null) {
                mVelocityTracker = VelocityTracker.obtain();
            } else {
                mVelocityTracker.clear();
            }
            mBaseTranslationY = ViewHelper.getTranslationY(mInterceptionLayout);
            mVelocityTracker.addMovement(ev);
        }

        @Override
        public void onMoveMotionEvent(MotionEvent ev, float diffX, float diffY) {
            int flexibleSpace = mFlexibleSpaceHeight - mTabHeight;
            float translationY = ScrollUtils.getFloat(ViewHelper.getTranslationY(mInterceptionLayout) + diffY, -flexibleSpace, 0);
            MotionEvent e = MotionEvent.obtainNoHistory(ev);
            e.offsetLocation(0, translationY - mBaseTranslationY);
            mVelocityTracker.addMovement(e);
            updateFlexibleSpace(translationY);
        }

        @Override
        public void onUpOrCancelMotionEvent(MotionEvent ev) {
            mScrolled = false;
            mVelocityTracker.computeCurrentVelocity(1000, mMaximumVelocity);
            int velocityY = (int) mVelocityTracker.getYVelocity(mActivePointerId);
            mActivePointerId = INVALID_POINTER;
            mScroller.forceFinished(true);
            int baseTranslationY = (int) ViewHelper.getTranslationY(mInterceptionLayout);

            int minY = -(mFlexibleSpaceHeight - mTabHeight);
            int maxY = 0;
            mScroller.fling(0, baseTranslationY, 0, velocityY, 0, 0, minY, maxY);
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    updateLayout();
                }
            });
        }
    };

    private void updateLayout() {
        boolean needsUpdate = false;
        float translationY = 0;
        if (mScroller.computeScrollOffset()) {
            translationY = mScroller.getCurrY();
            int flexibleSpace = mFlexibleSpaceHeight - mTabHeight;
            if (-flexibleSpace <= translationY && translationY <= 0) {
                needsUpdate = true;
            } else if (translationY < -flexibleSpace) {
                translationY = -flexibleSpace;
                needsUpdate = true;
            } else if (0 < translationY) {
                translationY = 0;
                needsUpdate = true;
            }
        }

        if (needsUpdate) {
            updateFlexibleSpace(translationY);

            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    updateLayout();
                }
            });
        }
    }

    private Scrollable getCurrentScrollable() {
        Fragment fragment = getCurrentFragment();
        if (fragment == null) {
            return null;
        }
        View view = fragment.getView();
        if (view == null) {
            return null;
        }
        return (Scrollable) view.findViewById(R.id.scroll);
    }

    private void updateFlexibleSpace() {
        updateFlexibleSpace(ViewHelper.getTranslationY(mInterceptionLayout));
    }

    private void updateFlexibleSpace(float translationY) {
        ViewHelper.setTranslationY(mInterceptionLayout, translationY);
        int minOverlayTransitionY = getActionBarSize() - mOverlayView.getHeight();
        ViewHelper.setTranslationY(mImageView, ScrollUtils.getFloat(-translationY / 2, minOverlayTransitionY, 0));

        // Change alpha of overlay
        float flexibleRange = mFlexibleSpaceHeight - getActionBarSize();
        ViewHelper.setAlpha(mOverlayView, ScrollUtils.getFloat(-translationY / flexibleRange, 0, 1));

        // Scale title text
        float scale = 1 + ScrollUtils.getFloat((flexibleRange + translationY - mTabHeight) / flexibleRange, 0, MAX_TEXT_SCALE_DELTA);
        setPivotXToTitle();
        ViewHelper.setPivotY(mTitleView, 0);
        ViewHelper.setScaleX(mTitleView, scale);
        ViewHelper.setScaleY(mTitleView, scale);
    }

    protected int getActionBarSize() {
        TypedValue typedValue = new TypedValue();
        int[] textSizeAttr = new int[]{R.attr.actionBarSize};
        int indexOfAttrTextSize = 0;
        TypedArray a = obtainStyledAttributes(typedValue.data, textSizeAttr);
        int actionBarSize = a.getDimensionPixelSize(indexOfAttrTextSize, -1);
        a.recycle();
        return actionBarSize;
    }

    private Fragment getCurrentFragment() {
        return mPagerAdapter.getItemAt(mPager.getCurrentItem());
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setPivotXToTitle() {
        Configuration config = getResources().getConfiguration();
        if (Build.VERSION_CODES.JELLY_BEAN_MR1 <= Build.VERSION.SDK_INT
                && config.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
            ViewHelper.setPivotX(mTitleView, findViewById(android.R.id.content).getWidth());
        } else {
            ViewHelper.setPivotX(mTitleView, 0);
        }
    }

    /**
     * This adapter provides two types of fragments as an com.george.euzin.
     * {@linkplain #createItem(int)} should be modified if you use this com.george.euzin for your app.
     */

    private class NavigationAdapter extends CacheFragmentStatePagerAdapter {

        String genika = getResources().getString(R.string.genika);
        String health = getResources().getString(R.string.health);
        String info = getResources().getString(R.string.info);

        private final String[] TITLES = new String[]{genika, health, info};

        public NavigationAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        protected Fragment createItem(int position) {
            Fragment f;
            switch (position) {
                case 0:
                    f = new WednesdayFragment();
                    break;
                case 1:
                    f = new TuesdayFragment();
                    break;
                case 2:
                    f = new ThursdayFragment();
                    break;
                default:
                    f = new WednesdayFragment();
                    break;
            }
            return f;
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }
    }

    private class broadcastReceived extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String actionGet = intent.getAction();
            if (actionGet.equals(NUMBER_OF_RECEIVER)) {
                /*LoaderManager loaderManager = getSupportLoaderManager();
                Loader<String> githubSearchLoader = loaderManager.getLoader(MAIN_LOADER);
                if (githubSearchLoader == null) {
                    loaderManager.initLoader(MAIN_LOADER, null, mLoaderToCreatePdf);
                } else {
                    loaderManager.restartLoader(MAIN_LOADER, null, mLoaderToCreatePdf);
                }*/
                checkPermissionsForGranting();
                Log.e("Intent", "Received");
            } else if (actionGet.equals(NUMBER_OF_RECEIVER_NEXT)) {

                mPager.setCurrentItem(1);
                Log.e("IntentNextPage", "Received");
            } else if (actionGet.equals(NUMBER_OF_RECEIVER_NEXT_GENIKES)) {

                mPager.setCurrentItem(2);
                Log.e("IntentNextPage", "Received");
            } else if (actionGet.equals(NUMBER_OF_RECEIVER_NEXT_GENIKES_BACK)) {

                mPager.setCurrentItem(0);
                Log.e("IntentNextPage", "Received");
            } else if (actionGet.equals(NUMBER_OF_RECEIVER_MIDDLE_BACK)) {

                mPager.setCurrentItem(1);
                Log.e("IntentNextPage", "Received");
            }
        }
    }

    private void checkPermissionsForGranting() {
        int MyVersion = Build.VERSION.SDK_INT;
        if (MyVersion > Build.VERSION_CODES.JELLY_BEAN) {
            if (!checkIfAlreadyhavePermission()) {
                requestForSpecificPermission();
                Log.e("Lollipop", "No");

            }else{
                LoaderManager loaderManager = getSupportLoaderManager();
                Loader<String> githubSearchLoader = loaderManager.getLoader(MAIN_LOADER);
                if (githubSearchLoader == null) {
                    loaderManager.initLoader(MAIN_LOADER, null, mLoaderToCreatePdf);
                } else {
                    loaderManager.restartLoader(MAIN_LOADER, null, mLoaderToCreatePdf);
                }
                Log.e("Lollipop", "Yes");

            }
        }
    }

    //Check checkIfAlreadyhavePermission
    private boolean checkIfAlreadyhavePermission() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestForSpecificPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE}, 101);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 101:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //granted
                    LoaderManager loaderManager = getSupportLoaderManager();
                    Loader<String> githubSearchLoader = loaderManager.getLoader(MAIN_LOADER);
                    if (githubSearchLoader == null) {
                        loaderManager.initLoader(MAIN_LOADER, null, mLoaderToCreatePdf);
                    } else {
                        loaderManager.restartLoader(MAIN_LOADER, null, mLoaderToCreatePdf);
                    }
                } else {
                    //not granted
                    Toast.makeText(FlexibleSpaceWithImageWithViewPagerTab2Activity.this,getResources().getString(R.string.permissionsToast),Toast.LENGTH_LONG).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private LoaderManager.LoaderCallbacks mLoaderToCreatePdf = new LoaderManager.LoaderCallbacks<Bitmap>() {

        @Override
        public Loader<Bitmap> onCreateLoader(int id, Bundle args) {
            return new AsyncTaskLoader<Bitmap>(FlexibleSpaceWithImageWithViewPagerTab2Activity.this) {

                Document doc = new Document();
                Bitmap combinedBitmap;

                @Override
                protected void onStartLoading() {
                    Toast.makeText(FlexibleSpaceWithImageWithViewPagerTab2Activity.this, getResources().getString(R.string.toast_while_wait), Toast.LENGTH_LONG).show();

                    forceLoad();
                }

                @Override
                public Bitmap loadInBackground() {

                    try {

                        String path = Environment.getExternalStorageDirectory()
                                .getAbsolutePath() + "/EuZin";

                        File dir = new File(path);
                        if (!dir.exists())
                            dir.mkdirs();

                        File file = new File(dir, "EuZin.pdf");
                        FileOutputStream fOut = new FileOutputStream(file, false);

                        PdfWriter.getInstance(doc, fOut);

                        def = (LinearLayout) findViewById(R.id.linearYgeia1);
                        def4 = (LinearLayout) findViewById(R.id.linearYgeia2);
                        def2 = (LinearLayout) findViewById(R.id.linearYgeia);
                        def3 = (LinearLayout) findViewById(R.id.linearProsopikes);

                        def.getRootView();
                        def.setDrawingCacheEnabled(true);

                        def2.getRootView();
                        def2.setDrawingCacheEnabled(true);

                        def3.getRootView();
                        def3.setDrawingCacheEnabled(true);

                        def4.getRootView();
                        def4.setDrawingCacheEnabled(true);

                        Bitmap bitmap4, bitmap, bitmap2, bitmap3;

                        bitmap = Bitmap.createBitmap(def.getWidth(), def.getHeight(), Bitmap.Config.ARGB_8888);
                        bitmap2 = Bitmap.createBitmap(def2.getWidth(), def2.getHeight(), Bitmap.Config.ARGB_8888);
                        bitmap3 = Bitmap.createBitmap(def3.getWidth(), def3.getHeight(), Bitmap.Config.ARGB_8888);
                        bitmap4 = Bitmap.createBitmap(def4.getWidth(), def4.getHeight(), Bitmap.Config.ARGB_8888);

                        Canvas c = new Canvas(bitmap);
                        c.drawARGB(0, 225, 225, 255);
                        /*c.drawColor(Color.LTGRAY);*/
                        def.draw(c);

                        Canvas c2 = new Canvas(bitmap2);
                        c2.drawColor(Color.WHITE);
                        def2.draw(c2);

                        Canvas c3 = new Canvas(bitmap3);
                        c3.drawColor(Color.LTGRAY);
                        def3.draw(c3);

                        Canvas c4 = new Canvas(bitmap4);
                        c4.drawColor(Color.LTGRAY);
                        def4.draw(c4);

                        ArrayList<Bitmap> a = new ArrayList<Bitmap>();
                        a.add(bitmap);
                        a.add(bitmap4);
                        a.add(bitmap2);
                        a.add(bitmap3);
                        combinedBitmap = combineImageIntoOneFlexWidth(a);
                        Log.d("CombinedImage", "OK");

                        ByteArrayOutputStream stream = new ByteArrayOutputStream();

                        combinedBitmap.compress(Bitmap.CompressFormat.JPEG, 10, stream);
                        Log.d("Compress", "OK");
                        Image myImg = Image.getInstance(stream.toByteArray());
                        Log.d("StreamToByte", "OK");

                        doc.open();

                        if (myImg.getWidth() >= doc.getPageSize().getWidth() || myImg.getHeight() >= doc.getPageSize().getHeight()) {
                            myImg.scaleToFit(doc.getPageSize());
                        }
                        myImg.setAbsolutePosition((doc.getPageSize().getWidth() - myImg.getScaledWidth()) / BaseField.BORDER_WIDTH_MEDIUM, (doc.getPageSize().getHeight() - myImg.getScaledHeight()) / BaseField.BORDER_WIDTH_MEDIUM);

                        myImg.setAlignment(Image.ALIGN_CENTER);

                        // add image to document
                        doc.add(myImg);

                        ////new page
                        /*Image myImage2 = Image.getInstance(stream.toByteArray());
                        doc.newPage();

                        if (myImage2.getWidth() >= doc.getPageSize().getWidth() || myImage2.getHeight() >= doc.getPageSize().getHeight()) {
                            myImage2.scaleToFit(doc.getPageSize());
                        }
                        myImage2.setAbsolutePosition((doc.getPageSize().getWidth() - myImage2.getScaledWidth()) / BaseField.BORDER_WIDTH_MEDIUM, (doc.getPageSize().getHeight() - myImage2.getScaledHeight()) / BaseField.BORDER_WIDTH_MEDIUM);

                        myImage2.setAlignment(Image.ALIGN_CENTER);

                        doc.add(myImage2);*/

                        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

                        doc.close();
                        return combinedBitmap;

                    } catch (DocumentException e) {
                        e.printStackTrace();
                        return null;
                    } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    } finally {

                    }
                }
            };
        }

        @Override
        public void onLoadFinished(Loader<Bitmap> loader, Bitmap data) {
            Toast.makeText(FlexibleSpaceWithImageWithViewPagerTab2Activity.this, getResources().getString(R.string.toast_end_pdf), Toast.LENGTH_LONG).show();

            InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

            mEponimo = (EditText) findViewById(R.id.stoixeiaEponimo);
            mOnoma = (EditText) findViewById(R.id.stoixeiaOnoma);
            String eponimoString = mEponimo.getText().toString();
            String onomaString = mOnoma.getText().toString();

            Intent email = new Intent(Intent.ACTION_SEND);
            email.setData(Uri.parse("mailto:"));
            email.putExtra(Intent.EXTRA_EMAIL, new String[]{"ef-zin@hotmail.com"});
            email.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.emailSubject));
            email.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.hello) + " " + onomaString + " " + eponimoString + " " + getResources().getString(R.string.apostelo) + " " +
                    getResources().getString(R.string.parakalo));
            /*Uri uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + "/EuZin", "EuZin.pdf"));*/
            Uri uri = FileProvider.getUriForFile(FlexibleSpaceWithImageWithViewPagerTab2Activity.this, getApplicationContext().getPackageName() + ".my.package.name.provider",
                    new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/EuZin", "EuZin.pdf"));
            email.putExtra(Intent.EXTRA_STREAM, uri);
            email.setType("application/pdf");
            email.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            email.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (email.resolveActivity(getPackageManager()) != null) {
                startActivity(email);
            }
        }

        @Override
        public void onLoaderReset(Loader<Bitmap> loader) {

        }
    };

    //method to put layouts in pdf with height order,one on top of other
    private Bitmap combineImageIntoOneFlex(ArrayList<Bitmap> bitmap) {
        int w = 0, h = 0;
        for (int i = 0; i < bitmap.size(); i++) {
            if (i < bitmap.size() - 1) {
                w = bitmap.get(i).getWidth() > bitmap.get(i + 1).getWidth() ? bitmap.get(i).getWidth() : bitmap.get(i + 1).getWidth();
            }
            h += bitmap.get(i).getHeight();
        }

        Bitmap temp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(temp);
        int top = 0;
        for (int i = 0; i < bitmap.size(); i++) {
            Log.e("HTML", "Combine: " + i + "/" + bitmap.size() + 1);

            top = (i == 0 ? 0 : top + bitmap.get(i).getHeight());
            canvas.drawBitmap(bitmap.get(i), 0f, top, null);
        }
        return temp;
    }

    //method to put layouts side by side
    private Bitmap combineImageIntoOneFlexWidth(ArrayList<Bitmap> bitmap) {
        int w = 0, h = 0;
        for (int i = 0; i < bitmap.size(); i++) {
            /*if (i < bitmap.size() - 1) {
                h = bitmap.get(i).getHeight() > bitmap.get(i + 1).getHeight() ? bitmap.get(i).getHeight() : bitmap.get(i + 1).getHeight();
            }*/
            //layout with number 1 is the longest
            h = bitmap.get(1).getHeight();
            w += bitmap.get(i).getWidth();
        }

        Bitmap temp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(temp);
        int top = 0;
        for (int i = 0; i < bitmap.size(); i++) {
            Log.e("HTML", "Combine: " + i + "/" + bitmap.size() + 1);

            top = (i == 0 ? 0 : top + bitmap.get(i).getWidth());
            //attributes 1:bitmap,2:width that starts drawing,3:height that starts drawing
            canvas.drawBitmap(bitmap.get(i), top, 0f, null);
        }
        return temp;
    }
}
