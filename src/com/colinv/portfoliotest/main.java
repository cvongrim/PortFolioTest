package com.colinv.portfoliotest;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.LinearLayout;


public class main extends FragmentActivity implements PortfolioList.OnHeadlineSelectedListener{

    public void onArticleSelected(int position, String url) {
        // The user selected the headline of an article from the HeadlinesFragment
        // Do something here to display that article
        // Create fragment and give it an argument specifying the article it should show
        Portfolio ourPortfolioFragment = new Portfolio();

        Bundle args = new Bundle();
        args.putInt(PortfolioFragment.ARG_POSITION, position);
        args.putString(PortfolioFragment.ARG_URL, url);
        ourPortfolioFragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.contentRelativeLayout, ourPortfolioFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }


   @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

    }

    public void showOurTeam(View view){
        if(findViewById(R.id.contentRelativeLayout) != null){
            // Create fragment and give it an argument specifying the article it should show
            OurTeam ourTeamFragment = new OurTeam();

            ourTeamFragment.setArguments(getIntent().getExtras());

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.contentRelativeLayout, ourTeamFragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();

            // Close the sub menu
            hideMenu();
        }

    }

    public void showOurPortfolio(View view){
        if(findViewById(R.id.contentRelativeLayout) != null){
            // Create fragment and give it an argument specifying the article it should show
            Portfolio ourPortfolioFragment = new Portfolio();

            Bundle args = new Bundle();
            args.putInt(PortfolioFragment.ARG_POSITION, -1);
            ourPortfolioFragment.setArguments(args);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.contentRelativeLayout, ourPortfolioFragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();


            // Close the sub menu
            hideMenu();
        }


    }

    public void showHome(View view){
        if(findViewById(R.id.contentRelativeLayout) != null){
            // Create fragment and give it an argument specifying the article it should show
            YellowX yellowX = new YellowX();

            yellowX.setArguments(getIntent().getExtras());

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.contentRelativeLayout, yellowX);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        }



    }

    public void showMenu(View view) {
        LinearLayout subMenuLinearLayout = (LinearLayout) findViewById(R.id.subMenuLinearLayout);

        if(subMenuLinearLayout .getVisibility() == View.VISIBLE)
            subMenuLinearLayout .setVisibility(View.GONE);
        else
            subMenuLinearLayout .setVisibility(View.VISIBLE);
    }

    public void hideMenu(){
        LinearLayout subMenuLinearLayout = (LinearLayout) findViewById(R.id.subMenuLinearLayout);

        if(subMenuLinearLayout .getVisibility() == View.VISIBLE)
            subMenuLinearLayout .setVisibility(View.GONE);

    }


}
