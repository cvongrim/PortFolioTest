package com.colinv.portfoliotest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;


public class PortFolioListAdapter extends BaseAdapter {
        private Context context;
        ArrayList<ArrayList> mobileValues;

        public PortFolioListAdapter(Context context, ArrayList<ArrayList> mobileValues) {
            this.context = context;
            this.mobileValues = mobileValues;
        }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            //gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.portfolio_listview_item, null);

            // set value into TextView
            TextView textView = (TextView) gridView
                    .findViewById(R.id.grid_item_label);

            ArrayList<String> portArray = mobileValues.get(position);
            textView.setText(portArray.get(0));


            String portfolioImageUrl = portArray.get(2);
            // set image based on selected text

            // Turn on Image Caching
            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .cacheInMemory(true)
                    .cacheOnDisc(true)
                    .build();


            ImageView imgView = (ImageView) gridView.findViewById(R.id.grid_item_image); // Grab Image View
            ImageLoader imageLoader = ImageLoader.getInstance();     // Create Image Loader
            imageLoader.init(ImageLoaderConfiguration.createDefault(context));  // Initialize Loader
            imageLoader.getInstance().displayImage(portfolioImageUrl, imgView, options);  // Load from the URL into the view

        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }


    @Override
    public int getCount() {
        return mobileValues.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
