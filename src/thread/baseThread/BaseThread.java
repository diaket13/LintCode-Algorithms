package thread.baseThread;

public class BaseThread {
	public static void main(String[] args) {
		Thread1 thread1 = new Thread1();
		thread1.start();
		Runnable1 runnable1 = new Runnable1();
		Thread thread2 = new Thread(runnable1);
		thread2.start();
		System.out.println("���߳�:"+Thread.currentThread().getName()+" " + Thread.currentThread().getId());
	}
}

class Thread1 extends Thread {
	@Override
	public void run() {
		System.out.println("���߳�:"+Thread.currentThread().getName()+" " + Thread.currentThread().getId());
	}
}

class Runnable1 implements Runnable {

	@Override
	public void run() {
		System.out.println("���߳�:"+Thread.currentThread().getName()+" " + Thread.currentThread().getId());
	}
	
}