package com.colinv.portfoliotest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class OurTeamFragment extends Fragment {

    public static final String OUR_TEAM_MEMBERS = "teamMembers";

    private String name;

    public OurTeamFragment() {
        // empty
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String name = getArguments().getString(OUR_TEAM_MEMBERS);
        this.name = name;
        View v = inflater.inflate(R.layout.our_team_fragment, container, false);
        TextView messageTextView = (TextView) v.findViewById(R.id.employeeNameView);
        messageTextView.setText(name);
        return v;
    }
}