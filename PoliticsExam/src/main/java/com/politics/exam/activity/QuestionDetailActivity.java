package com.politics.exam.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;

import com.politics.exam.R;

/**
 * Created by malijie on 2017/5/27.
 */

public class QuestionDetailActivity extends BaseActivity{
    private ViewPager mViewPager = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_detail);
        initViews();
        initData();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initViews() {
        mViewPager = (ViewPager) findViewById(R.id.id_question_detail_view_pager);
        
    }
}
