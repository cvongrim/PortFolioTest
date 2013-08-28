package com.colinv.portfoliotest;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class OurTeam extends Fragment {
    public ViewPager pager;
    private Integer ourTeamSelectedPosition;
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

                for (int i = 0; i < posts.length(); i++) {

                    JSONObject post = posts.getJSONObject(i); // Get a JSONObject for the post

                    JSONObject meta = post.getJSONObject("meta");  // Get the JSONOBject of all the meta data

                    // Add the data to the array list
                    String teamMemberName =  post.getString("title");

                    String teamMemberPicture =  meta.getString("team_member_portrait_url");

                    // TODO Clean Up Output and remove
                    String teamMemberJobTitle =  meta.getString("job_title");
                    String teamMemberBio =  meta.getString("what_i_do");

                    // Add that ArrayList to our portFolioArrayList that will create
                    // our DynamicView
                    frags.add(prepareFragment(teamMemberName,teamMemberJobTitle, teamMemberPicture, teamMemberBio));

                }

                OurTeamPageAdapter pageAdapter = new OurTeamPageAdapter(getChildFragmentManager(), frags);

                pager = (ViewPager) getActivity().findViewById(R.id.viewpager);
                pager.setAdapter(pageAdapter);

              //  pager.setCurrentItem(ourTeamSelectedPosition);
                Log.e("Our Team", "PagerView:" + ourTeamSelectedPosition);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.our_team, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // TODO Add ListSelector for when the user uses the list to select a team member
       // ourTeamSelectedPosition = getArguments().getInt("position");

        // List<Fragment> fragments = getFragments();
        new ReadPortfolioJSONFeedTask().execute("http://xiik.com/index.php/team?feed=json");



    }

    /**
     * Create and set up one OurTeamFragment.
     * @param name Our Team Members.
     */
    Fragment prepareFragment(String name, String teamMemberJobTitle, String memberPicture, String bio) {
        OurTeamFragment cf = new OurTeamFragment();
        Bundle args = new Bundle();
        args.putString(OurTeamFragment.OUR_TEAM_MEMBERS, name);
        args.putString(OurTeamFragment.OUR_TEAM_MEMBERS_JOB_TITLE, teamMemberJobTitle);
        args.putString(OurTeamFragment.OUR_TEAM_MEMBERS_PICTURE, memberPicture);
        args.putString(OurTeamFragment.OUR_TEAM_MEMBERS_BIO, bio);
        cf.setArguments(args);
        return cf;
    }
}
