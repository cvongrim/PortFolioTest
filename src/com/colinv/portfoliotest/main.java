package com.colinv.portfoliotest;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;


public class main extends FragmentActivity implements PortfolioList.OnHeadlineSelectedListener, OurTeamList.OnTeamSelectedListener{

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

    public void onTeamSelected(int position, String url) {
        // The user selected the headline of an article from the HeadlinesFragment
        // Do something here to display that article
        // Create fragment and give it an argument specifying the article it should show
        OurTeam ourTeamFragment = new OurTeam();

        Bundle args = new Bundle();
        args.putInt(PortfolioFragment.ARG_POSITION, position);
        args.putString(PortfolioFragment.ARG_URL, url);
        ourTeamFragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.contentRelativeLayout, ourTeamFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }


   @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

       if(findViewById(R.id.portfolioListView) != null){
           // Create fragment and give it an argument specifying the article it should show
          PortfolioList portfolioListFragment = new PortfolioList();

           portfolioListFragment.setArguments(getIntent().getExtras());

           FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

           // Replace whatever is in the fragment_container view with this fragment,
           // and add the transaction to the back stack so the user can navigate back
           transaction.replace(R.id.portfolioListView, portfolioListFragment);
           // transaction.replace(R.id.portfolioListView, ourTeamListFragment);
           transaction.addToBackStack(null);

           // Commit the transaction
           transaction.commit();

           // Close the sub menu
           hideMenu();
       }
    }

    public void showOurTeam(View view){
        if(findViewById(R.id.contentRelativeLayout) != null){
            // Create fragment and give it an argument specifying the article it should show
            OurTeam ourTeamFragment = new OurTeam();
            OurTeamList ourTeamListFragment = new OurTeamList();

            ourTeamFragment.setArguments(getIntent().getExtras());
            ourTeamListFragment.setArguments(getIntent().getExtras());

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.contentRelativeLayout, ourTeamFragment);
            transaction.replace(R.id.portfolioListView, ourTeamListFragment);
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
            PortfolioList ourPortfolioListFragment = new PortfolioList();

            Bundle args = new Bundle();
            args.putInt(PortfolioFragment.ARG_POSITION, -1);
            ourPortfolioFragment.setArguments(args);

            ourPortfolioListFragment.setArguments(getIntent().getExtras());

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.contentRelativeLayout, ourPortfolioFragment);
            transaction.replace(R.id.portfolioListView, ourPortfolioListFragment);

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
            subMenuLinearLayout .setVisibility(View.INVISIBLE);
        else
            subMenuLinearLayout .setVisibility(View.VISIBLE);
    }

    public void hideMenu(){
        LinearLayout subMenuLinearLayout = (LinearLayout) findViewById(R.id.subMenuLinearLayout);

        if(subMenuLinearLayout .getVisibility() == View.VISIBLE)
            subMenuLinearLayout .setVisibility(View.INVISIBLE);

    }

    public void showInfo(View view) {
        ScrollView aboutProjectTextView = (ScrollView) findViewById(R.id.aboutProjectScrollView);

        if(aboutProjectTextView .getVisibility() == View.VISIBLE)
            aboutProjectTextView .setVisibility(View.INVISIBLE);
        else
            aboutProjectTextView .setVisibility(View.VISIBLE);
    }


}
