package com.example.android.viewpager;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ThursdayFragment extends Fragment {

    private TextView imerominia;
    private View mThursdayFragmentView;
    private ImageButton mButtonApostoli;
    private ImageButton mButttonBack;
    private static final String NUMBER_OF_RECEIVER = "pdfCreating";
    private static final String NUMBER_OF_RECEIVER_MIDDLE_BACK = "middleBack";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mThursdayFragmentView = inflater.inflate(R.layout.fragment_erotiseis_prosopikes, container, false);
        imerominia = (TextView) mThursdayFragmentView.findViewById(R.id.imerominiaTextDisplay);
        mButtonApostoli = (ImageButton) mThursdayFragmentView.findViewById(R.id.apostoli);
        mButttonBack = (ImageButton) mThursdayFragmentView.findViewById(R.id.backTooMain);
        mButtonApostoli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toSendSignal();
            }
        });
        mButttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toMiddleSignal();
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

    private void toMiddleSignal() {
        Intent intent = new Intent();
        intent.setAction(NUMBER_OF_RECEIVER_MIDDLE_BACK);
        getActivity().sendBroadcast(intent);

    }
}