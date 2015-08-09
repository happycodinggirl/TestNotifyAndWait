package com.example.happycodinggirl.testnotifyandwait;

import android.util.Log;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by happycodinggirl on 2015/8/9.
 */
public class Producer extends Thread {
    public Producer(LinkedList<String> queueList,int masSize) {
        this.queueList = queueList;
        this.masSize=masSize;
    }

    LinkedList<String> queueList;
    int masSize;

    @Override
    public void run() {
        super.run();
        while (true) {
            synchronized (queueList) {
                while (queueList.size() == masSize) {
                    try {
                        queueList.notifyAll();
                        queueList.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Random random=new Random();
                int num=random.nextInt();
                String item=new String("produce item "+num);
                queueList.add(item);
                Log.v("Tag", "---------produce item "+item);
               /* if (queueList.size()==masSize) {

                }*/
            }
        }
    }
}

