/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.viewpager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BaseField;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Displays a {@link ViewPager} where each page shows a different day of the week.
 */
public class MainActivity extends FragmentActivity  {

    private TabLayout tabLayout;
    private Button buttonToPdf;
    /*private TextView def;*/
    /*private LinearLayout def;*/
    private static final int MAIN_LOADER = 47;
    private RelativeLayout def;
    private LinearLayout def2,def3;
    private SimpleFragmentPagerAdapter adapter;

    private LoaderManager.LoaderCallbacks<Bitmap> mLoaderToBitmap = new LoaderManager.LoaderCallbacks<Bitmap>() {
        @Override
        public Loader<Bitmap> onCreateLoader(int id, Bundle args) {
            return new AsyncTaskLoader<Bitmap>(MainActivity.this) {
                Document doc = new Document();
                Bitmap combinedBitmap;

                @Override
                protected void onStartLoading() {
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

                        def = (RelativeLayout) findViewById(R.id.relative1);
                        def2 = (LinearLayout) findViewById(R.id.linearYgeia);
                        def3 = (LinearLayout) findViewById(R.id.linearInfo);

                        def.getRootView();
                        def.setDrawingCacheEnabled(true);

                        def2.getRootView();
                        def2.setDrawingCacheEnabled(true);

                        def3.getRootView();
                        def3.setDrawingCacheEnabled(true);

                        Bitmap bitmap, bitmap2, bitmap3;

                        bitmap = Bitmap.createBitmap(def.getWidth(), def.getHeight(), Bitmap.Config.ARGB_8888);
                        bitmap2 = Bitmap.createBitmap(def2.getWidth(), def2.getHeight(), Bitmap.Config.ARGB_8888);
                        bitmap3 = Bitmap.createBitmap(def3.getWidth(), def3.getHeight(), Bitmap.Config.ARGB_8888);

                        Canvas c = new Canvas(bitmap);
                        c.drawColor(Color.LTGRAY);
                        def.draw(c);

                        Canvas c2 = new Canvas(bitmap2);
                        c2.drawColor(Color.LTGRAY);
                        def2.draw(c2);

                        Canvas c3 = new Canvas(bitmap3);
                        c3.drawColor(Color.LTGRAY);
                        def3.draw(c3);

                        ArrayList<Bitmap> a = new ArrayList<Bitmap>();
                        a.add(bitmap);
                        a.add(bitmap3);
                        a.add(bitmap2);
                        combinedBitmap = combineImageIntoOne(a);
                        Log.e("CombinedImage", "OK");

                        ByteArrayOutputStream stream = new ByteArrayOutputStream();

                        combinedBitmap.compress(Bitmap.CompressFormat.JPEG, 10, stream);
                        Log.e("Compress", "OK");
                        Image myImg = Image.getInstance(stream.toByteArray());
                        Log.e("StreamToByte", "OK");

                        doc.open();

                        if (myImg.getWidth() >= doc.getPageSize().getWidth() || myImg.getHeight() >= doc.getPageSize().getHeight()) {
                            myImg.scaleToFit(doc.getPageSize());
                        }
                        myImg.setAbsolutePosition((doc.getPageSize().getWidth() - myImg.getScaledWidth()) / BaseField.BORDER_WIDTH_MEDIUM, (doc.getPageSize().getHeight() - myImg.getScaledHeight()) / BaseField.BORDER_WIDTH_MEDIUM);

                        myImg.setAlignment(Image.ALIGN_CENTER);

                        // add image to document
                        doc.add(myImg);


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

            /*FileOutputStream fOut = null;
        try {
            String path = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + "/AEuZin";

            File dir = new File(path);
            if (!dir.exists())
                dir.mkdirs();

            File file = new File(dir, "2.png");
            fOut=new FileOutputStream(file, false);

            data.compress(Bitmap.CompressFormat.PNG, 10, fOut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                if ( fOut!= null) {
                    fOut.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/

            Toast.makeText(MainActivity.this, "Η Συνταγή αποθηκεύτηκε στη διαδρομή: /Εσωτερικός χώρος αποθήκευσης/EuZin", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onLoaderReset(Loader<Bitmap> loader) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        //below took me 2 days
        viewPager.setOffscreenPageLimit(2);
        buttonToPdf = (Button) findViewById(R.id.buttonToPdf);

        // Create an adapter that knows which fragment should be shown on each page
        adapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        buttonToPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(MainActivity.this,ServiceForPdf.class);
                startService(intent);*/
                /*everythingToPdf();*/
                /*adapter.instantiateItem(view,1);
                adapter.instantiateItem(view,0);
                adapter.instantiateItem(view,2);*/

                LoaderManager loaderManager = getSupportLoaderManager();
                Loader<String> githubSearchLoader = loaderManager.getLoader(MAIN_LOADER);
                if (githubSearchLoader == null) {
                    loaderManager.initLoader(MAIN_LOADER, null, mLoaderToBitmap);
                } else {
                    loaderManager.restartLoader(MAIN_LOADER, null, mLoaderToBitmap);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
        getSupportLoaderManager().destroyLoader(MAIN_LOADER);
    }

    public void toSecond(View view) {
        Intent intent = new Intent(this, FlexibleSpaceWithImageWithViewPagerTab2Activity.class);
        startActivity(intent);
    }

    public void toThird(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }


    public void everythingToPdf() {

        Document doc = new Document();

        try {

            String path = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + "/AEuZin";

            File dir = new File(path);
            if (!dir.exists())
                dir.mkdirs();

            File file = new File(dir, "EuZin.pdf");
            FileOutputStream fOut = new FileOutputStream(file, false);

            PdfWriter.getInstance(doc, fOut);

            // open the document
            doc.open();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();


            /*View def = view.getRootView();*/
            /*LayoutInflater mInflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
            RelativeLayout def = new RelativeLayout(this);
            mInflater.inflate(R.layout.activity_simetoxi, def, true);*/
            /*ScrollView def = (ScrollView)findViewById(R.id.scrolara);*/
            //////////////////////

            RelativeLayout def = (RelativeLayout) findViewById(R.id.relative1);
            RelativeLayout def2 = (RelativeLayout) findViewById(R.id.relative3);
            RelativeLayout def3 = (RelativeLayout) findViewById(R.id.relative5);
            /*TextView def=(TextView)findViewById(R.id.textToSee);*/
            def.getRootView();
            def.setDrawingCacheEnabled(true);

            def2.getRootView();
            def2.setDrawingCacheEnabled(true);

            def3.getRootView();
            def3.setDrawingCacheEnabled(true);

            Bitmap bitmap, bitmap2, bitmap3;

            bitmap = Bitmap.createBitmap(def.getWidth(), def.getHeight(), Bitmap.Config.ARGB_8888);
            bitmap2 = Bitmap.createBitmap(def2.getWidth(), def2.getHeight(), Bitmap.Config.ARGB_8888);
            bitmap3 = Bitmap.createBitmap(def3.getWidth(), def3.getHeight(), Bitmap.Config.ARGB_8888);
            /*def.layout(0, 0, def.getLayoutParams().width, def.getLayoutParams().height);*/


            /*View def = view.getRootView();
            def.setDrawingCacheEnabled(true);
            Bitmap bitmap = def.getDrawingCache();*/
            Canvas c = new Canvas(bitmap);
            c.drawColor(Color.LTGRAY);
            def.draw(c);

            Canvas c2 = new Canvas(bitmap2);
            c2.drawColor(Color.LTGRAY);
            def2.draw(c2);

            Canvas c3 = new Canvas(bitmap3);
            c3.drawColor(Color.LTGRAY);
            def3.draw(c3);

            //////////////////


            /*LayoutInflater mInflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
            RelativeLayout relig = new RelativeLayout(this);
            mInflater.inflate(R.layout.activity_simetoxi, relig, true);
            relig.setLayoutParams(new ViewGroup.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
            relig.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            relig.layout(0, 0, relig.getMeasuredWidth(), relig.getMeasuredHeight());

            Bitmap bitmap = Bitmap.createBitmap(relig.getMeasuredWidth(),relig.getMeasuredHeight(),Bitmap.Config.ARGB_8888);*/

            /*rootView.draw(c);*/





            /*Bitmap bitmap = BitmapFactory.decodeResource(getBaseContext()
                    .getResources(), R.drawable.piil10);*/

            ArrayList<Bitmap> a = new ArrayList<Bitmap>();
            a.add(bitmap);
            a.add(bitmap2);
            a.add(bitmap3);
            Bitmap combinedBitmap = combineImageIntoOne(a);

            combinedBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            Image myImg = Image.getInstance(stream.toByteArray());
                    /*myImg.scalePercent(30,13);

            myImg.setBackgroundColor(Color.parseColor(""));*/
            if (myImg.getWidth() >= doc.getPageSize().getWidth() || myImg.getHeight() >= doc.getPageSize().getHeight()) {
                myImg.scaleToFit(doc.getPageSize());
            }
            myImg.setAbsolutePosition((doc.getPageSize().getWidth() - myImg.getScaledWidth()) / BaseField.BORDER_WIDTH_MEDIUM, (doc.getPageSize().getHeight() - myImg.getScaledHeight()) / BaseField.BORDER_WIDTH_MEDIUM);

            /*myImg.setBackgroundColor(Color.parseColor(""));*/


            myImg.setAlignment(Image.ALIGN_CENTER);

            // add image to document
            doc.add(myImg);


            InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);


            /////////////////
            /*ImageView pdfvie = (ImageView)findViewById(R.id.pdfvie);
            if(pdfvie==null){
                pdfvie.setImageBitmap(bitmap);

            }else if(pdfvie!=null){
                pdfvie.setImageDrawable(null);
                pdfvie.setImageBitmap(bitmap);

            }*/
            Toast.makeText(getApplicationContext(), "Η Συνταγή αποθηκεύτηκε στη διαδρομή: /Εσωτερικός χώρος αποθήκευσης/EuZin", Toast.LENGTH_LONG).show();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //Important or else .pdf is corrupted
            doc.close();
        }

    }

    private Bitmap combineImageIntoOne(ArrayList<Bitmap> bitmap) {
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
            Log.d("HTML", "Combine: " + i + "/" + bitmap.size() + 1);

            top = (i == 0 ? 0 : top + bitmap.get(i).getHeight());
            canvas.drawBitmap(bitmap.get(i), 0f, top, null);
        }
        return temp;
    }

}
