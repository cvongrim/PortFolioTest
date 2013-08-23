package com.colinv.portfoliotest;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import org.lucasr.twowayview.TwoWayView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;


public class PortfolioList extends Fragment {
    private static final String LOGTAG = "TwoWayViewSample";

    OnHeadlineSelectedListener mCallback;

    // Container Activity must implement this interface
    public interface OnHeadlineSelectedListener {
        public void onArticleSelected(int position);
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

        TwoWayView mListView;

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
        mListView.setAdapter(new SimpleListAdapter(context));
    }

}
