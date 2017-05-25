package com.politics.exam.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.politics.exam.util.Utils;

/**
 * Created by malijie on 2017/5/25.
 */

public class BaseActivity extends Activity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //
    public int getResColor(int resId){
        return Utils.getColor(resId);
    }

}
