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
    public static final String OUR_TEAM_MEMBERS_PICTURE = "teamMembersPicture";
    public static final String OUR_TEAM_MEMBERS_BIO = "teamMembersBio";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String name = getArguments().getString(OUR_TEAM_MEMBERS);
        String bio = getArguments().getString(OUR_TEAM_MEMBERS_BIO);
        String teamMembersPicture = getArguments().getString(OUR_TEAM_MEMBERS_PICTURE);
        String imageUri = "drawable://" + R.drawable.team_topher; // from drawables (only images, non-9patch)

        View v = inflater.inflate(R.layout.our_team_fragment, container, false);

        TextView messageTextView = (TextView) v.findViewById(R.id.employeeNameView);
        messageTextView.setText(name);

        TextView bioTextView = (TextView) v.findViewById(R.id.teamBio);
        bioTextView.setText(bio);


        // Set up our image caching
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .build();


        // Create a reference to our ImageView
        ImageView imgView = (ImageView) v.findViewById(R.id.teamImageView);
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(v.getContext())); // Initialize the ImageLoader
        imageLoader.displayImage(teamMembersPicture, imgView, options);  // Load the image into our ImageView

        return v;
    }
}