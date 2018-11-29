package com.example.java7concurrency.chapter2.section6;

/**
 * @auther:lifuyi
 * @Date: 2018/11/29 14:19
 * @Description:
 */
public class Reader implements Runnable{

    private PricesInfo pricesInfo;

    public Reader(PricesInfo pricesInfo) {
        this.pricesInfo = pricesInfo;
    }

    @Override
    public void run() {
        for(int i=0;i<10;i++){
            System.out.printf("%s: Price 1: %f\n", Thread.currentThread().getName(),pricesInfo.getPrice1());
            System.out.printf("%s: Price 1: %f\n", Thread.currentThread().getName(),pricesInfo.getPrice2());
        }
    }
}
