package com.example.android.viewpager;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BaseField;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity {

    private LinearLayout def;
    private RatingBar mRatingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_erotiseis_genikes);
        def = (LinearLayout)findViewById(R.id.activity_second_linear);
        /*mRatingBar = (RatingBar)findViewById(R.id.ratingBar);
        mRatingBar.setRating(3);*/
    }

    public void secondToPdfidio(View view){
        everythingToPdf();
    }

    private void everythingToPdf() {
        Document doc = new Document();
        try {

            String path = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + "/AEuZin";

            File dir = new File(path);
            if (!dir.exists())
                dir.mkdirs();

            File file = new File(dir, "EuZin.pdf");
            FileOutputStream fOut = new FileOutputStream(file,false);


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

            /*RelativeLayout def = (RelativeLayout) findViewById(R.id.relative1);*/
            /*TextView def=(TextView)findViewById(R.id.textToSee);*/
            def.getRootView();
            def.setDrawingCacheEnabled(true);
            Bitmap bitmap;
            bitmap = Bitmap.createBitmap(def.getWidth(), def.getHeight(), Bitmap.Config.ARGB_8888);
            /*def.layout(0, 0, def.getLayoutParams().width, def.getLayoutParams().height);*/


            /*View def = view.getRootView();
            def.setDrawingCacheEnabled(true);
            Bitmap bitmap = def.getDrawingCache();*/
            Canvas c = new Canvas(bitmap);
            c.drawColor(Color.LTGRAY);
            def.draw(c);

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
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
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
        }finally {
            //Important or else .pdf is corrupted
            doc.close();
        }

    }
}
