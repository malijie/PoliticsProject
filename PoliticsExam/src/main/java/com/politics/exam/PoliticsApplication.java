package com.politics.exam;

import android.app.Application;
import android.content.Context;

/**
 * Created by malijie on 2017/5/25.
 */

public class PoliticsApplication extends Application{
    public static Context sContext = null;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
    }
}
