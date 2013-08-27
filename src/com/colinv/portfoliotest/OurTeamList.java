package com.colinv.portfoliotest;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class OurTeamList extends Fragment {
    private static final String LOGTAG = "TwoWayViewSample";
    public TwoWayView mListView;

    // ArrayList that we will use to populate our gridView
    ArrayList<ArrayList> portfolioArrayList = new ArrayList<ArrayList>();

    OnTeamSelectedListener mCallback;

    // Container Activity must implement this interface
    public interface OnTeamSelectedListener {
        public void onTeamSelected(int position, String url);
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

                JSONObject jsonObject = new JSONObject(result);  // Create a JSON Object
                JSONArray posts = jsonObject.getJSONArray("posts"); // Create a JSONArray of all the post

                for (int i = 0; i < posts.length(); i++) {

                    JSONObject post = posts.getJSONObject(i); // Get a JSONObject for the post

                    JSONObject meta = post.getJSONObject("meta");  // Get the JSONOBject of all the meta data

                    // Now We are going to create an Array List to hold of the JSON data
                    // we just grabbed.
                    ArrayList<String> portArray = new ArrayList<String>();

                    // Add the data to the array list
                    portArray.add(post.getString("ID"));
                    portArray.add(post.getString("title"));
                    portArray.add(post.getString("excerpt"));
                    portArray.add(meta.getString("team_member_thumbnail_url"));
                    //log.e("Thumb URL", "Thumb URL:" +post.getString("color_work_thumbnail"));
                    portArray.add(post.getString("permalink"));

                    // Add that ArrayList to our portFolioArrayList that will create
                    // our DynamicView
                    portfolioArrayList.add(portArray);

                }

                mListView = (TwoWayView) getActivity().findViewById(R.id.list);
                mListView.setItemMargin(10);
                mListView.setOrientation(TwoWayView.Orientation.HORIZONTAL);
                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View child, int position,
                                            long id) {

                        EditText idEditText = (EditText) child.findViewById(R.id.portfolio_id_edit_text);  // Create a reference to the ID field
                        EditText urlEditText = (EditText) child.findViewById(R.id.portfolio_url_edit_text);  // Create a reference to the ID field

                        String portfolioID = idEditText.getText().toString(); // Grab the value of the EditText
                        String portfolioURL = urlEditText.getText().toString(); // Grab the value of the EditText

                        Integer portfolioIDInteger = Integer.valueOf(portfolioID); // Convert it to an integer

                        mCallback.onTeamSelected(portfolioIDInteger, portfolioURL);  // Pass it to the Portfolio Activity
                    }
                });


                mListView.setRecyclerListener(new TwoWayView.RecyclerListener() {
                    @Override
                    public void onMovedToScrapHeap(View view) {
                        Log.d(LOGTAG, "View moved to scrap heap");
                    }
                });

                Context context = getActivity();
                mListView.setAdapter(new OurTeamListAdapter(context, portfolioArrayList));


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
            mCallback = (OnTeamSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnTeamSelectedListener");
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
        new ReadPortfolioJSONFeedTask().execute("http://xiik.com/index.php/team?feed=json");

    }

}