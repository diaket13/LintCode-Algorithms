package thread.pool.assist;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class BaseCyclicBarrier {
    public static void main(String[] args) {
        int playersNumber = 10;
        final Thread thread = new Thread(() -> {
            System.out.println("全部陈述完" + Thread.currentThread().getName() + "是卧底");
        });
        CyclicBarrier barrier = new CyclicBarrier(playersNumber,thread);
        for (int i = 0; i <playersNumber ; i++) {
            new Player(barrier).start();
        }

    }

}
class Player extends Thread {
    private final CyclicBarrier cyclicBarrier;

    Player(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }


    @Override
    public void run() {
        System.out.println("选手:" + Thread.currentThread().getName() + "正在陈述");
        try {
            Thread.sleep(5000);
            System.out.println("选手:" + Thread.currentThread().getName() + "已陈述完");
            cyclicBarrier.await();
            System.out.println("选手:" + Thread.currentThread().getName() + "退出了游戏");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
