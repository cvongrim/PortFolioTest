package com.colinv.portfoliotest;

import android.app.Activity;
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

public class PortfolioFragment extends Fragment {
    final static String ARG_POSITION = "position";
    final static String ARG_URL = "url";
    public static final String PORTFOLIO_NAME = "portfolioName";
    public static final String PORTFOLIO_URL = "portfolioURL";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String name = getArguments().getString(PORTFOLIO_NAME);
        String url = getArguments().getString(PORTFOLIO_URL);

        View v = inflater.inflate(R.layout.portfolio_fragment, container, false);
        TextView messageTextView = (TextView) v.findViewById(R.id.portfolioNameView);
        messageTextView.setText(name);



        // Turn on Image Caching
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .build();

        Activity thisActivity = getActivity();

        ImageView imgView = (ImageView) v.findViewById(R.id.portfolioImageView); // Grab Image View
        ImageLoader imageLoader = ImageLoader.getInstance();     // Create Image Loader
        imageLoader.init(ImageLoaderConfiguration.createDefault(thisActivity));  // Initialize Loader
        imageLoader.getInstance().displayImage(url, imgView, options);  // Load from the URL into the view

        return v;
    }
}
