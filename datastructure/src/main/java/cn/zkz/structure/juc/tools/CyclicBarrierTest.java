package cn.zkz.structure.juc.tools;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(4, () -> {
        System.err.println("任务执行完成");
    });

    public static void main(String[] args) {
        //初始化四个线程
        for (int i = 0; i < 4; i++) {
            new Thread(() -> {
                System.out.println("线程" + Thread.currentThread().getName() + "正在执行");
                try {
                    Thread.sleep(1000);      //以睡眠来模拟操作
                    System.out.println("线程" + Thread.currentThread().getName() + "执行完毕，等待其他线程执行完成");
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }


}
