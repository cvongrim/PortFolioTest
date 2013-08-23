package com.colinv.portfoliotest;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Portfolio extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.portfolio, container, false);




    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Fragment> fragments = getFragments();
        PortfolioPageAdapter pageAdapter = new PortfolioPageAdapter(getChildFragmentManager(), fragments);

        ViewPager pager = (ViewPager) view.findViewById(R.id.viewpager);
        pager.setAdapter(pageAdapter);
    }


    private List<Fragment> getFragments() {
        List<Fragment> frags = new ArrayList<Fragment>();
        frags.add(prepareFragment("Simon"));
        frags.add(prepareFragment("IMPA"));
        frags.add(prepareFragment("EcoPath"));
        frags.add(prepareFragment("Blue Beard"));
        frags.add(prepareFragment("Kilroys"));
        frags.add(prepareFragment("Prairie Godmothers"));
        return frags;
    }

    /**
     * Create and set up one CountryFragment.
     * @param name Country name.
     */
    Fragment prepareFragment(String name) {
        PortfolioFragment cf = new PortfolioFragment();
        Bundle args = new Bundle();
        args.putString(PortfolioFragment.COUNTRY_NAME, name);
        cf.setArguments(args);
        return cf;
    }
}