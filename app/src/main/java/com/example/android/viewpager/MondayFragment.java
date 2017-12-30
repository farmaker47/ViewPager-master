
package com.example.android.viewpager;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;

/**
 * Fragment that displays "Monday".
 */
public class MondayFragment extends Fragment {

    int n11;
    private View myFragmentView;

    String sinleft = String.valueOf(0);
    String sinleft2 = String.valueOf(0);
    String sinleft3 = String.valueOf(0);

    private TextView mStateCapitalView;
    private AutoCompleteTextView mStateNameView,autofarmako2, autofarmako3;
    private TextView Desciptionperigrafi, Perigrafiview, posostotext, posotitatext, simetoxi, euro$, farmako2, sinololefta;
    private Spinner spinner1, spinner2;

    private ImageButton but21x;
    private RelativeLayout rel1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        AutoCompleteDbAdapter4 dbHelper = null;
        try {
            dbHelper = new AutoCompleteDbAdapter4(getActivity());
        } catch (IOException e) {
            e.printStackTrace();
        }

        myFragmentView = inflater.inflate(R.layout.fragment_monday, container, false);


        autofarmako2 = (AutoCompleteTextView) myFragmentView.findViewById(R.id.autofarmako2);
        rel1 = (RelativeLayout)myFragmentView.findViewById(R.id.relative1);
        Perigrafiview = (TextView) myFragmentView.findViewById(R.id.Perigrafiview);
        Desciptionperigrafi = (TextView) myFragmentView.findViewById(R.id.desciptionperigrafi);
        posostotext = (TextView) myFragmentView.findViewById(R.id.posostotext);
        posotitatext = (TextView) myFragmentView.findViewById(R.id.posotitatext);
        spinner1 = (Spinner) myFragmentView.findViewById(R.id.spinner1);
        spinner2 = (Spinner) myFragmentView.findViewById(R.id.spinner2);
        simetoxi = (TextView) myFragmentView.findViewById(R.id.simetoxi);
        euro$ = (TextView) myFragmentView.findViewById(R.id.euro$);
        mStateCapitalView = (TextView) myFragmentView.findViewById(R.id.state_capital);

        but21x = (ImageButton) myFragmentView.findViewById(R.id.but21x);
        but21x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mStateNameView.setText("");
                mStateCapitalView.setText("");
                Desciptionperigrafi.setText("");
                euro$.setText("");
                sinleft = String.valueOf(0);

                double n21, n22, n23, n24;

                n21 = Double.parseDouble(sinleft);
                n22 = Double.parseDouble(sinleft2);
                n23 = Double.parseDouble(sinleft3);
                n24 = n21 + n22 + n23 + 1;
                n24 = Math.round(n24 * 100);
                n24 = n24 / 100;
                String n25 = Double.toString(n24);

                sinololefta.setText(n25 + " €");
            }
        });

        farmako2 = (TextView) myFragmentView.findViewById(R.id.farmako2);

        autofarmako3 = (AutoCompleteTextView) myFragmentView.findViewById(R.id.autofarmako3);
        TextView lianiki2 = (TextView) myFragmentView.findViewById(R.id.lianiki2);
        TextView lianikitimi2 = (TextView) myFragmentView.findViewById(R.id.lianikitimi2);
        TextView anafora2 = (TextView) myFragmentView.findViewById(R.id.anafora2);
        TextView anaforatimi2 = (TextView) myFragmentView.findViewById(R.id.anaforatimi2);
        TextView pososto2 = (TextView) myFragmentView.findViewById(R.id.pososto2);
        TextView posotita2 = (TextView) myFragmentView.findViewById(R.id.posotita2);
        TextView euro2 = (TextView) myFragmentView.findViewById(R.id.euro2);
        TextView simetoxi2 = (TextView) myFragmentView.findViewById(R.id.simetoxi2);
        Spinner spinner3 = (Spinner) myFragmentView.findViewById(R.id.spinner3);
        Spinner spinner4 = (Spinner) myFragmentView.findViewById(R.id.spinner4);

        TextView farmako3 = (TextView) myFragmentView.findViewById(R.id.farmako3);

        mStateNameView = (AutoCompleteTextView) myFragmentView.findViewById(R.id.state_name);

        TextView lianiki3 = (TextView) myFragmentView.findViewById(R.id.lianiki3);
        TextView lianikitimi3 = (TextView) myFragmentView.findViewById(R.id.lianikitimi3);
        TextView anafora3 = (TextView) myFragmentView.findViewById(R.id.anafora3);
        TextView anaforatimi3 = (TextView) myFragmentView.findViewById(R.id.anaforatimi3);
        TextView pososto3 = (TextView) myFragmentView.findViewById(R.id.pososto3);
        TextView posotita3 = (TextView) myFragmentView.findViewById(R.id.posotita3);
        TextView euro3 = (TextView) myFragmentView.findViewById(R.id.euro3);
        TextView simetoxi3 = (TextView) myFragmentView.findViewById(R.id.simetoxi3);
        Spinner spinner5 = (Spinner) myFragmentView.findViewById(R.id.spinner5);
        Spinner spinner6 = (Spinner) myFragmentView.findViewById(R.id.spinner6);

        TextView sinolo = (TextView) myFragmentView.findViewById(R.id.sinolo);
        sinololefta = (TextView) myFragmentView.findViewById(R.id.sinololefta);

        ImageView image21 = (ImageView) myFragmentView.findViewById(R.id.image21);
        ImageView image22 = (ImageView) myFragmentView.findViewById(R.id.image22);

        Button pdfix = (Button) myFragmentView.findViewById(R.id.pdfix);
        Button pdfixs = (Button) myFragmentView.findViewById(R.id.pdfixs);

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

        return myFragmentView;
    }


    class ItemAutoTextAdapter2 extends CursorAdapter
            implements AdapterView.OnItemClickListener {

        private AutoCompleteDbAdapter4 mDbHelper;


        public ItemAutoTextAdapter2(AutoCompleteDbAdapter4 dbHelper) {
            // Call the CursorAdapter constructor with a null Cursor.
            super(getContext(), null);
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

            TextView lianikitimi2 = (TextView) view.findViewById(R.id.lianikitimi2);
            lianikitimi2.setText(String.valueOf(capital) + " €");
            TextView anaforatimi2 = (TextView) view.findViewById(R.id.anaforatimi2);
            anaforatimi2.setText(String.valueOf(capital1) + " €");

            TextView posotita2 = (TextView) view.findViewById(R.id.posotita2);
            Spinner spinner3 = (Spinner) view.findViewById(R.id.spinner3);
            Spinner spinner4 = (Spinner) view.findViewById(R.id.spinner4);
            TextView simetoxi2 = (TextView) view.findViewById(R.id.simetoxi2);
            TextView euro2 = (TextView) view.findViewById(R.id.euro2);

            InputMethodManager in = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            in.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);


            if (proto.equals("2") && dio.equals("1")) {
                /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(1)",Toast.LENGTH_LONG).show();*/
                ArrayAdapter adapter2 = ArrayAdapter.createFromResource(getContext(), R.array.deutera,
                        android.R.layout.simple_spinner_dropdown_item);
                ArrayAdapter adapter3 = ArrayAdapter.createFromResource(getContext(), R.array.trita,
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


                        double n1, n2, n3, n6, n7, n8, n9, n21, n22, n23, n24;
                        n1 = Double.parseDouble(capital);
                        n2 = Double.parseDouble(capital1);
                        n6 = Double.parseDouble(n5);
                        if (n1 >= n2) {
                            if (n1 - n2 <= 20) {
                                n3 = ((n1 - n2) + n2 * n6 / 100) * n11;
                                n3 = Math.round(n3 * 100);
                                n3 = n3 / 100;
                                String n4 = Double.toString(n3);
                                TextView euro2 = (TextView) view.findViewById(R.id.euro2);
                                euro2.setText(n4 + " €");
                                sinleft2 = n4;
                            /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(1)---n1>n2",Toast.LENGTH_LONG).show();*/

                                n21 = Double.parseDouble(sinleft);
                                n22 = Double.parseDouble(sinleft2);
                                n23 = Double.parseDouble(sinleft3);
                                n24 = n21 + n22 + n23 + 1;
                                n24 = Math.round(n24 * 100);
                                n24 = n24 / 100;
                                String n25 = Double.toString(n24);


                                TextView sinololefta = (TextView) view.findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");
                            } else {
                                n3 = (20 + n2 * n6 / 100) * n11;
                                n3 = Math.round(n3 * 100);
                                n3 = n3 / 100;
                                String n4 = Double.toString(n3);
                                TextView euro2 = (TextView) view.findViewById(R.id.euro2);
                                euro2.setText(n4 + " €");
                                sinleft2 = n4;
                            /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(1)---n1>n2",Toast.LENGTH_LONG).show();*/

                                n21 = Double.parseDouble(sinleft);
                                n22 = Double.parseDouble(sinleft2);
                                n23 = Double.parseDouble(sinleft3);
                                n24 = n21 + n22 + n23 + 1;
                                n24 = Math.round(n24 * 100);
                                n24 = n24 / 100;
                                String n25 = Double.toString(n24);


                                TextView sinololefta = (TextView) view.findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");
                            }


                        } else {

                            n7 = n1 * n6 / 100;
                            n8 = n2 - n1;
                            n9 = n7 / 2;
                            if (n9 <= n8) {
                                n3 = (n7 - n9) * n11;
                                n3 = Math.round(n3 * 100);
                                n3 = n3 / 100;
                                String n4 = Double.toString(n3);
                                TextView euro2 = (TextView) view.findViewById(R.id.euro2);
                                euro2.setText(n4 + " €");
                                sinleft2 = n4;
                                /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(1)---n1<n2---n9<n8",Toast.LENGTH_LONG).show();*/

                                n21 = Double.parseDouble(sinleft);
                                n22 = Double.parseDouble(sinleft2);
                                n23 = Double.parseDouble(sinleft3);
                                n24 = n21 + n22 + n23 + 1;
                                n24 = Math.round(n24 * 100);
                                n24 = n24 / 100;
                                String n25 = Double.toString(n24);


                                TextView sinololefta = (TextView) view.findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");

                            } else {
                                n3 = (n7 - n8) * n11;
                                n3 = Math.round(n3 * 100);
                                n3 = n3 / 100;
                                String n4 = Double.toString(n3);
                                TextView euro2 = (TextView) view.findViewById(R.id.euro2);
                                euro2.setText(n4 + " €");
                                sinleft2 = n4;
                                /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(1)---n1<n2---n9>n8",Toast.LENGTH_LONG).show();*/

                                n21 = Double.parseDouble(sinleft);
                                n22 = Double.parseDouble(sinleft2);
                                n23 = Double.parseDouble(sinleft3);
                                n24 = n21 + n22 + n23 + 1;
                                n24 = Math.round(n24 * 100);
                                n24 = n24 / 100;
                                String n25 = Double.toString(n24);


                                TextView sinololefta = (TextView) view.findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");

                            }

                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

            } else if (proto.equals("2") && dio.equals("2")) {
                /*Toast.makeText(getApplicationContext(), "proto.equals(2) && dio.equals(2)", Toast.LENGTH_LONG).show();*/
                ArrayAdapter adapter2 = ArrayAdapter.createFromResource(getContext(), R.array.deutera,
                        android.R.layout.simple_spinner_dropdown_item);
                ArrayAdapter adapter3 = ArrayAdapter.createFromResource(getContext(), R.array.trita,
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


                        double n1, n2, n3, n6, n7, n8, n9, n21, n22, n23, n24;
                        n1 = Double.parseDouble(capital);
                        n2 = Double.parseDouble(capital1);
                        n6 = Double.parseDouble(n5);
                        if (n1 >= n2) {
                            if ((n1 - n2) / 2 <= 20) {
                                n3 = ((n1 - n2) / 2 + n2 * n6 / 100) * n11;
                                n3 = Math.round(n3 * 100);
                                n3 = n3 / 100;
                                String n4 = Double.toString(n3);
                                TextView euro2 = (TextView) view.findViewById(R.id.euro2);
                                euro2.setText(n4 + " €");
                                sinleft2 = n4;
                            /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(2)---n1>n2",Toast.LENGTH_LONG).show();*/

                                n21 = Double.parseDouble(sinleft);
                                n22 = Double.parseDouble(sinleft2);
                                n23 = Double.parseDouble(sinleft3);
                                n24 = n21 + n22 + n23 + 1;
                                n24 = Math.round(n24 * 100);
                                n24 = n24 / 100;
                                String n25 = Double.toString(n24);


                                TextView sinololefta = (TextView) view.findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");
                            } else {
                                n3 = (20 + n2 * n6 / 100) * n11;
                                n3 = Math.round(n3 * 100);
                                n3 = n3 / 100;
                                String n4 = Double.toString(n3);
                                TextView euro2 = (TextView) view.findViewById(R.id.euro2);
                                euro2.setText(n4 + " €");
                                sinleft2 = n4;
                            /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(2)---n1>n2",Toast.LENGTH_LONG).show();*/

                                n21 = Double.parseDouble(sinleft);
                                n22 = Double.parseDouble(sinleft2);
                                n23 = Double.parseDouble(sinleft3);
                                n24 = n21 + n22 + n23 + 1;
                                n24 = Math.round(n24 * 100);
                                n24 = n24 / 100;
                                String n25 = Double.toString(n24);


                                TextView sinololefta = (TextView) view.findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");
                            }


                        } else {

                            n7 = n1 * n6 / 100;
                            n8 = n2 - n1;
                            n9 = n7 / 2;
                            if (n9 <= n8) {
                                n3 = (n7 - n9) * n11;
                                n3 = Math.round(n3 * 100);
                                n3 = n3 / 100;
                                String n4 = Double.toString(n3);
                                TextView euro2 = (TextView) view.findViewById(R.id.euro2);
                                euro2.setText(n4 + " €");
                                sinleft2 = n4;
                                /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(2)---n1<n2---n9<n8",Toast.LENGTH_LONG).show();*/

                                n21 = Double.parseDouble(sinleft);
                                n22 = Double.parseDouble(sinleft2);
                                n23 = Double.parseDouble(sinleft3);
                                n24 = n21 + n22 + n23 + 1;
                                n24 = Math.round(n24 * 100);
                                n24 = n24 / 100;
                                String n25 = Double.toString(n24);


                                TextView sinololefta = (TextView) view.findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");

                            } else {
                                n3 = (n7 - n8) * n11;
                                n3 = Math.round(n3 * 100);
                                n3 = n3 / 100;
                                String n4 = Double.toString(n3);
                                TextView euro2 = (TextView) view.findViewById(R.id.euro2);
                                euro2.setText(n4 + " €");
                                sinleft2 = n4;
                                /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(2)---n1<n2---n9>n8",Toast.LENGTH_LONG).show();*/

                                n21 = Double.parseDouble(sinleft);
                                n22 = Double.parseDouble(sinleft2);
                                n23 = Double.parseDouble(sinleft3);
                                n24 = n21 + n22 + n23 + 1;
                                n24 = Math.round(n24 * 100);
                                n24 = n24 / 100;
                                String n25 = Double.toString(n24);


                                TextView sinololefta = (TextView) view.findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");

                            }
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            } else if (proto.equals("1") && dio.equals("1")) {
                /*Toast.makeText(getApplicationContext(), "proto.equals(1) && dio.equals(1)", Toast.LENGTH_LONG).show();*/
                ArrayAdapter adapter2 = ArrayAdapter.createFromResource(getContext(), R.array.deutera,
                        android.R.layout.simple_spinner_dropdown_item);
                ArrayAdapter adapter3 = ArrayAdapter.createFromResource(getContext(), R.array.trita,
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


                        double n1, n2, n3, n6, n7, n8, n9, n21, n22, n23, n24;
                        n1 = Double.parseDouble(capital);
                        n2 = Double.parseDouble(capital1);
                        n6 = Double.parseDouble(n5);
                        if (n1 >= n2) {
                            if (n1 - n2 <= 20) {
                                n3 = ((n1 - n2) + n2 * n6 / 100) * n11;
                                n3 = Math.round(n3 * 100);
                                n3 = n3 / 100;
                                String n4 = Double.toString(n3);
                                TextView euro2 = (TextView) view.findViewById(R.id.euro2);
                                euro2.setText(n4 + " €");
                                sinleft2 = n4;
                            /*Toast.makeText(getApplicationContext(),"proto.equals(1) && dio.equals(1)---n1>n2",Toast.LENGTH_LONG).show();*/

                                n21 = Double.parseDouble(sinleft);
                                n22 = Double.parseDouble(sinleft2);
                                n23 = Double.parseDouble(sinleft3);
                                n24 = n21 + n22 + n23 + 1;
                                n24 = Math.round(n24 * 100);
                                n24 = n24 / 100;
                                String n25 = Double.toString(n24);


                                TextView sinololefta = (TextView) view.findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");

                            } else {
                                n3 = (20 + n2 * n6 / 100) * n11;
                                n3 = Math.round(n3 * 100);
                                n3 = n3 / 100;
                                String n4 = Double.toString(n3);
                                TextView euro2 = (TextView) view.findViewById(R.id.euro2);
                                euro2.setText(n4 + " €");
                                sinleft2 = n4;
                            /*Toast.makeText(getApplicationContext(),"proto.equals(1) && dio.equals(1)---n1>n2",Toast.LENGTH_LONG).show();*/

                                n21 = Double.parseDouble(sinleft);
                                n22 = Double.parseDouble(sinleft2);
                                n23 = Double.parseDouble(sinleft3);
                                n24 = n21 + n22 + n23 + 1;
                                n24 = Math.round(n24 * 100);
                                n24 = n24 / 100;
                                String n25 = Double.toString(n24);


                                TextView sinololefta = (TextView) view.findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");

                            }

                        } else {

                            n7 = n1 * n6 / 100;
                            n8 = n2 - n1;
                            n9 = n7 / 2;
                            if (n9 <= n8) {
                                n3 = (n7 - n9) * n11;
                                n3 = Math.round(n3 * 100);
                                n3 = n3 / 100;
                                String n4 = Double.toString(n3);
                                TextView euro2 = (TextView) view.findViewById(R.id.euro2);
                                euro2.setText(n4 + " €");
                                sinleft2 = n4;
                                /*Toast.makeText(getApplicationContext(),"proto.equals(1) && dio.equals(1)---n1<n2---n9<n8",Toast.LENGTH_LONG).show();*/

                                n21 = Double.parseDouble(sinleft);
                                n22 = Double.parseDouble(sinleft2);
                                n23 = Double.parseDouble(sinleft3);
                                n24 = n21 + n22 + n23 + 1;
                                n24 = Math.round(n24 * 100);
                                n24 = n24 / 100;
                                String n25 = Double.toString(n24);


                                TextView sinololefta = (TextView) view.findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");

                            } else {
                                n3 = (n7 - n8) * n11;
                                n3 = Math.round(n3 * 100);
                                n3 = n3 / 100;
                                String n4 = Double.toString(n3);
                                TextView euro2 = (TextView) view.findViewById(R.id.euro2);
                                euro2.setText(n4 + " €");
                                sinleft2 = n4;
                                /*Toast.makeText(getApplicationContext(),"proto.equals(1) && dio.equals(1)---n1<n2---n9>n8",Toast.LENGTH_LONG).show();*/

                                n21 = Double.parseDouble(sinleft);
                                n22 = Double.parseDouble(sinleft2);
                                n23 = Double.parseDouble(sinleft3);
                                n24 = n21 + n22 + n23 + 1;
                                n24 = Math.round(n24 * 100);
                                n24 = n24 / 100;
                                String n25 = Double.toString(n24);


                                TextView sinololefta = (TextView) view.findViewById(R.id.sinololefta);
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


        public ItemAutoTextAdapter3(AutoCompleteDbAdapter4 dbHelper) {
            // Call the CursorAdapter constructor with a null Cursor.
            super(getContext(), null);
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


            TextView lianikitimi3 = (TextView) view.findViewById(R.id.lianikitimi3);
            lianikitimi3.setText(String.valueOf(capital) + " €");
            TextView anaforatimi3 = (TextView) view.findViewById(R.id.anaforatimi3);
            anaforatimi3.setText(String.valueOf(capital1) + " €");

            TextView posotita3 = (TextView) view.findViewById(R.id.posotita3);
            Spinner spinner5 = (Spinner) view.findViewById(R.id.spinner5);
            Spinner spinner6 = (Spinner) view.findViewById(R.id.spinner6);
            TextView simetoxi2 = (TextView) view.findViewById(R.id.simetoxi2);
            TextView euro3 = (TextView) view.findViewById(R.id.euro3);

            InputMethodManager in = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            in.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);


            if (proto.equals("2") && dio.equals("1")) {
                /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(1)",Toast.LENGTH_LONG).show();*/
                ArrayAdapter adapter2 = ArrayAdapter.createFromResource(getContext(), R.array.deutera,
                        android.R.layout.simple_spinner_dropdown_item);
                ArrayAdapter adapter3 = ArrayAdapter.createFromResource(getContext(), R.array.trita,
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


                        double n1, n2, n3, n6, n7, n8, n9, n21, n22, n23, n24;
                        n1 = Double.parseDouble(capital);
                        n2 = Double.parseDouble(capital1);
                        n6 = Double.parseDouble(n5);
                        if (n1 >= n2) {
                            if (n1 - n2 <= 20) {
                                n3 = ((n1 - n2) + n2 * n6 / 100) * n11;
                                n3 = Math.round(n3 * 100);
                                n3 = n3 / 100;
                                String n4 = Double.toString(n3);
                                TextView euro3 = (TextView) view.findViewById(R.id.euro3);
                                euro3.setText(n4 + " €");
                                sinleft3 = n4;
                            /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(1)---n1>n2",Toast.LENGTH_LONG).show();*/

                                n21 = Double.parseDouble(sinleft);
                                n22 = Double.parseDouble(sinleft2);
                                n23 = Double.parseDouble(sinleft3);
                                n24 = n21 + n22 + n23 + 1;
                                n24 = Math.round(n24 * 100);
                                n24 = n24 / 100;
                                String n25 = Double.toString(n24);


                                sinololefta = (TextView) view.findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");
                            } else {
                                n3 = (20 + n2 * n6 / 100) * n11;
                                n3 = Math.round(n3 * 100);
                                n3 = n3 / 100;
                                String n4 = Double.toString(n3);
                                TextView euro3 = (TextView) view.findViewById(R.id.euro3);
                                euro3.setText(n4 + " €");
                                sinleft3 = n4;
                            /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(1)---n1>n2",Toast.LENGTH_LONG).show();*/

                                n21 = Double.parseDouble(sinleft);
                                n22 = Double.parseDouble(sinleft2);
                                n23 = Double.parseDouble(sinleft3);
                                n24 = n21 + n22 + n23 + 1;
                                n24 = Math.round(n24 * 100);
                                n24 = n24 / 100;
                                String n25 = Double.toString(n24);


                                TextView sinololefta = (TextView) view.findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");
                            }


                        } else {

                            n7 = n1 * n6 / 100;
                            n8 = n2 - n1;
                            n9 = n7 / 2;
                            if (n9 <= n8) {
                                n3 = (n7 - n9) * n11;
                                n3 = Math.round(n3 * 100);
                                n3 = n3 / 100;
                                String n4 = Double.toString(n3);
                                TextView euro3 = (TextView) view.findViewById(R.id.euro3);
                                euro3.setText(n4 + " €");
                                sinleft3 = n4;
                                /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(1)---n1<n2---n9<n8",Toast.LENGTH_LONG).show();*/

                                n21 = Double.parseDouble(sinleft);
                                n22 = Double.parseDouble(sinleft2);
                                n23 = Double.parseDouble(sinleft3);
                                n24 = n21 + n22 + n23 + 1;
                                n24 = Math.round(n24 * 100);
                                n24 = n24 / 100;
                                String n25 = Double.toString(n24);


                                TextView sinololefta = (TextView) view.findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");

                            } else {
                                n3 = (n7 - n8) * n11;
                                n3 = Math.round(n3 * 100);
                                n3 = n3 / 100;
                                String n4 = Double.toString(n3);
                                TextView euro3 = (TextView) view.findViewById(R.id.euro3);
                                euro3.setText(n4 + " €");
                                sinleft3 = n4;
                                /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(1)---n1<n2---n9>n8",Toast.LENGTH_LONG).show();*/

                                n21 = Double.parseDouble(sinleft);
                                n22 = Double.parseDouble(sinleft2);
                                n23 = Double.parseDouble(sinleft3);
                                n24 = n21 + n22 + n23 + 1;
                                n24 = Math.round(n24 * 100);
                                n24 = n24 / 100;
                                String n25 = Double.toString(n24);


                                TextView sinololefta = (TextView) view.findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");

                            }

                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

            } else if (proto.equals("2") && dio.equals("2")) {
                /*Toast.makeText(getApplicationContext(), "proto.equals(2) && dio.equals(2)", Toast.LENGTH_LONG).show();*/
                ArrayAdapter adapter2 = ArrayAdapter.createFromResource(getContext(), R.array.deutera,
                        android.R.layout.simple_spinner_dropdown_item);
                ArrayAdapter adapter3 = ArrayAdapter.createFromResource(getContext(), R.array.trita,
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


                        double n1, n2, n3, n6, n7, n8, n9, n21, n22, n23, n24;
                        n1 = Double.parseDouble(capital);
                        n2 = Double.parseDouble(capital1);
                        n6 = Double.parseDouble(n5);
                        if (n1 >= n2) {
                            if ((n1 - n2) / 2 <= 20) {
                                n3 = ((n1 - n2) / 2 + n2 * n6 / 100) * n11;
                                n3 = Math.round(n3 * 100);
                                n3 = n3 / 100;
                                String n4 = Double.toString(n3);
                                TextView euro3 = (TextView) view.findViewById(R.id.euro3);
                                euro3.setText(n4 + " €");
                                sinleft3 = n4;
                            /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(2)---n1>n2",Toast.LENGTH_LONG).show();*/

                                n21 = Double.parseDouble(sinleft);
                                n22 = Double.parseDouble(sinleft2);
                                n23 = Double.parseDouble(sinleft3);
                                n24 = n21 + n22 + n23 + 1;
                                n24 = Math.round(n24 * 100);
                                n24 = n24 / 100;
                                String n25 = Double.toString(n24);


                                TextView sinololefta = (TextView) view.findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");
                            } else {
                                n3 = (20 + n2 * n6 / 100) * n11;
                                n3 = Math.round(n3 * 100);
                                n3 = n3 / 100;
                                String n4 = Double.toString(n3);
                                TextView euro3 = (TextView) view.findViewById(R.id.euro3);
                                euro3.setText(n4 + " €");
                                sinleft3 = n4;
                            /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(2)---n1>n2",Toast.LENGTH_LONG).show();*/

                                n21 = Double.parseDouble(sinleft);
                                n22 = Double.parseDouble(sinleft2);
                                n23 = Double.parseDouble(sinleft3);
                                n24 = n21 + n22 + n23 + 1;
                                n24 = Math.round(n24 * 100);
                                n24 = n24 / 100;
                                String n25 = Double.toString(n24);


                                TextView sinololefta = (TextView) view.findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");
                            }


                        } else {

                            n7 = n1 * n6 / 100;
                            n8 = n2 - n1;
                            n9 = n7 / 2;
                            if (n9 <= n8) {
                                n3 = (n7 - n9) * n11;
                                n3 = Math.round(n3 * 100);
                                n3 = n3 / 100;
                                String n4 = Double.toString(n3);
                                TextView euro3 = (TextView) view.findViewById(R.id.euro3);
                                euro3.setText(n4 + " €");
                                sinleft3 = n4;
                                /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(2)---n1<n2---n9<n8",Toast.LENGTH_LONG).show();*/

                                n21 = Double.parseDouble(sinleft);
                                n22 = Double.parseDouble(sinleft2);
                                n23 = Double.parseDouble(sinleft3);
                                n24 = n21 + n22 + n23 + 1;
                                n24 = Math.round(n24 * 100);
                                n24 = n24 / 100;
                                String n25 = Double.toString(n24);


                                TextView sinololefta = (TextView) view.findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");

                            } else {
                                n3 = (n7 - n8) * n11;
                                n3 = Math.round(n3 * 100);
                                n3 = n3 / 100;
                                String n4 = Double.toString(n3);
                                TextView euro3 = (TextView) view.findViewById(R.id.euro3);
                                euro3.setText(n4 + " €");
                                sinleft3 = n4;
                                /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(2)---n1<n2---n9>n8",Toast.LENGTH_LONG).show();*/

                                n21 = Double.parseDouble(sinleft);
                                n22 = Double.parseDouble(sinleft2);
                                n23 = Double.parseDouble(sinleft3);
                                n24 = n21 + n22 + n23 + 1;
                                n24 = Math.round(n24 * 100);
                                n24 = n24 / 100;
                                String n25 = Double.toString(n24);


                                TextView sinololefta = (TextView) view.findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");

                            }
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            } else if (proto.equals("1") && dio.equals("1")) {
                /*Toast.makeText(getApplicationContext(), "proto.equals(1) && dio.equals(1)", Toast.LENGTH_LONG).show();*/
                ArrayAdapter adapter2 = ArrayAdapter.createFromResource(getContext(), R.array.deutera,
                        android.R.layout.simple_spinner_dropdown_item);
                ArrayAdapter adapter3 = ArrayAdapter.createFromResource(getContext(), R.array.trita,
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


                        double n1, n2, n3, n6, n7, n8, n9, n21, n22, n23, n24;
                        n1 = Double.parseDouble(capital);
                        n2 = Double.parseDouble(capital1);
                        n6 = Double.parseDouble(n5);
                        if (n1 >= n2) {
                            if (n1 - n2 <= 20) {
                                n3 = ((n1 - n2) + n2 * n6 / 100) * n11;
                                n3 = Math.round(n3 * 100);
                                n3 = n3 / 100;
                                String n4 = Double.toString(n3);
                                TextView euro3 = (TextView) view.findViewById(R.id.euro3);
                                euro3.setText(n4 + " €");
                                sinleft3 = n4;
                            /*Toast.makeText(getApplicationContext(),"proto.equals(1) && dio.equals(1)---n1>n2",Toast.LENGTH_LONG).show();*/

                                n21 = Double.parseDouble(sinleft);
                                n22 = Double.parseDouble(sinleft2);
                                n23 = Double.parseDouble(sinleft3);
                                n24 = n21 + n22 + n23 + 1;
                                n24 = Math.round(n24 * 100);
                                n24 = n24 / 100;
                                String n25 = Double.toString(n24);


                                TextView sinololefta = (TextView) view.findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");
                            } else {
                                n3 = (20 + n2 * n6 / 100) * n11;
                                n3 = Math.round(n3 * 100);
                                n3 = n3 / 100;
                                String n4 = Double.toString(n3);
                                TextView euro3 = (TextView) view.findViewById(R.id.euro3);
                                euro3.setText(n4 + " €");
                                sinleft3 = n4;
                            /*Toast.makeText(getApplicationContext(),"proto.equals(1) && dio.equals(1)---n1>n2",Toast.LENGTH_LONG).show();*/

                                n21 = Double.parseDouble(sinleft);
                                n22 = Double.parseDouble(sinleft2);
                                n23 = Double.parseDouble(sinleft3);
                                n24 = n21 + n22 + n23 + 1;
                                n24 = Math.round(n24 * 100);
                                n24 = n24 / 100;
                                String n25 = Double.toString(n24);


                                TextView sinololefta = (TextView) view.findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");
                            }


                        } else {

                            n7 = n1 * n6 / 100;
                            n8 = n2 - n1;
                            n9 = n7 / 2;
                            if (n9 <= n8) {
                                n3 = (n7 - n9) * n11;
                                n3 = Math.round(n3 * 100);
                                n3 = n3 / 100;
                                String n4 = Double.toString(n3);
                                TextView euro3 = (TextView) view.findViewById(R.id.euro3);
                                euro3.setText(n4 + " €");
                                sinleft3 = n4;
                                /*Toast.makeText(getApplicationContext(),"proto.equals(1) && dio.equals(1)---n1<n2---n9<n8",Toast.LENGTH_LONG).show();*/

                                n21 = Double.parseDouble(sinleft);
                                n22 = Double.parseDouble(sinleft2);
                                n23 = Double.parseDouble(sinleft3);
                                n24 = n21 + n22 + n23 + 1;
                                n24 = Math.round(n24 * 100);
                                n24 = n24 / 100;
                                String n25 = Double.toString(n24);


                                TextView sinololefta = (TextView) view.findViewById(R.id.sinololefta);
                                sinololefta.setText(n25 + " €");

                            } else {
                                n3 = (n7 - n8) * n11;
                                n3 = Math.round(n3 * 100);
                                n3 = n3 / 100;
                                String n4 = Double.toString(n3);
                                TextView euro3 = (TextView) view.findViewById(R.id.euro3);
                                euro3.setText(n4 + " €");
                                sinleft3 = n4;
                                /*Toast.makeText(getApplicationContext(),"proto.equals(1) && dio.equals(1)---n1<n2---n9>n8",Toast.LENGTH_LONG).show();*/

                                n21 = Double.parseDouble(sinleft);
                                n22 = Double.parseDouble(sinleft2);
                                n23 = Double.parseDouble(sinleft3);
                                n24 = n21 + n22 + n23 + 1;
                                n24 = Math.round(n24 * 100);
                                n24 = n24 / 100;
                                String n25 = Double.toString(n24);


                                TextView sinololefta = (TextView) view.findViewById(R.id.sinololefta);
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
            super(getContext(), null);
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
            Desciptionperigrafi.setText(String.valueOf(capital1) + " €");

            InputMethodManager in = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            in.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);


            if (proto.equals("2") && dio.equals("1")) {
                /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(1)",Toast.LENGTH_LONG).show();*/
                ArrayAdapter adapter2 = ArrayAdapter.createFromResource(getContext(), R.array.deutera,
                        android.R.layout.simple_spinner_dropdown_item);

                ArrayAdapter adapter3 = ArrayAdapter.createFromResource(getContext(), R.array.trita,
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


                        double n1, n2, n3, n6, n7, n8, n9, n21, n22, n23, n24;
                        n1 = Double.parseDouble(capital);
                        n2 = Double.parseDouble(capital1);
                        n6 = Double.parseDouble(n5);
                        if (n1 >= n2) {
                            if (n1 - n2 <= 20) {
                                n3 = ((n1 - n2) + n2 * n6 / 100) * n11;
                                n3 = Math.round(n3 * 100);
                                n3 = n3 / 100;
                                String n4 = Double.toString(n3);
                                euro$.setText(n4 + " €");
                                sinleft = n4;
                            /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(1)---n1>n2",Toast.LENGTH_LONG).show();*/

                                n21 = Double.parseDouble(sinleft);
                                n22 = Double.parseDouble(sinleft2);
                                n23 = Double.parseDouble(sinleft3);
                                n24 = n21 + n22 + n23 + 1;
                                n24 = Math.round(n24 * 100);
                                n24 = n24 / 100;
                                String n25 = Double.toString(n24);

                                sinololefta.setText(n25 + " €");
                            } else {
                                n3 = (20 + n2 * n6 / 100) * n11;
                                n3 = Math.round(n3 * 100);
                                n3 = n3 / 100;
                                String n4 = Double.toString(n3);
                                euro$.setText(n4 + " €");
                                sinleft = n4;
                            /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(1)---n1>n2",Toast.LENGTH_LONG).show();*/

                                n21 = Double.parseDouble(sinleft);
                                n22 = Double.parseDouble(sinleft2);
                                n23 = Double.parseDouble(sinleft3);
                                n24 = n21 + n22 + n23 + 1;
                                n24 = Math.round(n24 * 100);
                                n24 = n24 / 100;
                                String n25 = Double.toString(n24);

                                sinololefta.setText(n25 + " €");
                            }


                        } else {

                            n7 = n1 * n6 / 100;
                            n8 = n2 - n1;
                            n9 = n7 / 2;
                            if (n9 <= n8) {
                                n3 = (n7 - n9) * n11;
                                n3 = Math.round(n3 * 100);
                                n3 = n3 / 100;
                                String n4 = Double.toString(n3);
                                euro$.setText(n4 + " €");
                                sinleft = n4;
                                /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(1)---n1<n2---n9<n8",Toast.LENGTH_LONG).show();*/

                                n21 = Double.parseDouble(sinleft);
                                n22 = Double.parseDouble(sinleft2);
                                n23 = Double.parseDouble(sinleft3);
                                n24 = n21 + n22 + n23 + 1;
                                n24 = Math.round(n24 * 100);
                                n24 = n24 / 100;
                                String n25 = Double.toString(n24);

                                sinololefta.setText(n25 + " €");

                            } else {
                                n3 = (n7 - n8) * n11;
                                n3 = Math.round(n3 * 100);
                                n3 = n3 / 100;
                                String n4 = Double.toString(n3);
                                euro$.setText(n4 + " €");
                                sinleft = n4;
                                /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(1)---n1<n2---n9>n8",Toast.LENGTH_LONG).show();*/

                                n21 = Double.parseDouble(sinleft);
                                n22 = Double.parseDouble(sinleft2);
                                n23 = Double.parseDouble(sinleft3);
                                n24 = n21 + n22 + n23 + 1;
                                n24 = Math.round(n24 * 100);
                                n24 = n24 / 100;
                                String n25 = Double.toString(n24);

                                sinololefta.setText(n25 + " €");

                            }


                        }


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

            } else if (proto.equals("2") && dio.equals("2")) {
                /*Toast.makeText(getApplicationContext(), "proto.equals(2) && dio.equals(2)", Toast.LENGTH_LONG).show();*/
                ArrayAdapter adapter2 = ArrayAdapter.createFromResource(getContext(), R.array.deutera,
                        android.R.layout.simple_spinner_dropdown_item);
                ArrayAdapter adapter3 = ArrayAdapter.createFromResource(getContext(), R.array.trita,
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

                        double n1, n2, n3, n6, n7, n8, n9, n21, n22, n23, n24;
                        n1 = Double.parseDouble(capital);
                        n2 = Double.parseDouble(capital1);
                        n6 = Double.parseDouble(n5);
                        if (n1 >= n2) {
                            if ((n1 - n2) / 2 <= 20) {
                                n3 = ((n1 - n2) / 2 + n2 * n6 / 100) * n11;
                                n3 = Math.round(n3 * 100);
                                n3 = n3 / 100;
                                String n4 = Double.toString(n3);
                                euro$.setText(n4 + " €");
                                sinleft = n4;
                            /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(2)---n1>n2",Toast.LENGTH_LONG).show();*/

                                n21 = Double.parseDouble(sinleft);
                                n22 = Double.parseDouble(sinleft2);
                                n23 = Double.parseDouble(sinleft3);
                                n24 = n21 + n22 + n23 + 1;
                                n24 = Math.round(n24 * 100);
                                n24 = n24 / 100;
                                String n25 = Double.toString(n24);

                                sinololefta.setText(n25 + " €");
                            } else {
                                n3 = (20 + n2 * n6 / 100) * n11;
                                n3 = Math.round(n3 * 100);
                                n3 = n3 / 100;
                                String n4 = Double.toString(n3);
                                euro$.setText(n4 + " €");
                                sinleft = n4;
                            /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(2)---n1>n2",Toast.LENGTH_LONG).show();*/

                                n21 = Double.parseDouble(sinleft);
                                n22 = Double.parseDouble(sinleft2);
                                n23 = Double.parseDouble(sinleft3);
                                n24 = n21 + n22 + n23 + 1;
                                n24 = Math.round(n24 * 100);
                                n24 = n24 / 100;
                                String n25 = Double.toString(n24);

                                sinololefta.setText(n25 + " €");
                            }


                        } else {

                            n7 = n1 * n6 / 100;
                            n8 = n2 - n1;
                            n9 = n7 / 2;
                            if (n9 <= n8) {
                                n3 = (n7 - n9) * n11;
                                n3 = Math.round(n3 * 100);
                                n3 = n3 / 100;
                                String n4 = Double.toString(n3);
                                euro$.setText(n4 + " €");
                                sinleft = n4;
                                /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(2)---n1<n2---n9<n8",Toast.LENGTH_LONG).show();*/

                                n21 = Double.parseDouble(sinleft);
                                n22 = Double.parseDouble(sinleft2);
                                n23 = Double.parseDouble(sinleft3);
                                n24 = n21 + n22 + n23 + 1;
                                n24 = Math.round(n24 * 100);
                                n24 = n24 / 100;
                                String n25 = Double.toString(n24);

                                sinololefta.setText(n25 + " €");

                            } else {
                                n3 = (n7 - n8) * n11;
                                n3 = Math.round(n3 * 100);
                                n3 = n3 / 100;
                                String n4 = Double.toString(n3);
                                euro$.setText(n4 + " €");
                                sinleft = n4;
                                /*Toast.makeText(getApplicationContext(),"proto.equals(2) && dio.equals(2)---n1<n2---n9>n8",Toast.LENGTH_LONG).show();*/

                                n21 = Double.parseDouble(sinleft);
                                n22 = Double.parseDouble(sinleft2);
                                n23 = Double.parseDouble(sinleft3);
                                n24 = n21 + n22 + n23 + 1;
                                n24 = Math.round(n24 * 100);
                                n24 = n24 / 100;
                                String n25 = Double.toString(n24);

                                sinololefta.setText(n25 + " €");

                            }


                        }


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            } else if (proto.equals("1") && dio.equals("1")) {
                /*Toast.makeText(getApplicationContext(), "proto.equals(1) && dio.equals(1)", Toast.LENGTH_LONG).show();*/
                ArrayAdapter adapter2 = ArrayAdapter.createFromResource(getContext(), R.array.deutera,
                        android.R.layout.simple_spinner_dropdown_item);
                ArrayAdapter adapter3 = ArrayAdapter.createFromResource(getContext(), R.array.trita,
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


                        double n1, n2, n3, n6, n7, n8, n9, n21, n22, n23, n24;
                        n1 = Double.parseDouble(capital);
                        n2 = Double.parseDouble(capital1);
                        n6 = Double.parseDouble(n5);
                        if (n1 >= n2) {
                            if (n1 - n2 <= 20) {
                                n3 = ((n1 - n2) + n2 * n6 / 100) * n11;
                                n3 = Math.round(n3 * 100);
                                n3 = n3 / 100;
                                String n4 = Double.toString(n3);
                                euro$.setText(n4 + " €");
                                sinleft = n4;
                            /*Toast.makeText(getApplicationContext(),"proto.equals(1) && dio.equals(1)---n1>n2",Toast.LENGTH_LONG).show();*/

                                n21 = Double.parseDouble(sinleft);
                                n22 = Double.parseDouble(sinleft2);
                                n23 = Double.parseDouble(sinleft3);
                                n24 = n21 + n22 + n23 + 1;
                                n24 = Math.round(n24 * 100);
                                n24 = n24 / 100;
                                String n25 = Double.toString(n24);

                                sinololefta.setText(n25 + " €");
                            } else {
                                n3 = (20 + n2 * n6 / 100) * n11;
                                n3 = Math.round(n3 * 100);
                                n3 = n3 / 100;
                                String n4 = Double.toString(n3);

                                euro$.setText(n4 + " €");
                                sinleft = n4;
                            /*Toast.makeText(getApplicationContext(),"proto.equals(1) && dio.equals(1)---n1>n2",Toast.LENGTH_LONG).show();*/

                                n21 = Double.parseDouble(sinleft);
                                n22 = Double.parseDouble(sinleft2);
                                n23 = Double.parseDouble(sinleft3);
                                n24 = n21 + n22 + n23 + 1;
                                n24 = Math.round(n24 * 100);
                                n24 = n24 / 100;
                                String n25 = Double.toString(n24);

                                sinololefta.setText(n25 + " €");
                            }


                        } else {

                            n7 = n1 * n6 / 100;
                            n8 = n2 - n1;
                            n9 = n7 / 2;
                            if (n9 <= n8) {
                                n3 = (n7 - n9) * n11;
                                n3 = Math.round(n3 * 100);
                                n3 = n3 / 100;
                                String n4 = Double.toString(n3);
                                euro$.setText(n4 + " €");
                                sinleft = n4;
                                /*Toast.makeText(getApplicationContext(),"proto.equals(1) && dio.equals(1)---n1<n2---n9<n8",Toast.LENGTH_LONG).show();*/

                                n21 = Double.parseDouble(sinleft);
                                n22 = Double.parseDouble(sinleft2);
                                n23 = Double.parseDouble(sinleft3);
                                n24 = n21 + n22 + n23 + 1;
                                n24 = Math.round(n24 * 100);
                                n24 = n24 / 100;
                                String n25 = Double.toString(n24);

                                sinololefta.setText(n25 + " €");

                            } else {
                                n3 = (n7 - n8) * n11;
                                n3 = Math.round(n3 * 100);
                                n3 = n3 / 100;
                                String n4 = Double.toString(n3);
                                euro$.setText(n4 + " €");
                                sinleft = n4;
                                /*Toast.makeText(getApplicationContext(),"proto.equals(1) && dio.equals(1)---n1<n2---n9>n8",Toast.LENGTH_LONG).show();*/

                                n21 = Double.parseDouble(sinleft);
                                n22 = Double.parseDouble(sinleft2);
                                n23 = Double.parseDouble(sinleft3);
                                n24 = n21 + n22 + n23 + 1;
                                n24 = Math.round(n24 * 100);
                                n24 = n24 / 100;
                                String n25 = Double.toString(n24);

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


}
