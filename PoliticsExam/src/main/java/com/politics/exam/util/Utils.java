package com.politics.exam.util;

import android.graphics.drawable.Drawable;
import android.os.Build;

import com.politics.exam.PoliticsApplication;
import com.politics.exam.R;

/**
 * Created by malijie on 2017/5/25.
 */

public class Utils {

    public static int getColor(int resId){
        return PoliticsApplication.sContext.getResources().getColor(resId);
    }

    public static Drawable getDrawable(int resId){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return PoliticsApplication.sContext.getDrawable(resId);
        }else{
            return PoliticsApplication.sContext.getResources().getDrawable(resId);

        }
    }

}
