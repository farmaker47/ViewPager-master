package com.example.android.viewpager;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by farmaker1 on 28/12/2017.
 */

public class ServiceForPdf extends IntentService {

    private MainActivity mMain= new MainActivity();

    public ServiceForPdf() {
        super("ServiceForPdf");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        /*mMain.everythingToPdf();*/
    }
}
