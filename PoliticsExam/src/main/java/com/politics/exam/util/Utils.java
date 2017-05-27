package com.politics.exam.util;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;

import com.politics.exam.R;

import static com.politics.exam.PoliticsApplication.sContext;

/**
 * Created by malijie on 2017/5/25.
 */

public class Utils {

    public static int getColor(int resId){
        return sContext.getResources().getColor(resId);
    }

    public static Drawable getDrawable(int resId){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return sContext.getDrawable(resId);
        }else{
            return sContext.getResources().getDrawable(resId);

        }
    }

    public static View getView(int resId){
        return LayoutInflater.from(sContext).inflate(resId,null);
    }

    public void testText(){
//        String content="[顶]"+"哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈啊哈哈";
//        SpannableStringBuilder builder = new SpannableStringBuilder(content);
//        ForegroundColorSpan redSpan = new ForegroundColorSpan(Color.parseColor("#f45b5b"));
//
//        SpannableString textSpan = new SpannableString (content);
//        textSpan.setSpan(new AbsoluteSizeSpan(50),0,4, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
//        textSpan.setSpan(new AbsoluteSizeSpan(32),5,8,Spannable.SPAN_INCLUSIVE_INCLUSIVE);
//
//        builder.setSpan(redSpan, 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        mTextSubject.setText(builder);
//        mTextSubject.setText(textSpan);
    }

}
