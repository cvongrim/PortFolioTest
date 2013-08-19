package com.colinv.portfoliotest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class OurTeamFragment extends Fragment {

    public static final String COUNTRY_NAME = "countryName";

    private String name;

    public OurTeamFragment() {
        // emtpy
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String name = getArguments().getString(COUNTRY_NAME);
        this.name = name;
        View v = inflater.inflate(R.layout.our_team_fragment, container, false);
        TextView messageTextView = (TextView) v.findViewById(R.id.employeeNameView);
        messageTextView.setText(name);
        return v;
    }
}