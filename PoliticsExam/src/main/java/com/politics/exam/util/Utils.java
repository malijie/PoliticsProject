package com.politics.exam.util;

import com.politics.exam.PoliticsApplication;
import com.politics.exam.R;

/**
 * Created by malijie on 2017/5/25.
 */

public class Utils {

    public static int getColor(int resId){
        return PoliticsApplication.sContext.getResources().getColor(resId);
    }

}
