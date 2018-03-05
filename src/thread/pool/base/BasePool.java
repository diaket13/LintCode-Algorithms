package thread.pool.base;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BasePool {
	public static void main(String[] args) {
		ThreadPoolExecutor fixExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
		ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(5));
		for(int i=0;i<15;i++) {
			TheTask task = new TheTask(i);
			executor.execute(task);
			System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
		             executor.getQueue().size()+"，已执行玩别的任务数目："+executor.getCompletedTaskCount());
		}
		for(int i=0;i<21;i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
					executor.getQueue().size()+"，已执行玩别的任务数目："+executor.getCompletedTaskCount());
		}
		
		
	}
}

class TheTask extends Thread{
	private int taskNum;
	
	public TheTask(int i){
		this.taskNum = i;
	}
	
	@Override
	public void run() {
		System.out.println("task "+taskNum+" is running now!");
		try {
			Thread.currentThread();
			Thread.sleep(1000);
		}catch(Exception e) {
			System.out.println("task "+taskNum+" is out error!");
		}
		System.out.println("task "+taskNum+" has run");
	}
}