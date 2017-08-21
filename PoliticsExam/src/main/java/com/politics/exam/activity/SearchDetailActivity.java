package com.politics.exam.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;

import com.politics.exam.R;
import com.politics.exam.entity.QuestionInfo;
import com.politics.exam.util.IntentManager;

/**
 * Created by malijie on 2017/8/21.
 */

public class SearchDetailActivity extends BaseActivity {
    private ImageButton mButtonBack = null;
    private QuestionInfo mQuestionInfo = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_detail);
    }

    @Override
    public void initData() {
    }

    @Override
    public void initViews() {
        mButtonBack = (ImageButton) findViewById(R.id.id_title_bar_button_back);
        mButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(SearchDetailActivity.this);
            }
        });
    }
}
