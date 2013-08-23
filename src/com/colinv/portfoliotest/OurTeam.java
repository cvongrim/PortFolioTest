package com.colinv.portfoliotest;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class OurTeam extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.our_team, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Fragment> fragments = getFragments();
        OurTeamPageAdapter pageAdapter = new OurTeamPageAdapter(getChildFragmentManager(), fragments);

        ViewPager pager = (ViewPager) view.findViewById(R.id.viewpager);
        pager.setAdapter(pageAdapter);
    }


    private List<Fragment> getFragments() {
        List<Fragment> frags = new ArrayList<Fragment>();
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
