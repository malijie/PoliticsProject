package com.politics.exam.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.politics.exam.R;
import com.politics.exam.widget.MyExpandableListAdapter;


/**
 * Created by malijie on 2016/12/8.
 */


public class QuestionsFragment extends Fragment {
    private ExpandableListView mExpandListView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View messageLayout = inflater.inflate(R.layout.question_fragment, container, false);
        initViews(messageLayout);

        return messageLayout;
    }

    private void initViews(View messageLayout) {
        mExpandListView = (ExpandableListView) messageLayout.findViewById(R.id.expand_list_view);
        mExpandListView.setAdapter(new MyExpandableListAdapter());
        mExpandListView.setGroupIndicator(null);
    }

}