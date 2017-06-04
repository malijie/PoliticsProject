package com.politics.exam.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.politics.exam.R;
import com.politics.exam.db.operator.IDBOperator;
import com.politics.exam.util.Utils;

/**
 * Created by malijie on 2017/5/25.
 */

public abstract class BaseActivity extends Activity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public int getResColor(int resId){
        return Utils.getColor(resId);
    }

    public abstract void initData();
    public abstract void initViews();

    public void updateTextUI(int questionNo, String textFrom, String textTitle, TextView textView){
        String content= questionNo  + "." + textFrom + textTitle;
        SpannableString textSpan = new SpannableString (content);
        textSpan.setSpan(new ForegroundColorSpan(Utils.getColor(R.color.font_yellow)),
                3,8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textSpan.setSpan(new AbsoluteSizeSpan(26),3,8,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(textSpan);



    }


}
