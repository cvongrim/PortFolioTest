package com.colinv.portfoliotest;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;


public class main extends FragmentActivity {

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

            ourPortfolioFragment.setArguments(getIntent().getExtras());

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
            TestFragment testFragment = new TestFragment();

            testFragment.setArguments(getIntent().getExtras());

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.contentRelativeLayout, testFragment);
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
