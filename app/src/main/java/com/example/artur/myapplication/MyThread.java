package com.example.artur.myapplication;

import android.app.Activity;
import android.os.SystemClock;
import android.view.View;
import android.widget.ProgressBar;

/**
 * Created by Artur on 2015-02-22.
 */
public class MyThread implements Runnable {

    private Object[] mInput;
    private Activity mActivity;
    private int mProgress=0;

    public MyThread(Activity activity,Object ... input)
    {
        mActivity=activity;
        mInput=input;
    }

    @Override
    public void run() {
        mProgress = 0;

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mActivity.findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
                ((ProgressBar) mActivity.findViewById(R.id.progressBar)).setProgress(0);
            }
        };
        mActivity.runOnUiThread(runnable);

        for (Object input : mInput)
        {
            SystemClock.sleep(50);

            runnable=new Runnable() {
                @Override
                public void run() {
                    ((ProgressBar)mActivity.findViewById(R.id.progressBar)).setMax(mInput.length);
                    ((ProgressBar)mActivity.findViewById(R.id.progressBar)).setProgress(++mProgress);
                }
            };
            mActivity.runOnUiThread(runnable);
        }
        runnable=new Runnable() {
            @Override
            public void run() {
                mActivity.findViewById(R.id.progressBar).setVisibility(View.INVISIBLE);
            }
        };
        mActivity.runOnUiThread(runnable);
    }
}
