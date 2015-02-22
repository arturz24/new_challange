package com.example.artur.myapplication;

import java.util.LinkedList;

/**
 * Created by Artur on 2015-02-22.
 */
public class ThreadSafeQueue {
    private LinkedList<String> mList=new LinkedList<String>();
    private final Object mLock=new Object();


    public void offer(String value)
    {
        synchronized (mLock)
        {
            mList.offer(value);
            mLock.notifyAll();
        }
    }

    public synchronized String poll()
    {
        synchronized (mLock)
        {
            while (mList.isEmpty())
            {
                try{
                   mLock.wait();
                }catch (InterruptedException e){

                }
            }
            return mList.poll();
        }
    }
}
