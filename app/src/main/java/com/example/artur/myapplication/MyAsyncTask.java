package com.example.artur.myapplication;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.view.View;
import android.widget.ProgressBar;

/**
 * Created by Artur on 2015-02-22.
 */
public class MyAsyncTask extends AsyncTask<String,Integer,Integer> {

    private Activity mActivity;

    public MyAsyncTask(Activity activity)
    {
        mActivity=activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mActivity.findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
        ((ProgressBar)mActivity.findViewById(R.id.progressBar)).setProgress(0);
    }

    @Override
    protected Integer doInBackground(String... strings) {

        int progress=0;
        for(String input : strings)
        {
            SystemClock.sleep(50);
            publishProgress(++progress,strings.length);
        }
        return progress;

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        ((ProgressBar)mActivity.findViewById(R.id.progressBar)).setMax(values[1]);
        ((ProgressBar)mActivity.findViewById(R.id.progressBar)).setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        mActivity.findViewById(R.id.progressBar).setVisibility(View.INVISIBLE);
    }
}
