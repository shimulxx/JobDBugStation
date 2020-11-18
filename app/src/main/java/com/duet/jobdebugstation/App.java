package com.duet.jobdebugstation;

import android.app.Application;

public class App extends Application {
    @Override
    public void onCreate() {
        AppUtilities.initThreadPoolService();
        super.onCreate();
    }

}
