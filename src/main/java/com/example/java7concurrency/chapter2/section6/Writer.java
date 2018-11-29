package com.example.java7concurrency.chapter2.section6;

import java.util.concurrent.TimeUnit;

/**
 * @auther:lifuyi
 * @Date: 2018/11/29 14:25
 * @Description:
 */
public class Writer implements Runnable{

    private PricesInfo pricesInfo;

    public Writer(PricesInfo pricesInfo) {
        this.pricesInfo = pricesInfo;
    }

    @Override
    public void run() {
        for(int i=0;i<3;i++){
            System.out.printf("Writer: Attempt to modify the prices.\n");
            pricesInfo.setPrices(Math.random()*10, Math.random()*8);
            System.out.printf("Writer: Prices have been modified.\n");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
