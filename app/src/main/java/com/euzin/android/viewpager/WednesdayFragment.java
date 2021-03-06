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
package com.euzin.android.viewpager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class WednesdayFragment extends Fragment {

    private View mFragmentView;
    private ImageButton mButtonGenikes;
    private static final String NUMBER_OF_RECEIVER_NEXT = "nextPage";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mFragmentView=inflater.inflate(R.layout.fragment_erotiseis_ygeia, container, false);

        mButtonGenikes = (ImageButton)mFragmentView.findViewById(R.id.buttonGenikes);
        mButtonGenikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toSendSignal();
            }
        });

        return mFragmentView;
    }

    private void toSendSignal() {
        Intent intent = new Intent();
        intent.setAction(NUMBER_OF_RECEIVER_NEXT);
        getActivity().sendBroadcast(intent);

    }

}
