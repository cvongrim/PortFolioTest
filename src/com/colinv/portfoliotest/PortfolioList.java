package com.colinv.portfoliotest;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.lucasr.twowayview.TwoWayView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class PortfolioList extends Fragment {
    private static final String LOGTAG = "TwoWayViewSample";
    public TwoWayView mListView;

    // ArrayList that we will use to populate our gridView
    ArrayList<ArrayList> portfolioArrayList = new ArrayList<ArrayList>();

    OnHeadlineSelectedListener mCallback;

    // Container Activity must implement this interface
    public interface OnHeadlineSelectedListener {
        public void onArticleSelected(int position);
    }

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

            /*
            //---sample result---
                {
                   "title":" Page title",
                   "url":"http:\/\/url.com\/",
                   "description":"Page Description",
                   "lastBuildDate":"Tue, 04 Jun 2013 20:36:56 +0000",
                   "language":"en-US",
                   "updatePerson":"hourly",
                   "updateFrequency":"1",
                   "post_count":10,
                   "posts":[
                       {
                       "ID":123,
                       "title":"The Post Title",
                       "permalink":"http:www.url.com",
                       "publishDate":"Wed, 31 Oct 2012 18:07:49 +0000"
                       ,"author":"John Doe",
                       "category":["233","307","340"],
                       "guid":"http:www.url.com",
                       "excerpt":"Brief Post Description.",
                       "content":"Lorem Ipsum",
                       "modifiedDate":"April 29, 2013",
                       "status":"publish",
                       "tags":0,"menu_order":16,
                       "meta":{"wpcf-gallery":"wpcf-gallery",
                                "header_font_color":"header_font_color",
                                "header_image":"header_image",
                                "header_background":"header_background",
                                "external_link":"external_link",
                                "challenge":"challenge",
                                "solution":"solution",
                                "quote":"quote",
                                "project":"project",
                                "b&w_work_thumbnail":"b&w_work_thumbnail",
                                "color_work_thumbnail":"color_work_thumbnail",
                                "project_description":"project_description",
                                "portfolio_images":[
                                                    {"url":"http:\/\/xiik.com\/responsive\/wp-content\/uploads\/2012\/10\/BoomingHadley_1.jpg","type":"Web"},
                                                    {"url":"http:\/\/xiik.com\/responsive\/wp-content\/uploads\/2012\/10\/BoomingHadley_2.jpg","type":"Web"},
                                                    {"url":"http:\/\/xiik.com\/responsive\/wp-content\/uploads\/2012\/10\/BoomingHadley_3.jpg","type":"Web"},
                                                    {"url":"http:\/\/xiik.com\/responsive\/wp-content\/uploads\/2012\/10\/BoomingHadley_4.jpg","type":"Web"},
                                                    {"url":"http:\/\/xiik.com\/responsive\/wp-content\/uploads\/2012\/10\/BoomingHadley_5.jpg","type":"Web"}]
                                                    },
                                "featured_image":""}
                    },


                 }

                */


                JSONObject jsonObject = new JSONObject(result);  // Create a JSON Object
                JSONArray posts = jsonObject.getJSONArray("posts"); // Create a JSONArray of all the post

                for (int i = 0; i < posts.length(); i++) {

                    JSONObject post = posts.getJSONObject(i); // Get a JSONObject for the post

                    JSONObject meta = post.getJSONObject("meta");  // Get the JSONOBject of all the meta data
                    JSONArray portfolio_images = meta.getJSONArray("portfolio_images"); // Get the JSONArray of all the Images
                    JSONObject portfolioImage =  portfolio_images.getJSONObject(0);  // Get the url to the first image

                    // Now We are going to create an Array List to hold of the JSON data
                    // we just grabbed.
                    ArrayList<String> portArray = new ArrayList<String>();

                    // Add the data to the array list
                    portArray.add(post.getString("title"));
                    portArray.add(post.getString("excerpt"));
                    portArray.add(portfolioImage.getString("url"));

                    // Add that ArrayList to our portFolioArrayList that will create
                    // our DynamicView
                    portfolioArrayList.add(portArray);

                }

                mListView = (TwoWayView) getActivity().findViewById(R.id.list);
                mListView.setItemMargin(10);
                mListView.setLongClickable(true);
                mListView.setOrientation(TwoWayView.Orientation.HORIZONTAL);
                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View child, int position,
                                            long id) {
                        mCallback.onArticleSelected(position);
                    }
                });


                mListView.setRecyclerListener(new TwoWayView.RecyclerListener() {
                    @Override
                    public void onMovedToScrapHeap(View view) {
                        Log.d(LOGTAG, "View moved to scrap heap");
                    }
                });

                Context context = getActivity();
                mListView.setAdapter(new PortFolioListAdapter(context, portfolioArrayList));


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnHeadlineSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.portfolio_listview, container, false);
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // TODO Get live URL working and fix repeated elements
        new ReadPortfolioJSONFeedTask().execute("http://xiik.com/responsive/index.php/portfolio/json/");

    }

}
