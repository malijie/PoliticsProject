package com.politics.exam.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.widget.TextView;

import com.politics.exam.R;
import com.politics.exam.db.operator.BaseOperator;
import com.politics.exam.util.Logger;

import static android.text.Html.FROM_HTML_MODE_LEGACY;

/**
 * Created by malijie on 2017/8/25.
 */

public class TestActivity extends BaseActivity{

    private TextView mText = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);
        initViews();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initViews() {
        mText = (TextView) findViewById(R.id.test_text);
        mText.setText(Html.fromHtml(new BaseOperator().getExamContentByYear("")));
    }
}
