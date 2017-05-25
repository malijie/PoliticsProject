package com.politics.exam.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.politics.exam.R;


/**
 * Created by malijie on 2016/12/8.
 */

public class QuestionsFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View messageLayout = inflater.inflate(R.layout.question_fragment, container, false);
        return messageLayout;
    }

}