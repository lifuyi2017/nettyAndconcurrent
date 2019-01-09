package com.example.java7concurrency.chapter2.section6;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @auther:lifuyi
 * @Date: 2018/11/29 11:46
 * @Description:
 */
public class PricesInfo {

    private double price1;
    private double price2;
    private ReadWriteLock lock;

    public PricesInfo() {
        price1=1.0;
        price2=2.0;
        lock=new ReentrantReadWriteLock();
    }

    public double getPrice1() {
        lock.readLock().lock();
        System.out.println("price1被线程"+Thread.currentThread().getName()+"获取读锁");
        double value=price1;
        System.out.println("***********************************"+value);
        System.out.println("price1被线程"+Thread.currentThread().getName()+"释放读锁");
        lock.readLock().unlock();
        return value;
    }

    public double getPrice2() {

        lock.readLock().lock();
        System.out.println("price2被线程"+Thread.currentThread().getName()+"获取读锁");
        double value=price2;
        System.out.println("***********************************"+value);
        System.out.println("price2被线程"+Thread.currentThread().getName()+"释放读锁");
        lock.readLock().unlock();
        return value;

    }

    public void setPrices(double price1, double price2){
        lock.writeLock().lock();
        System.out.println("获取写锁");
        this.price1=price1;
        System.out.println("***********************************"+price1);
        this.price2=price2;
        System.out.println("***********************************"+price2);
        System.out.println("释放写锁");
        lock.writeLock().unlock();
    }


}
