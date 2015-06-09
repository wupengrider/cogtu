package com.cogtu.realtime.kafka;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lenovo on 2015/6/3.
 */
public class KafkaProducerPool {

    // threadCount, recordCountPerThread
    public static void main(String[] args) {
        System.out.println("KafkaProducerPool. " + Arrays.toString(args) + " 2 ");
        BufferedReader wt = null;
        try {
            List<KafkaProducerThread> threadList = new ArrayList<KafkaProducerThread>();
            for (int i = 0; i < Integer.valueOf(args[0]); i++) {
                KafkaProducerThread thread = new KafkaProducerThread(5000, Integer.valueOf(args[1]));
                thread.start();
                threadList.add(thread);
            }

            wt = new BufferedReader(new InputStreamReader(System.in));
            while ("end".equals(wt.readLine())) { // 如果输入end，将所有线程关闭
                for (int i = 0; i < threadList.size(); i++) {
                    threadList.get(i).stopThread();
                }
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                wt.close();
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }

    }


}
