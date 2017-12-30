package com.example.android.viewpager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;



import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;




public class simetoxi2 extends Activity {


    int n11;


    class ItemAutoTextAdapter2 extends CursorAdapter
            implements AdapterView.OnItemClickListener {

        private AutoCompleteDbAdapter4 mDbHelper;


        public ItemAutoTextAdapter2(AutoCompleteDbAdapter4 dbHelper) {
            // Call the CursorAdapter constructor with a null Cursor.
            super(simetoxi2.this, null);
            mDbHelper = dbHelper;

        }

        @Override
        public Cursor runQueryOnBackgroundThread(CharSequence constraint) {
            if (getFilterQueryProvider() != null) {
                return getFilterQueryProvider().runQuery(constraint);
            }

            Cursor cursor = mDbHelper.getMatchingStates(
                    (constraint != null ? constraint.toString() : null));

            return cursor;
        }

        @Override
        public String convertToString(Cursor cursor) {
            final int columnIndex = cursor.getColumnIndexOrThrow("description");
            final String str = cursor.getString(columnIndex);
            return str;

        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            final String text = convertToString(cursor);
            ((TextView) view.findViewById(R.id.customiti)).setText(text);
            ((TextView) view.findViewById(R.id.customiti)).setTextColor(Color.BLACK);
            ((TextView) view.findViewById(R.id.customiti)).setBackgroundColor(Color.LTGRAY);
        }


        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            final LayoutInflater inflater = LayoutInflater.from(context);
            final View view =
                    inflater.inflate(R.layout.custom_item,
                            parent, false);

            return view;
        }

        @Override
        public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
            Cursor cursor = (Cursor) listView.getItemAtPosition(position);

            final String capital = cursor.getString(cursor.getColumnIndexOrThrow("price"));
            final String proto = cursor.getString(cursor.getColumnIndexOrThrow("proto"));
            final String dio = cursor.getString(cursor.getColumnIndexOrThrow("dio"));
            final String capital1 = cursor.getString(cursor.getColumnIndexOrThrow("anafora"));

            TextView lianikitimi2 = (TextView)findViewById(R.id.lianikitimi2);
            lianikitimi2.setText(String.valueOf(capital) + " €");
            TextView anaforatimi2 = (TextView)findViewById(R.id.anaforatimi2);
            anaforatimi2.setText(String.valueOf(capital1) + " €");

            TextView posotita2 = (TextView)findViewById(R.id.posotita2);
            Spinner spinner3 = (Spinner)findViewById(R.id.spinner3);
            Spinner spinner4 = (Spinner)findViewById(R.id.spinner4);
            TextView simetoxi2 = (TextView)findViewById(R.id.simetoxi2);
            TextView euro2 = (TextView)findViewById(R.id.euro2);

            InputMethodManager in = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            in.hideSoftInputFromWindow(view.getApplicationWindowToken(),0);




            if (proto.equals("2") && dio.equals("1")) {
                /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(1)",Toast.LENGTH_LONG).show();*/
                ArrayAdapter adapter2 = ArrayAdapter.createFromResource(simetoxi2.this, R.array.deutera,
                        android.R.layout.simple_spinner_dropdown_item);
                ArrayAdapter adapter3 = ArrayAdapter.createFromResource(simetoxi2.this, R.array.trita,
                        android.R.layout.simple_spinner_dropdown_item);
                spinner3.setAdapter(adapter2);
                spinner4.setAdapter(adapter3);
                spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        TextView mytexto = (TextView) view;
                        String n10= mytexto.getText().toString();
                        n11= Integer.parseInt(n10);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                        TextView mytext = (TextView) view;
                        final String n5 = mytext.getText().toString();




                        double n1, n2, n3, n6, n7, n8, n9,n21,n22,n23,n24;
                        n1 = Double.parseDouble(capital);
                        n2 = Double.parseDouble(capital1);
                        n6 = Double.parseDouble(n5);
                        if (n1 >= n2) {
                            if (n1 - n2 <= 20){
                                n3 = ((n1 - n2) + n2 * n6 / 100) * n11;
                                n3 = Math.round(n3*100);
                                n3 = n3/100;
                                String n4 = Double.toString(n3);
                                TextView euro2 = (TextView) findViewById(R.id.euro2);
                                euro2.setText(n4 + " €");
                                sinleft2=n4;
                            /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(1)---n1>n2",Toast.LENGTH_LONG).show();*/

                                n21=Double.parseDouble(sinleft);
                                n22=Double.parseDouble(sinleft2);
                                n23=Double.parseDouble(sinleft3);
                                n24=n21+n22+n23+1;
                                n24=Math.round(n24 * 100);
                                n24=n24/100;
                                String n25=Double.toString(n24);


                                TextView sinololefta = (TextView)findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");
                            }else{
                                n3 = (20 + n2 * n6 / 100) * n11;
                                n3 = Math.round(n3*100);
                                n3 = n3/100;
                                String n4 = Double.toString(n3);
                                TextView euro2 = (TextView) findViewById(R.id.euro2);
                                euro2.setText(n4 + " €");
                                sinleft2=n4;
                            /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(1)---n1>n2",Toast.LENGTH_LONG).show();*/

                                n21=Double.parseDouble(sinleft);
                                n22=Double.parseDouble(sinleft2);
                                n23=Double.parseDouble(sinleft3);
                                n24=n21+n22+n23+1;
                                n24=Math.round(n24 * 100);
                                n24=n24/100;
                                String n25=Double.toString(n24);


                                TextView sinololefta = (TextView)findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");
                            }


                        }else{

                            n7=n1*n6/100;
                            n8=n2-n1;
                            n9=n7/2;
                            if (n9<=n8){
                                n3 = (n7-n9) * n11;
                                n3 = Math.round(n3*100);
                                n3 = n3/100;
                                String n4 = Double.toString(n3);
                                TextView euro2 = (TextView) findViewById(R.id.euro2);
                                euro2.setText(n4 + " €");
                                sinleft2=n4;
                                /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(1)---n1<n2---n9<n8",Toast.LENGTH_LONG).show();*/

                                n21=Double.parseDouble(sinleft);
                                n22=Double.parseDouble(sinleft2);
                                n23=Double.parseDouble(sinleft3);
                                n24=n21+n22+n23+1;
                                n24=Math.round(n24 * 100);
                                n24=n24/100;
                                String n25=Double.toString(n24);


                                TextView sinololefta = (TextView)findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");

                            }
                            else{
                                n3 = (n7-n8)* n11;
                                n3 = Math.round(n3*100);
                                n3 = n3/100;
                                String n4 = Double.toString(n3);
                                TextView euro2 = (TextView) findViewById(R.id.euro2);
                                euro2.setText(n4 + " €");
                                sinleft2=n4;
                                /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(1)---n1<n2---n9>n8",Toast.LENGTH_LONG).show();*/

                                n21=Double.parseDouble(sinleft);
                                n22=Double.parseDouble(sinleft2);
                                n23=Double.parseDouble(sinleft3);
                                n24=n21+n22+n23+1;
                                n24=Math.round(n24 * 100);
                                n24=n24/100;
                                String n25=Double.toString(n24);


                                TextView sinololefta = (TextView)findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");

                            }

                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

            }else if (proto.equals("2") && dio.equals("2")){
                /*Toast.makeText(getApplicationContext(), "proto.equals(2) && dio.equals(2)", Toast.LENGTH_LONG).show();*/
                ArrayAdapter adapter2 = ArrayAdapter.createFromResource(simetoxi2.this, R.array.deutera,
                        android.R.layout.simple_spinner_dropdown_item);
                ArrayAdapter adapter3 = ArrayAdapter.createFromResource(simetoxi2.this, R.array.trita,
                        android.R.layout.simple_spinner_dropdown_item);
                spinner3.setAdapter(adapter2);
                spinner4.setAdapter(adapter3);
                spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        TextView mytexto = (TextView) view;
                        String n10 = mytexto.getText().toString();
                        n11 = Integer.parseInt(n10);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                        TextView mytext = (TextView) view;
                        final String n5 = mytext.getText().toString();


                        double n1, n2, n3, n6, n7, n8, n9,n21,n22,n23,n24;
                        n1 = Double.parseDouble(capital);
                        n2 = Double.parseDouble(capital1);
                        n6 = Double.parseDouble(n5);
                        if (n1 >= n2) {
                            if((n1 - n2) / 2 <= 20){
                                n3 = ((n1 - n2) / 2 + n2 * n6 / 100) * n11;
                                n3 = Math.round(n3*100);
                                n3 = n3/100;
                                String n4 = Double.toString(n3);
                                TextView euro2 = (TextView) findViewById(R.id.euro2);
                                euro2.setText(n4 + " €");
                                sinleft2=n4;
                            /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(2)---n1>n2",Toast.LENGTH_LONG).show();*/

                                n21=Double.parseDouble(sinleft);
                                n22=Double.parseDouble(sinleft2);
                                n23=Double.parseDouble(sinleft3);
                                n24=n21+n22+n23+1;
                                n24=Math.round(n24 * 100);
                                n24=n24/100;
                                String n25=Double.toString(n24);


                                TextView sinololefta = (TextView)findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");
                            }else{
                                n3 = (20 + n2 * n6 / 100) * n11;
                                n3 = Math.round(n3*100);
                                n3 = n3/100;
                                String n4 = Double.toString(n3);
                                TextView euro2 = (TextView) findViewById(R.id.euro2);
                                euro2.setText(n4 + " €");
                                sinleft2=n4;
                            /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(2)---n1>n2",Toast.LENGTH_LONG).show();*/

                                n21=Double.parseDouble(sinleft);
                                n22=Double.parseDouble(sinleft2);
                                n23=Double.parseDouble(sinleft3);
                                n24=n21+n22+n23+1;
                                n24=Math.round(n24 * 100);
                                n24=n24/100;
                                String n25=Double.toString(n24);


                                TextView sinololefta = (TextView)findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");
                            }


                        }else{

                            n7=n1*n6/100;
                            n8=n2-n1;
                            n9=n7/2;
                            if (n9 <= n8){
                                n3 = (n7-n9) * n11;
                                n3 = Math.round(n3*100);
                                n3 = n3/100;
                                String n4 = Double.toString(n3);
                                TextView euro2 = (TextView) findViewById(R.id.euro2);
                                euro2.setText(n4 + " €");
                                sinleft2=n4;
                                /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(2)---n1<n2---n9<n8",Toast.LENGTH_LONG).show();*/

                                n21=Double.parseDouble(sinleft);
                                n22=Double.parseDouble(sinleft2);
                                n23=Double.parseDouble(sinleft3);
                                n24=n21+n22+n23+1;
                                n24=Math.round(n24 * 100);
                                n24=n24/100;
                                String n25=Double.toString(n24);


                                TextView sinololefta = (TextView)findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");

                            }
                            else{
                                n3 = (n7-n8) * n11;
                                n3 = Math.round(n3*100);
                                n3 = n3/100;
                                String n4 = Double.toString(n3);
                                TextView euro2 = (TextView) findViewById(R.id.euro2);
                                euro2.setText(n4 + " €");
                                sinleft2=n4;
                                /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(2)---n1<n2---n9>n8",Toast.LENGTH_LONG).show();*/

                                n21=Double.parseDouble(sinleft);
                                n22=Double.parseDouble(sinleft2);
                                n23=Double.parseDouble(sinleft3);
                                n24=n21+n22+n23+1;
                                n24=Math.round(n24 * 100);
                                n24=n24/100;
                                String n25=Double.toString(n24);


                                TextView sinololefta = (TextView)findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");

                            }
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }else if (proto.equals("1") && dio.equals("1")){
                /*Toast.makeText(getApplicationContext(), "proto.equals(1) && dio.equals(1)", Toast.LENGTH_LONG).show();*/
                ArrayAdapter adapter2 = ArrayAdapter.createFromResource(simetoxi2.this, R.array.deutera,
                        android.R.layout.simple_spinner_dropdown_item);
                ArrayAdapter adapter3 = ArrayAdapter.createFromResource(simetoxi2.this, R.array.trita,
                        android.R.layout.simple_spinner_dropdown_item);
                spinner3.setAdapter(adapter2);
                spinner4.setAdapter(adapter3);
                spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        TextView mytexto = (TextView) view;
                        String n10 = mytexto.getText().toString();
                        n11 = Integer.parseInt(n10);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                        TextView mytext = (TextView) view;
                        final String n5 = mytext.getText().toString();


                        double n1, n2, n3, n6, n7, n8, n9,n21,n22,n23,n24;
                        n1 = Double.parseDouble(capital);
                        n2 = Double.parseDouble(capital1);
                        n6 = Double.parseDouble(n5);
                        if (n1 >= n2) {
                            if(n1 - n2 <=20){
                                n3 = ((n1 - n2) + n2 * n6 / 100) * n11;
                                n3 = Math.round(n3*100);
                                n3 = n3/100;
                                String n4 = Double.toString(n3);
                                TextView euro2 = (TextView) findViewById(R.id.euro2);
                                euro2.setText(n4 + " €");
                                sinleft2=n4;
                            /*Toast.makeText(getApplicationContext(),"proto.equals(1) && dio.equals(1)---n1>n2",Toast.LENGTH_LONG).show();*/

                                n21=Double.parseDouble(sinleft);
                                n22=Double.parseDouble(sinleft2);
                                n23=Double.parseDouble(sinleft3);
                                n24=n21+n22+n23+1;
                                n24=Math.round(n24 * 100);
                                n24=n24/100;
                                String n25=Double.toString(n24);


                                TextView sinololefta = (TextView)findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");

                            }else{
                                n3 = (20 + n2 * n6 / 100) * n11;
                                n3 = Math.round(n3*100);
                                n3 = n3/100;
                                String n4 = Double.toString(n3);
                                TextView euro2 = (TextView) findViewById(R.id.euro2);
                                euro2.setText(n4 + " €");
                                sinleft2=n4;
                            /*Toast.makeText(getApplicationContext(),"proto.equals(1) && dio.equals(1)---n1>n2",Toast.LENGTH_LONG).show();*/

                                n21=Double.parseDouble(sinleft);
                                n22=Double.parseDouble(sinleft2);
                                n23=Double.parseDouble(sinleft3);
                                n24=n21+n22+n23+1;
                                n24=Math.round(n24 * 100);
                                n24=n24/100;
                                String n25=Double.toString(n24);


                                TextView sinololefta = (TextView)findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");

                            }

                        }else{

                            n7=n1*n6/100;
                            n8=n2-n1;
                            n9=n7/2;
                            if (n9 <= n8){
                                n3 = (n7-n9) * n11;
                                n3 = Math.round(n3*100);
                                n3 = n3/100;
                                String n4 = Double.toString(n3);
                                TextView euro2 = (TextView) findViewById(R.id.euro2);
                                euro2.setText(n4 + " €");
                                sinleft2=n4;
                                /*Toast.makeText(getApplicationContext(),"proto.equals(1) && dio.equals(1)---n1<n2---n9<n8",Toast.LENGTH_LONG).show();*/

                                n21=Double.parseDouble(sinleft);
                                n22=Double.parseDouble(sinleft2);
                                n23=Double.parseDouble(sinleft3);
                                n24=n21+n22+n23+1;
                                n24=Math.round(n24 * 100);
                                n24=n24/100;
                                String n25=Double.toString(n24);


                                TextView sinololefta = (TextView)findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");

                            }
                            else{
                                n3 = (n7-n8) * n11;
                                n3 = Math.round(n3*100);
                                n3 = n3/100;
                                String n4 = Double.toString(n3);
                                TextView euro2 = (TextView) findViewById(R.id.euro2);
                                euro2.setText(n4 + " €");
                                sinleft2=n4;
                                /*Toast.makeText(getApplicationContext(),"proto.equals(1) && dio.equals(1)---n1<n2---n9>n8",Toast.LENGTH_LONG).show();*/

                                n21=Double.parseDouble(sinleft);
                                n22=Double.parseDouble(sinleft2);
                                n23=Double.parseDouble(sinleft3);
                                n24=n21+n22+n23+1;
                                n24=Math.round(n24 * 100);
                                n24=n24/100;
                                String n25=Double.toString(n24);


                                TextView sinololefta = (TextView)findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");

                            }



                        }



                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }



        }
    }

    class ItemAutoTextAdapter3 extends CursorAdapter
            implements AdapterView.OnItemClickListener {

        private AutoCompleteDbAdapter4 mDbHelper;


        public ItemAutoTextAdapter3 (AutoCompleteDbAdapter4 dbHelper) {
            // Call the CursorAdapter constructor with a null Cursor.
            super(simetoxi2.this, null);
            mDbHelper = dbHelper;

        }




        @Override
        public Cursor runQueryOnBackgroundThread(CharSequence constraint) {
            if (getFilterQueryProvider() != null) {
                return getFilterQueryProvider().runQuery(constraint);
            }

            Cursor cursor = mDbHelper.getMatchingStates(
                    (constraint != null ? constraint.toString() : null));

            return cursor;
        }



        @Override
        public String convertToString(Cursor cursor) {
            final int columnIndex = cursor.getColumnIndexOrThrow("description");
            final String str = cursor.getString(columnIndex);
            return str;

        }




        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            final String text = convertToString(cursor);
            ((TextView) view.findViewById(R.id.customiti)).setText(text);
            ((TextView) view.findViewById(R.id.customiti)).setTextColor(Color.BLACK);
            ((TextView) view.findViewById(R.id.customiti)).setBackgroundColor(Color.LTGRAY);
        }


        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            final LayoutInflater inflater = LayoutInflater.from(context);
            final View view =
                    inflater.inflate(R.layout.custom_item,
                            parent, false);

            return view;
        }


        @Override
        public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
            // Get the cursor, positioned to the corresponding row in the result set
            Cursor cursor = (Cursor) listView.getItemAtPosition(position);

            // Get the state's capital from this row in the database.
            final String capital = cursor.getString(cursor.getColumnIndexOrThrow("price"));
            final String proto = cursor.getString(cursor.getColumnIndexOrThrow("proto"));
            final String dio = cursor.getString(cursor.getColumnIndexOrThrow("dio"));
            final String capital1 = cursor.getString(cursor.getColumnIndexOrThrow("anafora"));


            TextView lianikitimi3 = (TextView)findViewById(R.id.lianikitimi3);
            lianikitimi3.setText(String.valueOf(capital) + " €");
            TextView anaforatimi3 = (TextView)findViewById(R.id.anaforatimi3);
            anaforatimi3.setText(String.valueOf(capital1) + " €");

            TextView posotita3 = (TextView)findViewById(R.id.posotita3);
            Spinner spinner5 = (Spinner)findViewById(R.id.spinner5);
            Spinner spinner6 = (Spinner)findViewById(R.id.spinner6);
            TextView simetoxi2 = (TextView)findViewById(R.id.simetoxi2);
            TextView euro3 = (TextView)findViewById(R.id.euro3);

            InputMethodManager in = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            in.hideSoftInputFromWindow(view.getApplicationWindowToken(),0);




            if (proto.equals("2") && dio.equals("1")) {
                /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(1)",Toast.LENGTH_LONG).show();*/
                ArrayAdapter adapter2 = ArrayAdapter.createFromResource(simetoxi2.this, R.array.deutera,
                        android.R.layout.simple_spinner_dropdown_item);
                ArrayAdapter adapter3 = ArrayAdapter.createFromResource(simetoxi2.this, R.array.trita,
                        android.R.layout.simple_spinner_dropdown_item);
                spinner5.setAdapter(adapter2);
                spinner6.setAdapter(adapter3);
                spinner6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        TextView mytexto = (TextView) view;
                        String n10= mytexto.getText().toString();
                        n11= Integer.parseInt(n10);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                spinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                        TextView mytext = (TextView) view;
                        final String n5 = mytext.getText().toString();


                        double n1, n2, n3, n6, n7, n8, n9,n21,n22,n23,n24;
                        n1 = Double.parseDouble(capital);
                        n2 = Double.parseDouble(capital1);
                        n6 = Double.parseDouble(n5);
                        if (n1 >= n2) {
                            if(n1 - n2 <= 20){
                                n3 = ((n1 - n2) + n2 * n6 / 100) * n11;
                                n3 = Math.round(n3*100);
                                n3 = n3/100;
                                String n4 = Double.toString(n3);
                                TextView euro3 = (TextView) findViewById(R.id.euro3);
                                euro3.setText(n4 + " €");
                                sinleft3=n4;
                            /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(1)---n1>n2",Toast.LENGTH_LONG).show();*/

                                n21=Double.parseDouble(sinleft);
                                n22=Double.parseDouble(sinleft2);
                                n23=Double.parseDouble(sinleft3);
                                n24=n21+n22+n23+1;
                                n24=Math.round(n24 * 100);
                                n24=n24/100;
                                String n25=Double.toString(n24);


                                TextView sinololefta = (TextView)findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");
                            }else{
                                n3 = (20 + n2 * n6 / 100) * n11;
                                n3 = Math.round(n3*100);
                                n3 = n3/100;
                                String n4 = Double.toString(n3);
                                TextView euro3 = (TextView) findViewById(R.id.euro3);
                                euro3.setText(n4 + " €");
                                sinleft3=n4;
                            /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(1)---n1>n2",Toast.LENGTH_LONG).show();*/

                                n21=Double.parseDouble(sinleft);
                                n22=Double.parseDouble(sinleft2);
                                n23=Double.parseDouble(sinleft3);
                                n24=n21+n22+n23+1;
                                n24=Math.round(n24 * 100);
                                n24=n24/100;
                                String n25=Double.toString(n24);


                                TextView sinololefta = (TextView)findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");
                            }


                        }else{

                            n7=n1*n6/100;
                            n8=n2-n1;
                            n9=n7/2;
                            if (n9<=n8){
                                n3 = (n7-n9) * n11;
                                n3 = Math.round(n3*100);
                                n3 = n3/100;
                                String n4 = Double.toString(n3);
                                TextView euro3 = (TextView) findViewById(R.id.euro3);
                                euro3.setText(n4 + " €");
                                sinleft3=n4;
                                /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(1)---n1<n2---n9<n8",Toast.LENGTH_LONG).show();*/

                                n21=Double.parseDouble(sinleft);
                                n22=Double.parseDouble(sinleft2);
                                n23=Double.parseDouble(sinleft3);
                                n24=n21+n22+n23+1;
                                n24=Math.round(n24 * 100);
                                n24=n24/100;
                                String n25=Double.toString(n24);


                                TextView sinololefta = (TextView)findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");

                            }
                            else{
                                n3 = (n7-n8)* n11;
                                n3 = Math.round(n3*100);
                                n3 = n3/100;
                                String n4 = Double.toString(n3);
                                TextView euro3 = (TextView) findViewById(R.id.euro3);
                                euro3.setText(n4 + " €");
                                sinleft3=n4;
                                /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(1)---n1<n2---n9>n8",Toast.LENGTH_LONG).show();*/

                                n21=Double.parseDouble(sinleft);
                                n22=Double.parseDouble(sinleft2);
                                n23=Double.parseDouble(sinleft3);
                                n24=n21+n22+n23+1;
                                n24=Math.round(n24 * 100);
                                n24=n24/100;
                                String n25=Double.toString(n24);


                                TextView sinololefta = (TextView)findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");

                            }

                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

            }else if (proto.equals("2") && dio.equals("2")){
                /*Toast.makeText(getApplicationContext(), "proto.equals(2) && dio.equals(2)", Toast.LENGTH_LONG).show();*/
                ArrayAdapter adapter2 = ArrayAdapter.createFromResource(simetoxi2.this, R.array.deutera,
                        android.R.layout.simple_spinner_dropdown_item);
                ArrayAdapter adapter3 = ArrayAdapter.createFromResource(simetoxi2.this, R.array.trita,
                        android.R.layout.simple_spinner_dropdown_item);
                spinner5.setAdapter(adapter2);
                spinner6.setAdapter(adapter3);
                spinner6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        TextView mytexto = (TextView) view;
                        String n10 = mytexto.getText().toString();
                        n11 = Integer.parseInt(n10);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                spinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                        TextView mytext = (TextView) view;
                        final String n5 = mytext.getText().toString();




                        double n1, n2, n3, n6, n7, n8, n9,n21,n22,n23,n24;
                        n1 = Double.parseDouble(capital);
                        n2 = Double.parseDouble(capital1);
                        n6 = Double.parseDouble(n5);
                        if (n1 >= n2) {
                            if((n1-n2)/2 <=20){
                                n3 = ((n1 - n2) / 2 + n2 * n6 / 100) * n11;
                                n3 = Math.round(n3*100);
                                n3 = n3/100;
                                String n4 = Double.toString(n3);
                                TextView euro3 = (TextView) findViewById(R.id.euro3);
                                euro3.setText(n4 + " €");
                                sinleft3=n4;
                            /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(2)---n1>n2",Toast.LENGTH_LONG).show();*/

                                n21=Double.parseDouble(sinleft);
                                n22=Double.parseDouble(sinleft2);
                                n23=Double.parseDouble(sinleft3);
                                n24=n21+n22+n23+1;
                                n24=Math.round(n24 * 100);
                                n24=n24/100;
                                String n25=Double.toString(n24);


                                TextView sinololefta = (TextView)findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");
                            }else{
                                n3 = (20 + n2 * n6 / 100) * n11;
                                n3 = Math.round(n3*100);
                                n3 = n3/100;
                                String n4 = Double.toString(n3);
                                TextView euro3 = (TextView) findViewById(R.id.euro3);
                                euro3.setText(n4 + " €");
                                sinleft3=n4;
                            /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(2)---n1>n2",Toast.LENGTH_LONG).show();*/

                                n21=Double.parseDouble(sinleft);
                                n22=Double.parseDouble(sinleft2);
                                n23=Double.parseDouble(sinleft3);
                                n24=n21+n22+n23+1;
                                n24=Math.round(n24 * 100);
                                n24=n24/100;
                                String n25=Double.toString(n24);


                                TextView sinololefta = (TextView)findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");
                            }


                        }else{

                            n7=n1*n6/100;
                            n8=n2-n1;
                            n9=n7/2;
                            if (n9 <= n8){
                                n3 = (n7-n9) * n11;
                                n3 = Math.round(n3*100);
                                n3 = n3/100;
                                String n4 = Double.toString(n3);
                                TextView euro3 = (TextView) findViewById(R.id.euro3);
                                euro3.setText(n4 + " €");
                                sinleft3=n4;
                                /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(2)---n1<n2---n9<n8",Toast.LENGTH_LONG).show();*/

                                n21=Double.parseDouble(sinleft);
                                n22=Double.parseDouble(sinleft2);
                                n23=Double.parseDouble(sinleft3);
                                n24=n21+n22+n23+1;
                                n24=Math.round(n24 * 100);
                                n24=n24/100;
                                String n25=Double.toString(n24);


                                TextView sinololefta = (TextView)findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");

                            }
                            else{
                                n3 = (n7-n8) * n11;
                                n3 = Math.round(n3*100);
                                n3 = n3/100;
                                String n4 = Double.toString(n3);
                                TextView euro3 = (TextView) findViewById(R.id.euro3);
                                euro3.setText(n4 + " €");
                                sinleft3=n4;
                                /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(2)---n1<n2---n9>n8",Toast.LENGTH_LONG).show();*/

                                n21=Double.parseDouble(sinleft);
                                n22=Double.parseDouble(sinleft2);
                                n23=Double.parseDouble(sinleft3);
                                n24=n21+n22+n23+1;
                                n24=Math.round(n24 * 100);
                                n24=n24/100;
                                String n25=Double.toString(n24);


                                TextView sinololefta = (TextView)findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");

                            }
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }else if (proto.equals("1") && dio.equals("1")){
                /*Toast.makeText(getApplicationContext(), "proto.equals(1) && dio.equals(1)", Toast.LENGTH_LONG).show();*/
                ArrayAdapter adapter2 = ArrayAdapter.createFromResource(simetoxi2.this, R.array.deutera,
                        android.R.layout.simple_spinner_dropdown_item);
                ArrayAdapter adapter3 = ArrayAdapter.createFromResource(simetoxi2.this, R.array.trita,
                        android.R.layout.simple_spinner_dropdown_item);
                spinner5.setAdapter(adapter2);
                spinner6.setAdapter(adapter3);
                spinner6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        TextView mytexto = (TextView) view;
                        String n10 = mytexto.getText().toString();
                        n11 = Integer.parseInt(n10);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                spinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                        TextView mytext = (TextView) view;
                        final String n5 = mytext.getText().toString();




                        double n1, n2, n3, n6, n7, n8, n9,n21,n22,n23,n24;
                        n1 = Double.parseDouble(capital);
                        n2 = Double.parseDouble(capital1);
                        n6 = Double.parseDouble(n5);
                        if (n1 >= n2) {
                            if(n1 - n2 <= 20){
                                n3 = ((n1 - n2) + n2 * n6 / 100) * n11;
                                n3 = Math.round(n3*100);
                                n3 = n3/100;
                                String n4 = Double.toString(n3);
                                TextView euro3 = (TextView) findViewById(R.id.euro3);
                                euro3.setText(n4 + " €");
                                sinleft3=n4;
                            /*Toast.makeText(getApplicationContext(),"proto.equals(1) && dio.equals(1)---n1>n2",Toast.LENGTH_LONG).show();*/

                                n21=Double.parseDouble(sinleft);
                                n22=Double.parseDouble(sinleft2);
                                n23=Double.parseDouble(sinleft3);
                                n24=n21+n22+n23+1;
                                n24=Math.round(n24 * 100);
                                n24=n24/100;
                                String n25=Double.toString(n24);


                                TextView sinololefta = (TextView)findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");
                            }else{
                                n3 = (20 + n2 * n6 / 100) * n11;
                                n3 = Math.round(n3*100);
                                n3 = n3/100;
                                String n4 = Double.toString(n3);
                                TextView euro3 = (TextView) findViewById(R.id.euro3);
                                euro3.setText(n4 + " €");
                                sinleft3=n4;
                            /*Toast.makeText(getApplicationContext(),"proto.equals(1) && dio.equals(1)---n1>n2",Toast.LENGTH_LONG).show();*/

                                n21=Double.parseDouble(sinleft);
                                n22=Double.parseDouble(sinleft2);
                                n23=Double.parseDouble(sinleft3);
                                n24=n21+n22+n23+1;
                                n24=Math.round(n24 * 100);
                                n24=n24/100;
                                String n25=Double.toString(n24);


                                TextView sinololefta = (TextView)findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");
                            }


                        }else{

                            n7=n1*n6/100;
                            n8=n2-n1;
                            n9=n7/2;
                            if (n9 <= n8){
                                n3 = (n7-n9) * n11;
                                n3 = Math.round(n3*100);
                                n3 = n3/100;
                                String n4 = Double.toString(n3);
                                TextView euro3 = (TextView) findViewById(R.id.euro3);
                                euro3.setText(n4 + " €");
                                sinleft3=n4;
                                /*Toast.makeText(getApplicationContext(),"proto.equals(1) && dio.equals(1)---n1<n2---n9<n8",Toast.LENGTH_LONG).show();*/

                                n21=Double.parseDouble(sinleft);
                                n22=Double.parseDouble(sinleft2);
                                n23=Double.parseDouble(sinleft3);
                                n24=n21+n22+n23+1;
                                n24=Math.round(n24 * 100);
                                n24=n24/100;
                                String n25=Double.toString(n24);


                                TextView sinololefta = (TextView)findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");

                            }
                            else{
                                n3 = (n7-n8) * n11;
                                n3 = Math.round(n3*100);
                                n3 = n3/100;
                                String n4 = Double.toString(n3);
                                TextView euro3 = (TextView) findViewById(R.id.euro3);
                                euro3.setText(n4 + " €");
                                sinleft3=n4;
                                /*Toast.makeText(getApplicationContext(),"proto.equals(1) && dio.equals(1)---n1<n2---n9>n8",Toast.LENGTH_LONG).show();*/

                                n21=Double.parseDouble(sinleft);
                                n22=Double.parseDouble(sinleft2);
                                n23=Double.parseDouble(sinleft3);
                                n24=n21+n22+n23+1;
                                n24=Math.round(n24 * 100);
                                n24=n24/100;
                                String n25=Double.toString(n24);


                                TextView sinololefta = (TextView)findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");

                            }



                        }



                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }



        }
    }

    ////////////////////////////////////////////////////////////////


    class ItemAutoTextAdapter extends CursorAdapter
            implements AdapterView.OnItemClickListener {

        private AutoCompleteDbAdapter4 mDbHelper;


        public ItemAutoTextAdapter(AutoCompleteDbAdapter4 dbHelper) {
            // Call the CursorAdapter constructor with a null Cursor.
            super(simetoxi2.this, null);
            mDbHelper = dbHelper;

        }




        @Override
        public Cursor runQueryOnBackgroundThread(CharSequence constraint) {
            if (getFilterQueryProvider() != null) {
                return getFilterQueryProvider().runQuery(constraint);
            }

            Cursor cursor = mDbHelper.getMatchingStates(
                    (constraint != null ? constraint.toString() : null));

            return cursor;
        }



        @Override
        public String convertToString(Cursor cursor) {
            final int columnIndex = cursor.getColumnIndexOrThrow("description");
            final String str = cursor.getString(columnIndex);
            return str;

        }




        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            final String text = convertToString(cursor);
            ((TextView) view.findViewById(R.id.customiti)).setText(text);
            ((TextView) view.findViewById(R.id.customiti)).setTextColor(Color.BLACK);
            ((TextView) view.findViewById(R.id.customiti)).setBackgroundColor(Color.LTGRAY);
        }


        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            final LayoutInflater inflater = LayoutInflater.from(context);
            final View view =
                    inflater.inflate(R.layout.custom_item,
                            parent, false);

            return view;
        }


        @Override
        public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
            // Get the cursor, positioned to the corresponding row in the result set
            Cursor cursor = (Cursor) listView.getItemAtPosition(position);

            // Get the state's capital from this row in the database.
            final String capital = cursor.getString(cursor.getColumnIndexOrThrow("price"));
            final String proto = cursor.getString(cursor.getColumnIndexOrThrow("proto"));
            final String dio = cursor.getString(cursor.getColumnIndexOrThrow("dio"));
            final String capital1 = cursor.getString(cursor.getColumnIndexOrThrow("anafora"));


            // Update the parent class's TextView
            mStateCapitalView.setText(String.valueOf(capital) + " €");
            TextView Desciptionperigrafi = (TextView)findViewById(R.id.desciptionperigrafi);
            Desciptionperigrafi.setText(String.valueOf(capital1) + " €");

            TextView posostotext = (TextView)findViewById(R.id.posostotext);
            Spinner spinner1 = (Spinner)findViewById(R.id.spinner1);
            Spinner spinner2 = (Spinner)findViewById(R.id.spinner2);
            TextView simetoxi = (TextView)findViewById(R.id.simetoxi);
                     /*TextView euro$ = (TextView)findViewById(R.id.euro$);*/

            InputMethodManager in = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            in.hideSoftInputFromWindow(view.getApplicationWindowToken(),0);





            if (proto.equals("2") && dio.equals("1")) {
                /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(1)",Toast.LENGTH_LONG).show();*/
                ArrayAdapter adapter2 = ArrayAdapter.createFromResource(simetoxi2.this, R.array.deutera,
                        android.R.layout.simple_spinner_dropdown_item);

                ArrayAdapter adapter3 = ArrayAdapter.createFromResource(simetoxi2.this, R.array.trita,
                        android.R.layout.simple_spinner_dropdown_item);
                spinner1.setAdapter(adapter2);
                spinner2.setAdapter(adapter3);
                spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        TextView mytexto = (TextView) view;
                        String n10= mytexto.getText().toString();
                        n11= Integer.parseInt(n10);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                        TextView mytext = (TextView) view;
                        final String n5 = mytext.getText().toString();


                        double n1, n2, n3, n6, n7, n8, n9, n21, n22, n23, n24;
                        n1 = Double.parseDouble(capital);
                        n2 = Double.parseDouble(capital1);
                        n6 = Double.parseDouble(n5);
                        if (n1 >= n2) {
                            if (n1 - n2 <= 20){
                                n3 = ((n1 - n2) + n2 * n6 / 100) * n11;
                                n3 = Math.round(n3*100);
                                n3 = n3/100;
                                String n4 = Double.toString(n3);
                                TextView euro$ = (TextView) findViewById(R.id.euro$);
                                euro$.setText(n4 + " €");
                                sinleft = n4;
                            /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(1)---n1>n2",Toast.LENGTH_LONG).show();*/

                                n21=Double.parseDouble(sinleft);
                                n22=Double.parseDouble(sinleft2);
                                n23=Double.parseDouble(sinleft3);
                                n24=n21+n22+n23+1;
                                n24=Math.round(n24 * 100);
                                n24=n24/100;
                                String n25=Double.toString(n24);


                                TextView sinololefta = (TextView)findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");
                            }else{
                                n3 = (20 + n2 * n6 / 100) * n11;
                                n3 = Math.round(n3*100);
                                n3 = n3/100;
                                String n4 = Double.toString(n3);
                                TextView euro$ = (TextView) findViewById(R.id.euro$);
                                euro$.setText(n4 + " €");
                                sinleft = n4;
                            /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(1)---n1>n2",Toast.LENGTH_LONG).show();*/

                                n21=Double.parseDouble(sinleft);
                                n22=Double.parseDouble(sinleft2);
                                n23=Double.parseDouble(sinleft3);
                                n24=n21+n22+n23+1;
                                n24=Math.round(n24 * 100);
                                n24=n24/100;
                                String n25=Double.toString(n24);


                                TextView sinololefta = (TextView)findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");
                            }




                        }else{

                            n7=n1*n6/100;
                            n8=n2-n1;
                            n9=n7/2;
                            if (n9<=n8){
                                n3 = (n7-n9) * n11;
                                n3 = Math.round(n3*100);
                                n3 = n3/100;
                                String n4 = Double.toString(n3);
                                TextView euro$ = (TextView) findViewById(R.id.euro$);
                                euro$.setText(n4 + " €");
                                sinleft = n4;
                                /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(1)---n1<n2---n9<n8",Toast.LENGTH_LONG).show();*/

                                n21=Double.parseDouble(sinleft);
                                n22=Double.parseDouble(sinleft2);
                                n23=Double.parseDouble(sinleft3);
                                n24=n21+n22+n23+1;
                                n24=Math.round(n24 * 100);
                                n24=n24/100;
                                String n25=Double.toString(n24);


                                TextView sinololefta = (TextView)findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");

                            }
                            else{
                                n3 = (n7-n8)* n11;
                                n3 = Math.round(n3*100);
                                n3 = n3/100;
                                String n4 = Double.toString(n3);
                                TextView euro$ = (TextView) findViewById(R.id.euro$);
                                euro$.setText(n4 + " €");
                                sinleft = n4;
                                /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(1)---n1<n2---n9>n8",Toast.LENGTH_LONG).show();*/

                                n21=Double.parseDouble(sinleft);
                                n22=Double.parseDouble(sinleft2);
                                n23=Double.parseDouble(sinleft3);
                                n24=n21+n22+n23+1;
                                n24=Math.round(n24 * 100);
                                n24=n24/100;
                                String n25=Double.toString(n24);


                                TextView sinololefta = (TextView)findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");

                            }



                        }



                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

            }else if (proto.equals("2") && dio.equals("2")){
                /*Toast.makeText(getApplicationContext(), "proto.equals(2) && dio.equals(2)", Toast.LENGTH_LONG).show();*/
                ArrayAdapter adapter2 = ArrayAdapter.createFromResource(simetoxi2.this, R.array.deutera,
                        android.R.layout.simple_spinner_dropdown_item);
                ArrayAdapter adapter3 = ArrayAdapter.createFromResource(simetoxi2.this, R.array.trita,
                        android.R.layout.simple_spinner_dropdown_item);
                spinner1.setAdapter(adapter2);
                spinner2.setAdapter(adapter3);
                spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        TextView mytexto = (TextView) view;
                        String n10 = mytexto.getText().toString();
                        n11 = Integer.parseInt(n10);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                        TextView mytext = (TextView) view;
                        final String n5 = mytext.getText().toString();

                        double n1, n2, n3, n6, n7, n8, n9,n21,n22,n23,n24;
                        n1 = Double.parseDouble(capital);
                        n2 = Double.parseDouble(capital1);
                        n6 = Double.parseDouble(n5);
                        if (n1 >= n2) {
                            if((n1 - n2)/2 <= 20){
                                n3 = ((n1 - n2) / 2 + n2 * n6 / 100) * n11;
                                n3 = Math.round(n3*100);
                                n3 = n3/100;
                                String n4 = Double.toString(n3);
                                TextView euro$ = (TextView) findViewById(R.id.euro$);
                                euro$.setText(n4 + " €");
                                sinleft = n4;
                            /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(2)---n1>n2",Toast.LENGTH_LONG).show();*/

                                n21=Double.parseDouble(sinleft);
                                n22=Double.parseDouble(sinleft2);
                                n23=Double.parseDouble(sinleft3);
                                n24=n21+n22+n23+1;
                                n24=Math.round(n24 * 100);
                                n24=n24/100;
                                String n25=Double.toString(n24);


                                TextView sinololefta = (TextView)findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");
                            }else{
                                n3 = (20 + n2 * n6 / 100) * n11;
                                n3 = Math.round(n3*100);
                                n3 = n3/100;
                                String n4 = Double.toString(n3);
                                TextView euro$ = (TextView) findViewById(R.id.euro$);
                                euro$.setText(n4 + " €");
                                sinleft = n4;
                            /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(2)---n1>n2",Toast.LENGTH_LONG).show();*/

                                n21=Double.parseDouble(sinleft);
                                n22=Double.parseDouble(sinleft2);
                                n23=Double.parseDouble(sinleft3);
                                n24=n21+n22+n23+1;
                                n24=Math.round(n24 * 100);
                                n24=n24/100;
                                String n25=Double.toString(n24);


                                TextView sinololefta = (TextView)findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");
                            }


                        }else{

                            n7=n1*n6/100;
                            n8=n2-n1;
                            n9=n7/2;
                            if (n9 <= n8){
                                n3 = (n7-n9) * n11;
                                n3 = Math.round(n3*100);
                                n3 = n3/100;
                                String n4 = Double.toString(n3);
                                TextView euro$ = (TextView) findViewById(R.id.euro$);
                                euro$.setText(n4 + " €");
                                sinleft = n4;
                                /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(2)---n1<n2---n9<n8",Toast.LENGTH_LONG).show();*/

                                n21=Double.parseDouble(sinleft);
                                n22=Double.parseDouble(sinleft2);
                                n23=Double.parseDouble(sinleft3);
                                n24=n21+n22+n23+1;
                                n24=Math.round(n24 * 100);
                                n24=n24/100;
                                String n25=Double.toString(n24);


                                TextView sinololefta = (TextView)findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");

                            }
                            else{
                                n3 = (n7-n8) * n11;
                                n3 = Math.round(n3*100);
                                n3 = n3/100;
                                String n4 = Double.toString(n3);
                                TextView euro$ = (TextView) findViewById(R.id.euro$);
                                euro$.setText(n4 + " €");
                                sinleft = n4;
                                /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(2)---n1<n2---n9>n8",Toast.LENGTH_LONG).show();*/

                                n21=Double.parseDouble(sinleft);
                                n22=Double.parseDouble(sinleft2);
                                n23=Double.parseDouble(sinleft3);
                                n24=n21+n22+n23+1;
                                n24=Math.round(n24 * 100);
                                n24=n24/100;
                                String n25=Double.toString(n24);


                                TextView sinololefta = (TextView)findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");

                            }



                        }



                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }else if (proto.equals("1") && dio.equals("1")){
                /*Toast.makeText(getApplicationContext(), "proto.equals(1) && dio.equals(1)", Toast.LENGTH_LONG).show();*/
                ArrayAdapter adapter2 = ArrayAdapter.createFromResource(simetoxi2.this, R.array.deutera,
                        android.R.layout.simple_spinner_dropdown_item);
                ArrayAdapter adapter3 = ArrayAdapter.createFromResource(simetoxi2.this, R.array.trita,
                        android.R.layout.simple_spinner_dropdown_item);
                spinner1.setAdapter(adapter2);
                spinner2.setAdapter(adapter3);
                spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        TextView mytexto = (TextView) view;
                        String n10 = mytexto.getText().toString();
                        n11 = Integer.parseInt(n10);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                        TextView mytext = (TextView) view;
                        final String n5 = mytext.getText().toString();


                        double n1, n2, n3, n6, n7, n8, n9,n21,n22,n23,n24;
                        n1 = Double.parseDouble(capital);
                        n2 = Double.parseDouble(capital1);
                        n6 = Double.parseDouble(n5);
                        if (n1 >= n2) {
                            if(n1 - n2 <= 20){
                                n3 = ((n1 - n2) + n2 * n6 / 100) * n11;
                                n3 = Math.round(n3*100);
                                n3 = n3/100;
                                String n4 = Double.toString(n3);
                                TextView euro$ = (TextView) findViewById(R.id.euro$);
                                euro$.setText(n4 + " €");
                                sinleft = n4;
                            /*Toast.makeText(getApplicationContext(),"proto.equals(1) && dio.equals(1)---n1>n2",Toast.LENGTH_LONG).show();*/

                                n21=Double.parseDouble(sinleft);
                                n22=Double.parseDouble(sinleft2);
                                n23=Double.parseDouble(sinleft3);
                                n24=n21+n22+n23+1;
                                n24=Math.round(n24 * 100);
                                n24=n24/100;
                                String n25=Double.toString(n24);


                                TextView sinololefta = (TextView)findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");
                            }else{
                                n3 = (20 + n2 * n6 / 100) * n11;
                                n3 = Math.round(n3*100);
                                n3 = n3/100;
                                String n4 = Double.toString(n3);
                                TextView euro$ = (TextView) findViewById(R.id.euro$);
                                euro$.setText(n4 + " €");
                                sinleft = n4;
                            /*Toast.makeText(getApplicationContext(),"proto.equals(1) && dio.equals(1)---n1>n2",Toast.LENGTH_LONG).show();*/

                                n21=Double.parseDouble(sinleft);
                                n22=Double.parseDouble(sinleft2);
                                n23=Double.parseDouble(sinleft3);
                                n24=n21+n22+n23+1;
                                n24=Math.round(n24 * 100);
                                n24=n24/100;
                                String n25=Double.toString(n24);


                                TextView sinololefta = (TextView)findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");
                            }


                        }else{

                            n7=n1*n6/100;
                            n8=n2-n1;
                            n9=n7/2;
                            if (n9 <= n8){
                                n3 = (n7-n9) * n11;
                                n3 = Math.round(n3*100);
                                n3 = n3/100;
                                String n4 = Double.toString(n3);
                                TextView euro$ = (TextView) findViewById(R.id.euro$);
                                euro$.setText(n4 + " €");
                                sinleft = n4;
                                /*Toast.makeText(getApplicationContext(),"proto.equals(1) && dio.equals(1)---n1<n2---n9<n8",Toast.LENGTH_LONG).show();*/

                                n21=Double.parseDouble(sinleft);
                                n22=Double.parseDouble(sinleft2);
                                n23=Double.parseDouble(sinleft3);
                                n24=n21+n22+n23+1;
                                n24=Math.round(n24 * 100);
                                n24=n24/100;
                                String n25=Double.toString(n24);


                                TextView sinololefta = (TextView)findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");

                            }
                            else{
                                n3 = (n7-n8) * n11;
                                n3 = Math.round(n3*100);
                                n3 = n3/100;
                                String n4 = Double.toString(n3);
                                TextView euro$ = (TextView) findViewById(R.id.euro$);
                                euro$.setText(n4 + " €");
                                sinleft = n4;
                                /*Toast.makeText(getApplicationContext(),"proto.equals(1) && dio.equals(1)---n1<n2---n9>n8",Toast.LENGTH_LONG).show();*/

                                n21=Double.parseDouble(sinleft);
                                n22=Double.parseDouble(sinleft2);
                                n23=Double.parseDouble(sinleft3);
                                n24=n21+n22+n23+1;
                                n24=Math.round(n24*100);
                                n24=n24/100;
                                String n25=Double.toString(n24);


                                TextView sinololefta = (TextView)findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");

                            }



                        }



                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }



        }
    }



    private TextView mStateCapitalView;
    private AutoCompleteTextView mStateNameView;

    String sinleft= String.valueOf(0);
    String sinleft2= String.valueOf(0);
    String sinleft3= String.valueOf(0);




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        AutoCompleteDbAdapter4 dbHelper = null;
        try {
            dbHelper = new AutoCompleteDbAdapter4(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        super.onCreate(savedInstanceState);

        TextView Perigrafiview = (TextView)findViewById(R.id.Perigrafiview);
        TextView Desciptionperigrafi = (TextView)findViewById(R.id.desciptionperigrafi);
        TextView posostotext = (TextView)findViewById(R.id.posostotext);
        TextView posotitatext = (TextView)findViewById(R.id.posotitatext);
        Spinner spinner1 = (Spinner)findViewById(R.id.spinner1);
        Spinner spinner2 = (Spinner)findViewById(R.id.spinner2);
        TextView simetoxi = (TextView)findViewById(R.id.simetoxi);
        TextView euro$ = (TextView)findViewById(R.id.euro$);

        TextView farmako2 = (TextView)findViewById(R.id.farmako2);
        AutoCompleteTextView autofarmako2 = (AutoCompleteTextView)findViewById(R.id.autofarmako2);
        TextView lianiki2 = (TextView)findViewById(R.id.lianiki2);
        TextView lianikitimi2 = (TextView)findViewById(R.id.lianikitimi2);
        TextView anafora2 = (TextView)findViewById(R.id.anafora2);
        TextView anaforatimi2 = (TextView)findViewById(R.id.anaforatimi2);
        TextView pososto2 = (TextView)findViewById(R.id.pososto2);
        TextView posotita2 = (TextView)findViewById(R.id.posotita2);
        TextView euro2 = (TextView)findViewById(R.id.euro2);
        TextView simetoxi2 = (TextView)findViewById(R.id.simetoxi2);
        Spinner spinner3 = (Spinner)findViewById(R.id.spinner3);
        Spinner spinner4 = (Spinner)findViewById(R.id.spinner4);

        TextView farmako3 = (TextView)findViewById(R.id.farmako3);
        AutoCompleteTextView autofarmako3 = (AutoCompleteTextView)findViewById(R.id.autofarmako3);
        TextView lianiki3 = (TextView)findViewById(R.id.lianiki3);
        TextView lianikitimi3 = (TextView)findViewById(R.id.lianikitimi3);
        TextView anafora3 = (TextView)findViewById(R.id.anafora3);
        TextView anaforatimi3 = (TextView)findViewById(R.id.anaforatimi3);
        TextView pososto3 = (TextView)findViewById(R.id.pososto3);
        TextView posotita3 = (TextView)findViewById(R.id.posotita3);
        TextView euro3 = (TextView)findViewById(R.id.euro3);
        TextView simetoxi3 = (TextView)findViewById(R.id.simetoxi3);
        Spinner spinner5 = (Spinner)findViewById(R.id.spinner5);
        Spinner spinner6 = (Spinner)findViewById(R.id.spinner6);

        TextView sinolo = (TextView)findViewById(R.id.sinolo);
        TextView sinololefta = (TextView)findViewById(R.id.sinololefta);

        ImageView image21 = (ImageView)findViewById(R.id.image21);
        ImageView image22 = (ImageView)findViewById(R.id.image22);

        Button pdfix = (Button)findViewById(R.id.pdfix);
        Button pdfixs = (Button)findViewById(R.id.pdfixs);



        mStateCapitalView = (TextView) findViewById(R.id.state_capital);
        mStateNameView = (AutoCompleteTextView) findViewById(R.id.state_name);

        // Create an ItemAutoTextAdapter for the State Name field,
        // and set it as the OnItemClickListener for that field.
        ItemAutoTextAdapter adapter = this.new ItemAutoTextAdapter(dbHelper);
        mStateNameView.setAdapter(adapter);
        mStateNameView.setOnItemClickListener(adapter);

        ItemAutoTextAdapter2 adapter2 = this.new ItemAutoTextAdapter2(dbHelper);
        autofarmako2.setAdapter(adapter2);
        autofarmako2.setOnItemClickListener(adapter2);

        ItemAutoTextAdapter3 adapter3 = this.new ItemAutoTextAdapter3(dbHelper);
        autofarmako3.setAdapter(adapter3);
        autofarmako3.setOnItemClickListener(adapter3);



    }

    public void but21xclick (View view){
        mStateNameView.setText("");
        mStateCapitalView.setText("");
        TextView Desciptionperigrafi = (TextView)findViewById(R.id.desciptionperigrafi);
        Desciptionperigrafi.setText("");
        TextView euro$ = (TextView)findViewById(R.id.euro$);
        euro$.setText("");
        sinleft=String.valueOf(0);

        double n21,n22,n23,n24;

        n21=Double.parseDouble(sinleft);
        n22=Double.parseDouble(sinleft2);
        n23=Double.parseDouble(sinleft3);
        n24=n21+n22+n23+1;
        n24=Math.round(n24 * 100);
        n24=n24/100;
        String n25=Double.toString(n24);


        TextView sinololefta = (TextView)findViewById(R.id.sinololefta);
        sinololefta.setText(n25 + " €");
    }

    public void but22xclick (View view){
        AutoCompleteTextView autofarmako2 = (AutoCompleteTextView)findViewById(R.id.autofarmako2);
        autofarmako2.setText("");
        TextView lianikitimi2 = (TextView)findViewById(R.id.lianikitimi2);
        lianikitimi2.setText("");
        TextView anaforatimi2 = (TextView)findViewById(R.id.anaforatimi2);
        anaforatimi2.setText("");
        TextView euro2 = (TextView)findViewById(R.id.euro2);
        euro2.setText("");
        sinleft2=String.valueOf(0);

        double n21,n22,n23,n24;

        n21=Double.parseDouble(sinleft);
        n22=Double.parseDouble(sinleft2);
        n23=Double.parseDouble(sinleft3);
        n24=n21+n22+n23+1;
        n24=Math.round(n24 * 100);
        n24=n24/100;
        String n25=Double.toString(n24);


        TextView sinololefta = (TextView)findViewById(R.id.sinololefta);
        sinololefta.setText(n25 + " €");
    }

    public void but23xclick (View view){
        AutoCompleteTextView autofarmako3 = (AutoCompleteTextView)findViewById(R.id.autofarmako3);
        autofarmako3.setText("");
        TextView lianikitimi3 = (TextView)findViewById(R.id.lianikitimi3);
        TextView anaforatimi3 = (TextView)findViewById(R.id.anaforatimi3);
        TextView euro3 = (TextView)findViewById(R.id.euro3);
        lianikitimi3.setText("");
        anaforatimi3.setText("");
        euro3.setText("");
        sinleft3=String.valueOf(0);

        double n21,n22,n23,n24;

        n21=Double.parseDouble(sinleft);
        n22=Double.parseDouble(sinleft2);
        n23=Double.parseDouble(sinleft3);
        n24=n21+n22+n23+1;
        n24=Math.round(n24 * 100);
        n24=n24/100;
        String n25=Double.toString(n24);


        TextView sinololefta = (TextView)findViewById(R.id.sinololefta);
        sinololefta.setText(n25 + " €");

    }

    String flname;

    public simetoxi2() {
        flname = null;
    }

    public void pdfixda (View view){


        AlertDialog.Builder alertdg = new AlertDialog.Builder(this);
        alertdg.setTitle("Επιλέξτε όνομα αρχείου");
        /*alertdg.setMessage("Επιλέξτε όνομα");*/

        final EditText inputname = new EditText(this);
        inputname.setWidth(1400);

        LinearLayout layout = new LinearLayout(this);
        layout.addView(inputname);
        alertdg.setView(layout);

        alertdg.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alertdg.setNegativeButton("Ακύρωση", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertdg.show();




        /*LayoutInflater inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
        RelativeLayout root = (RelativeLayout) inflater.inflate(R.layout.activity_simetoxi, null);
        root.setDrawingCacheEnabled(true);
        Bitmap screen = getBitmapFromView(this.getWindow().findViewById(R.id.relative1));*/

        /*RelativeLayout root = (RelativeLayout)findViewById(R.id.relative1);
        root.setDrawingCacheEnabled(true);
        root.buildDrawingCache();
        Bitmap screen = root.getDrawingCache();*/

        String path = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/Recipe-pdf";

        File dir = new File(path);
        if (!dir.exists())
            dir.mkdirs();

    }



    private String title = "Εγκατάσταση File Manager?";
    private String message = "Αυτή η λειτουργία χρειάζεται το ES File Manager..Εγκατάσταση τώρα??   (Διαφορετικά δείτε τα αρχεία στη διαδρομή= /Εσωτερικός χώρος αποθήκευσης/Recipe-pdf";
    private String buttonYes = "Ναι";
    private String buttonNo = "Όχι";


    public void pdfixdas (View view){
        String path = Environment.getExternalStorageDirectory()
                + "Recipe-pdf";

        File dir = new File(path);



        /*Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(new File(dir, "simetoxi.pdf"));
        intent.setDataAndType(uri, "application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);*/

        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.parse(Environment.getExternalStorageDirectory()
                + "/Recipe-pdf/");
        intent.setDataAndType(uri, "resource/folder");
        if(intent.resolveActivityInfo(getPackageManager(),0)!=null){
            startActivity(Intent.createChooser(intent,"Open folder"));
        }else{
            AlertDialog.Builder downloadDialog = new AlertDialog.Builder(this);
            downloadDialog.setTitle(title);
            downloadDialog.setMessage(message);
            downloadDialog.setPositiveButton(buttonYes, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.estrongs.android.pop");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    try {
                        startActivity(Intent.createChooser(intent,"Open folder"));
                    } catch (ActivityNotFoundException anfe) {
                        Toast.makeText(getApplicationContext(), "Δεν έχετε Android Market", Toast.LENGTH_LONG).show();
                    }
                }
            });
            downloadDialog.setNegativeButton(buttonNo, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            downloadDialog.show();
        }

    }


}

