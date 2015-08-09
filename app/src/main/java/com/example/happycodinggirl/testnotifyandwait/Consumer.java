package com.example.happycodinggirl.testnotifyandwait;

import android.util.Log;

import java.util.LinkedList;

/**
 * Created by happycodinggirl on 2015/8/9.
 */
public class Consumer extends Thread {
    LinkedList<String> queueList;
    int maxSize;

    public Consumer(LinkedList<String> queueList,int maxSize) {
        this.queueList = queueList;
        this.maxSize=maxSize;
    }

    @Override
    public void run() {
        super.run();
        while(true){
            synchronized (queueList){
                while(queueList.isEmpty()){
                    try {
                        queueList.notifyAll();
                        queueList.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Log.v("TAG", "----consume item " + queueList.remove());



            }
        }
    }
}
