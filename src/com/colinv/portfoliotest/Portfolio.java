package com.colinv.portfoliotest;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Portfolio extends Fragment {
    public ViewPager pager;
    List<Fragment> frags = new ArrayList<Fragment>();

    public String readJSONFeed(String URL) {
        StringBuilder stringBuilder = new StringBuilder();
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(URL);
        try {
            HttpResponse response = httpClient.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                InputStream inputStream = entity.getContent();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                inputStream.close();
            } else {
                Log.d("JSON", "Failed to download file");
            }
        } catch (Exception e) {
            Log.d("readJSONFeed", e.getLocalizedMessage());
        }
        return stringBuilder.toString();
    }

    private class ReadPortfolioJSONFeedTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... urls) {
            return readJSONFeed(urls[0]);
        }

        protected void onPostExecute(String result) {
            try {

                JSONObject jsonObject = new JSONObject(result);  // Create a JSON Object
                JSONArray posts = jsonObject.getJSONArray("posts"); // Create a JSONArray of all the post


                    JSONObject post = posts.getJSONObject(0); // Get a JSONObject for the post

                    JSONObject meta = post.getJSONObject("meta");  // Get the JSONOBject of all the meta data

                    JSONArray portfolioImages =  meta.getJSONArray("portfolio_images");

                    String portfolioTitle =  post.getString("title");
                    String portfolioExcerpt    =  post.getString("excerpt");
                    String portfolioChallenge  =  meta.getString("challenge");
                    String portfolioSolution   =  meta.getString("solution");

                    TextView portfolioTitleTextView = (TextView) getActivity().findViewById(R.id.portfolioTitleTextView);
                    TextView portfolioExcerptTextView = (TextView) getActivity().findViewById(R.id.portfolioDescriptionTextView);
                    TextView portfolioChallengeTextView = (TextView) getActivity().findViewById(R.id.portfolioChallengeTextView);
                    TextView portfolioSolutionTextView = (TextView) getActivity().findViewById(R.id.portfolioSolutionTextView);

                    portfolioTitleTextView.setText(portfolioTitle);
                    portfolioExcerptTextView.setText(portfolioExcerpt);
                    portfolioChallengeTextView.setText(portfolioChallenge);
                    portfolioSolutionTextView.setText(portfolioSolution);

                    for (int j = 0; j < portfolioImages.length(); j++) {
                        JSONObject portfolioImageObject = portfolioImages.getJSONObject(j);
                        String portfolioImageURL =  portfolioImageObject.getString("url");

                        // Add that ArrayList to our portFolioArrayList that will create
                        // our DynamicView
                        frags.add(prepareFragment(portfolioTitle,portfolioImageURL));
                    }


                PortfolioPageAdapter pageAdapter = new PortfolioPageAdapter(getChildFragmentManager(), frags);

                pager = (ViewPager) getActivity().findViewById(R.id.viewpager);
                pager.setAdapter(pageAdapter);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.portfolio, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String imageResourceUrl = getArguments().getString("url");
        if(imageResourceUrl != null){
           new ReadPortfolioJSONFeedTask().execute(imageResourceUrl+"/?feed=json");
        }else{

            new ReadPortfolioJSONFeedTask().execute("http://www.xiik.com/portfolio/simon-shopping-destinations/json/");
        }

    }


    /**
     * Create and set up one PortfolioFragment.
     * @param name Portfolio name.
     */
    Fragment prepareFragment(String name, String url) {
        PortfolioFragment cf = new PortfolioFragment();
        Bundle args = new Bundle();
        args.putString(PortfolioFragment.PORTFOLIO_NAME, name);
        args.putString(PortfolioFragment.PORTFOLIO_URL, url);
        cf.setArguments(args);
        return cf;
    }




    public void hideMenu(View view){
        TextView aboutProjectTextView = (TextView) view.findViewById(R.id.aboutProjectTextView);

        if(aboutProjectTextView .getVisibility() == View.VISIBLE)
            aboutProjectTextView .setVisibility(View.GONE);

    }
}