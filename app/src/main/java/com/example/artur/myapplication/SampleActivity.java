package com.example.artur.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

/**
 * Created by Artur on 2015-02-22.
 */
public class SampleActivity extends Activity implements Handler.Callback {

    public static final int SYNC_DATA =10;
    private static final int TWO_MINUTES_IN_MILLISECONDS = 2*1000;

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_layout);

        HandlerThread handlerThread=new HandlerThread("BackgroundThread");
        handlerThread.start();

        mHandler=new Handler(handlerThread.getLooper(),this);
    }



    public void sendMessageDemo(Object data)
    {
        Message.obtain(mHandler,SYNC_DATA,data).sendToTarget();

        mHandler.sendEmptyMessage(SYNC_DATA);

        mHandler.sendEmptyMessageAtTime(SYNC_DATA,TWO_MINUTES_IN_MILLISECONDS);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.getLooper().quit();
    }

    @Override
    public boolean handleMessage(Message message) {

        message.recycle();
        return true;
    }
}
