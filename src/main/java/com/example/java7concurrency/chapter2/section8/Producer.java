package com.example.java7concurrency.chapter2.section8;

/**
 * @author:lifuyi
 * @Date: 2019/1/7 17:34
 * @Description:
 */
public class Producer implements Runnable {

    private FileMock mock;
    private Buffer buffer;

    public Producer(FileMock mock, Buffer buffer) {
        this.mock = mock;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        buffer.setPendingLines(true);
        while (mock.hasMoreLines()) {
            String line = mock.getLine();
            buffer.insert(line);
        }
        buffer.setPendingLines(false);
    }

}
