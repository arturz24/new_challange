package com.example.artur.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {

    public static final String TAG="MainActivity";
    public static final int WIDTH_OF_SCREEN = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void doCalculations(View view) {
        TextView resultLabel = (TextView) findViewById(R.id.resultTextView);
        EditText input = (EditText) findViewById(R.id.number);
        int value;
        try {
            value=Integer.parseInt(input.getText().toString());
            resultLabel.setText(Integer.toString(getResult(value)));

        } catch (NumberFormatException e){
            Log.d(TAG,"zla liczba");
        }
    }

    private int getResult(int value)
    {
        int result=0;
        result = doCalculationLoop(result);
        result=result* WIDTH_OF_SCREEN+value;
        return result;
    }

    private int doCalculationLoop(int result) {
        for(int i=0;i<5;i++) {
            result += i;
        }
        return result;
    }

    public void testThread(View view) {
        Thread thread=new Thread(new MyThread(this,new Object[100]));
        thread.start();
    }

    public void testAsyncThread(View view) {
        MyAsyncTask myAsyncTask=new MyAsyncTask(this);
        myAsyncTask.execute(new String[50]);
    }

    public void invokeHandler(View view) {
        Intent intent=new Intent(this,SampleActivity.class);
        startActivity(intent);
    }
}
