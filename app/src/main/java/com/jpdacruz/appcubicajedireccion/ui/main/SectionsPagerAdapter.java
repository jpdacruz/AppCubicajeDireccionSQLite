package com.jpdacruz.appcubicajedireccion.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.jpdacruz.appcubicajedireccion.R;
import com.jpdacruz.appcubicajedireccion.fragments.Lista_Celdas_Fragment;
import com.jpdacruz.appcubicajedireccion.fragments.Lista_Resumen_Fragment;
import com.jpdacruz.appcubicajedireccion.fragments.Lista_silos_Fragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]
            {R.string.tab_text_1,R.string.tab_text_2,R.string.tab_text_3};

    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){

            case 0:
                Lista_silos_Fragment lista_silos_fragment = new Lista_silos_Fragment();
                return  lista_silos_fragment;
            case 1:
                Lista_Celdas_Fragment lista_celdas_fragment = new Lista_Celdas_Fragment();
                return lista_celdas_fragment;
            case 2:
                Lista_Resumen_Fragment lista_resumen_fragment = new Lista_Resumen_Fragment();
                return lista_resumen_fragment;
}
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 3;
    }
}