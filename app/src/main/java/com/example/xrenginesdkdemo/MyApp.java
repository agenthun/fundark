package com.example.xrenginesdkdemo;

import android.app.Application;

//import com.tencent.bugly.crashreport.CrashReport;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        CrashReport.initCrashReport(getApplicationContext(), "93bbe9f6be", true);
    }
}
