package com.colinv.portfoliotest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;


public class main extends Activity {


   @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


    }

    public void showMenu(View view) {
        LinearLayout subMenuLinearLayout = (LinearLayout) findViewById(R.id.subMenuLinearLayout);

        if(subMenuLinearLayout .getVisibility() == View.VISIBLE)
            subMenuLinearLayout .setVisibility(View.GONE);
        else
            subMenuLinearLayout .setVisibility(View.VISIBLE);
    }

}
