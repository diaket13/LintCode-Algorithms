package thread.synThread;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RWThread {
	public static void main(String[] args) {
		final RWData data = new RWData();
		new Thread(){
			public void run() {
				data.write(Thread.currentThread());
			}
		}.start();
		new Thread(){
			public void run() {
				data.read(Thread.currentThread());
			}
		}.start();
		new Thread(){
			public void run() {
				data.write(Thread.currentThread());
			}
		}.start();
	}
}

class RWData {
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	public void read(Thread thread) {
		lock.readLock().lock();
		System.out.println(thread.getName() + "读,获取锁");
		try {
			long start = System.currentTimeMillis();
			while (System.currentTimeMillis() - start <= 1)
				System.out.println(thread.getName() + "读操作");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println(thread.getName() + "读完了,释放锁");
			lock.readLock().unlock();
		}
	}
	
	public void write(Thread thread) {
		lock.writeLock().lock();
		System.out.println(thread.getName() + "写,获取锁");
		try {
			long start = System.currentTimeMillis();
			while (System.currentTimeMillis() - start <= 1)
				System.out.println(thread.getName() + "写操作");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println(thread.getName() + "写完了,释放锁");
			lock.writeLock().unlock();
		}
	}
}