package com.colinv.portfoliotest;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

                   // TODO Clean up what I do

                    String teamMemberBio =  meta.getString("what_i_do");

                    // Add that ArrayList to our portFolioArrayList that will create
                    // our DynamicView
                    frags.add(prepareFragment(teamMemberName, teamMemberPicture, teamMemberBio));

                }

                OurTeamPageAdapter pageAdapter = new OurTeamPageAdapter(getChildFragmentManager(), frags);

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
        return inflater.inflate(R.layout.our_team, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       // List<Fragment> fragments = getFragments();
        new ReadPortfolioJSONFeedTask().execute("http://xiik.com/index.php/team?feed=json");


    }


    private List<Fragment> getFragments() {

        frags.add(prepareFragment("Topher", "http://www.xiik.com/wp-content/uploads/1950/08/Large_Topher.gif", "Topher’s mission with xiik is to foster an effective, collaborative environment of inventive problem solvers. Built into this vision is an enthusiastic commitment to social responsibility and exceeding client expectations. This culture, he believes, creates fresh yet proven, results-driven solutions."));
        frags.add(prepareFragment("Matt", "http://www.xiik.com/wp-content/uploads/1963/08/Large_Matt.jpg", "Matt’s greatest strength is his work ethic. But guess what his kryptonite is? Yep, his work ethic. Easier to understand than this paradox are Matt’s passions: interactive marketing, business, his wife, his dog, Back to the Future, and noodles."));
        frags.add(prepareFragment("Colin", "http://www.xiik.com/wp-content/uploads/1968/08/Large_Colin.jpg", "Fueled by coffee, Colin thrives on activities like rock climbing, ultimate frisbee, and wakeskating. When Colin tells his xiik teammates about that last one, we smile and pretend that we know what 'wakeskating' is. But Colin also likes brewing beer, and we DO know what that is."));
        frags.add(prepareFragment("Kelly", "http://www.xiik.com/wp-content/uploads/1958/08/Large_Kelly.gif", "Kelly is xiik’s resident cornhole pro. At least we assume so–we’re typically too focused on Nerf wars during our limited free time to learn how to play another game. Kelly also loves camping and gardening."));
        frags.add(prepareFragment("Tony", "http://www.xiik.com/wp-content/uploads/1961/10/Large_Tony.gif", "Tony plays a variety of sports, and he coaches, too. We heard the Cubs tried to get him on board, but xiik got to him first. Tony also loves interior and furniture design."));
        frags.add(prepareFragment("Jason", "http://www.xiik.com/wp-content/uploads/2013/04/Large_Jason.jpg", "Whether it’s a Doctor Who marathon or a Thundercats convention, Jason is a connoisseur of all things nerd. When he isn’t drumming or walking his dog, he’s poring over code documentation or trying to build his geek cred."));
        return frags;
    }

    /**
     * Create and set up one OurTeamFragment.
     * @param name Our Team Members.
     */
    Fragment prepareFragment(String name, String memberPicture, String bio) {
        OurTeamFragment cf = new OurTeamFragment();
        Bundle args = new Bundle();
        args.putString(OurTeamFragment.OUR_TEAM_MEMBERS, name);
        args.putString(OurTeamFragment.OUR_TEAM_MEMBERS_PICTURE, memberPicture);
        args.putString(OurTeamFragment.OUR_TEAM_MEMBERS_BIO, bio);
        cf.setArguments(args);
        return cf;
    }
}
