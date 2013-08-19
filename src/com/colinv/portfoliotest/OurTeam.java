package com.colinv.portfoliotest;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class OurTeam extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.our_team, container, false);




    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Fragment> fragments = getFragments();
        OurTeamPageAdapter pageAdapter = new OurTeamPageAdapter(getChildFragmentManager(), fragments);

        ViewPager pager = (ViewPager) view.findViewById(R.id.viewpager);
        pager.setAdapter(pageAdapter);
    }


    private List<Fragment> getFragments() {
        List<Fragment> frags = new ArrayList<Fragment>();
        frags.add(prepareFragment("Topher"));
        frags.add(prepareFragment("Matt"));
        frags.add(prepareFragment("Colin"));
        frags.add(prepareFragment("Kelly"));
        frags.add(prepareFragment("Tony"));
        frags.add(prepareFragment("Jason"));
        return frags;
    }

    /**
     * Create and set up one CountryFragment.
     * @param name Country name.
     */
    Fragment prepareFragment(String name) {
        OurTeamFragment cf = new OurTeamFragment();
        Bundle args = new Bundle();
        args.putString(OurTeamFragment.COUNTRY_NAME, name);
        cf.setArguments(args);
        return cf;
    }
}
