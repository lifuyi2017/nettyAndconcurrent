package com.example.java7concurrency.chapter2.section8;

import java.util.Random;

/**
 * @author:lifuyi
 * @Date: 2019/1/7 18:10
 * @Description:
 */
public class Consumer implements Runnable{
    private Buffer buffer;
    public Consumer (Buffer buffer) {
        this.buffer=buffer;
    }

    @Override
    public void run() {
        while (buffer.hasPendingLines()) {
            String line=buffer.get();
            processLine(line);
        }
    }

    private void processLine(String line) {
        try {
            Random random=new Random();
            Thread.sleep(random.nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
