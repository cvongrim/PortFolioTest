package com.colinv.portfoliotest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PortfolioFragment extends Fragment {

    public static final String COUNTRY_NAME = "countryName";

    private String name;

    public PortfolioFragment() {
        // emtpy
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String name = getArguments().getString(COUNTRY_NAME);
        this.name = name;
        View v = inflater.inflate(R.layout.portfolio_fragment, container, false);
        TextView messageTextView = (TextView) v.findViewById(R.id.portfolioNameView);
        messageTextView.setText(name);
        return v;
    }
}
