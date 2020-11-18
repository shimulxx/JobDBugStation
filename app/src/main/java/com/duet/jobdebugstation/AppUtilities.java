package com.duet.jobdebugstation;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppUtilities {
    private AppUtilities(){};

    public static Handler handler = new Handler(Looper.getMainLooper());

    public static ExecutorService executorService;

    public static void initThreadPoolService(){
        int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
        executorService = Executors.newFixedThreadPool((int)(NUMBER_OF_CORES * 11 / 0.055));
    }
}
