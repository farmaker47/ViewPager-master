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

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Provides the appropriate {@link Fragment} for a view pager.
 */
public class SimpleFragmentPagerAdapter extends FragmentStatePagerAdapter {
    private String[] tabTitles = new String[]{"Με Όνομα", "Με Δραστική", "Με Barcode"};

    public SimpleFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new MondayFragment();
        } else if (position == 1) {
            return new TuesdayFragment();
        } else if (position == 2) {
            return new WednesdayFragment();
        } else {
            return null;
        }
    }

    /*@Override
    public Object instantiateItem(ViewGroup container, int position) {

        View layout = (View) LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_wednesday,null);

        return layout;
    }*/

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return tabTitles[position];

       /* SpannableString sb = new SpannableString("   " + tabTitles[position]);
        sb.setSpan(null, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
*/
    }
}
