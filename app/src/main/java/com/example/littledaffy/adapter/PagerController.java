package com.example.littledaffy.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.littledaffy.fragments.AdoptadosFragment;
import com.example.littledaffy.fragments.FragmentEncontrados;

public class PagerController extends FragmentPagerAdapter {
    int numoftabs;

    public PagerController(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.numoftabs = behavior;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FragmentEncontrados();
            case 1:
                return new AdoptadosFragment();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return numoftabs;
    }
}

