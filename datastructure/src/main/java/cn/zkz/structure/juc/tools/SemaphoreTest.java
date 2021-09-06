package cn.zkz.structure.juc.tools;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class SemaphoreTest {
    private static AtomicInteger a = new AtomicInteger(1);
    private static Semaphore aa = new Semaphore(1);
    private static Semaphore bb = new Semaphore(0);
    public static void main(String[] args) {
        new Thread(()->{
            while (a.get()<100) {
                try {
                    aa.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(a.getAndAdd(1) + "=========" + Thread.currentThread().getName());
                bb.release();
            }
        }).start();
        new Thread(()->{
            while (a.get()<=100) {
                try {
                    bb.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(a.getAndAdd(1)+ "=========" + Thread.currentThread().getName());
                aa.release();
            }
        }).start();
    }

}
