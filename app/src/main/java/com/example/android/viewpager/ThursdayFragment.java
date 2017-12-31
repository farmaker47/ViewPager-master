package com.example.android.viewpager;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by farmaker1 on 31/12/2017.
 */

public class ThursdayFragment extends Fragment {

    private TextView imerominia;
    private View mThursdayFragmentView;
    private Button mButtonApostoli;
    private static final String NUMBER_OF_RECEIVER = "pdfCreating";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mThursdayFragmentView = inflater.inflate(R.layout.fragment_erotiseis_prosopikes, container, false);
        imerominia = (TextView) mThursdayFragmentView.findViewById(R.id.imerominiaTextDisplay);
        mButtonApostoli = (Button) mThursdayFragmentView.findViewById(R.id.apostoli);
        mButtonApostoli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toSendSignal();
            }
        });

        imerominia.setText(getTheDateTime());
        return mThursdayFragmentView;
    }

    private String getTheDateTime() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String date = df.format(Calendar.getInstance().getTime());
        return date;
    }

    private void toSendSignal() {
        Intent intent = new Intent();
        intent.setAction(NUMBER_OF_RECEIVER);
        getActivity().sendBroadcast(intent);

    }
}
