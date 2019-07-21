package com.edm.edmfetchdataplatform.common;


import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Date 2019-07-21
 * @Author lifei
 */
public class ThreadTest {

    /**
     * 使用一个线程池进行操作
     */
    @Test
    public void singleThreadTest() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            Runnable r = () -> {
                System.out.println(Thread.currentThread().getName());
                commondMethod();
            };
            executorService.submit(r);
        }
        executorService.shutdown();
    }


    private void commondMethod(){
        System.out.println("I have a dream");
    }

    /**
     * 创建了多个单线程池进行操作
     */
    @Test
    public void manySingleThreadTest() {
        for (int i = 0; i < 10; i++) {
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            Runnable r = () -> {
                System.out.println(Thread.currentThread().getName());
                commondMethod();
            };
            executorService.submit(r);
            executorService.shutdown();
        }
    }
}
