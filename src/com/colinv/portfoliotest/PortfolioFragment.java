package com.colinv.portfoliotest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PortfolioFragment extends Fragment {
    final static String ARG_POSITION = "position";
    public static final String PORTFOLIO_NAME = "portfolioName";

    private String name;

    public PortfolioFragment() {
        // empty
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String name = getArguments().getString(PORTFOLIO_NAME);
        this.name = name;
        View v = inflater.inflate(R.layout.portfolio_fragment, container, false);
        TextView messageTextView = (TextView) v.findViewById(R.id.portfolioNameView);
        messageTextView.setText(name);
        return v;
    }
}
