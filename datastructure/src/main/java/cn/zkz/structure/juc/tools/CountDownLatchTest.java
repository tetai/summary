package cn.zkz.structure.juc.tools;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CountDownLatchTest {

    /**
     * 计数器，用来控制线程数量，传入参数2，表示计数器计数为2
     */
    private final static CountDownLatch M_COUNT_DOWN_LATCH = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {

        // 最先run WorkerThread
       new Thread(()->{
            System.out.println("[WorkerThread] started!");
            try {
                // 阻塞在这里等待 mCountDownLatch 里的count变为0；
                M_COUNT_DOWN_LATCH.await();
            } catch (InterruptedException e) {

            }
            System.out.println("[WorkerThread] end!");
        }).start();
        // 运行两个工作线程
        // 工作线程1运行3秒
        new Thread(() -> {
            System.out.println("[" + Thread.currentThread().getName() + "] started!");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            M_COUNT_DOWN_LATCH.countDown();
            System.out.println("[" + Thread.currentThread().getName() + "] end!");
        }).start();
        System.out.println(11111);
    }

}
