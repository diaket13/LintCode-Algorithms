package thread.pool.assist;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BaseCountDownLatch {
	public static void main(String[] args) {
		final CountDownLatch cdl1 = new CountDownLatch(15);
		
		ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
				new ArrayBlockingQueue<>(5));

        for(int i=0;i<15;i++) {
            Latch task = new Latch(cdl1);
            executor.execute(task);
            System.out.println("�̳߳����߳���Ŀ��"+executor.getPoolSize()+"�������еȴ�ִ�е�������Ŀ��"+
		             executor.getQueue().size()+"����ִ������������Ŀ��"+executor.getCompletedTaskCount());
        }
        new Thread(() -> {
            try {
                System.out.println("�ȴ��������");
                cdl1.await(100,TimeUnit.MILLISECONDS);
                System.out.println("������" + cdl1.getCount());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).run();

        final Thread thread = new Thread(() -> {
            try {
                    System.out.println("�ȴ��������");
                    cdl1.await();
                    System.out.println("�������" + cdl1.getCount());
                } catch (Exception e) {
                    e.printStackTrace();
                }
        });
        thread.run();

    }
	

}
class Latch implements Runnable{
	private final CountDownLatch cdl;
	
	Latch(CountDownLatch cdl) {
		this.cdl = cdl;
	}
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+" ==> ִ��");
		try {
			Thread.sleep(1000);
		}catch(Exception e) {
			System.out.println(Thread.currentThread().getName()+" is out error!");
		}
		cdl.countDown();
		System.out.println(Thread.currentThread().getName()+" ==> ִ�����,������һ");
	}
	
}