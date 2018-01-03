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

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Fragment that displays "Tuesday".
 */
public class TuesdayFragment extends Fragment {

    private ImageButton mButtonGenikes;
    private View mFragmentGenikes;
    private static final String NUMBER_OF_RECEIVER_NEXT = "nextPage";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mFragmentGenikes=inflater.inflate(R.layout.fragment_erotiseis_genikes, container, false);
        mButtonGenikes = (ImageButton)mFragmentGenikes.findViewById(R.id.buttonGenikes);
        mButtonGenikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toSendSignal();
            }
        });


        return mFragmentGenikes;
    }

    private void toSendSignal() {
        Intent intent = new Intent();
        intent.setAction(NUMBER_OF_RECEIVER_NEXT);
        getActivity().sendBroadcast(intent);

    }
}
