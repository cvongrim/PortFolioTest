package com.colinv.portfoliotest;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


public class main extends FragmentActivity {


   @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


    }

    public void showOurTeam(View view){


    }

    public void showMenu(View view) {
        LinearLayout subMenuLinearLayout = (LinearLayout) findViewById(R.id.subMenuLinearLayout);

        if(subMenuLinearLayout .getVisibility() == View.VISIBLE)
            subMenuLinearLayout .setVisibility(View.GONE);
        else
            subMenuLinearLayout .setVisibility(View.VISIBLE);
    }

}
