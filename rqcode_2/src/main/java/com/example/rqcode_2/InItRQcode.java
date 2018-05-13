package com.example.rqcode_2;

import android.app.Application;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;

public class InItRQcode extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ZXingLibrary.initDisplayOpinion(this);

    }
}
