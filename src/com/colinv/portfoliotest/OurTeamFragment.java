package com.colinv.portfoliotest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class OurTeamFragment extends Fragment {

    public static final String OUR_TEAM_MEMBERS = "teamMembers";
    public static final String OUR_TEAM_MEMBERS_JOB_TITLE = "teamMembersJobTitle";
    public static final String OUR_TEAM_MEMBERS_PICTURE = "teamMembersPicture";
    public static final String OUR_TEAM_MEMBERS_BIO = "teamMembersBio";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String name = getArguments().getString(OUR_TEAM_MEMBERS);
        String jobTitle = getArguments().getString(OUR_TEAM_MEMBERS_JOB_TITLE);
        String bio = getArguments().getString(OUR_TEAM_MEMBERS_BIO);
        String teamMembersPicture = getArguments().getString(OUR_TEAM_MEMBERS_PICTURE);

        View v = inflater.inflate(R.layout.our_team_fragment, container, false);

        TextView employeeJobTitleTextView = (TextView) v.findViewById(R.id.employeeJobTitleTextView);
        employeeJobTitleTextView.setText(jobTitle);

        TextView messageTextView = (TextView) v.findViewById(R.id.employeeNameTextView);
        messageTextView.setText(name);

        TextView bioTextView = (TextView) v.findViewById(R.id.teamBio);
        bioTextView.setText(bio);



        // Create a reference to our ImageView
        ImageView imgView = (ImageView) v.findViewById(R.id.teamImageView);
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(v.getContext())); // Initialize the ImageLoader
        imageLoader.displayImage(teamMembersPicture, imgView);  // Load the image into our ImageView

        return v;
    }
}