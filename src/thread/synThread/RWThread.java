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
		System.out.println(thread.getName() + "��,��ȡ��");
		try {
			long start = System.currentTimeMillis();
			while (System.currentTimeMillis() - start <= 1)
				System.out.println(thread.getName() + "������");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println(thread.getName() + "������,�ͷ���");
			lock.readLock().unlock();
		}
	}
	
	public void write(Thread thread) {
		lock.writeLock().lock();
		System.out.println(thread.getName() + "д,��ȡ��");
		try {
			long start = System.currentTimeMillis();
			while (System.currentTimeMillis() - start <= 1)
				System.out.println(thread.getName() + "д����");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println(thread.getName() + "д����,�ͷ���");
			lock.writeLock().unlock();
		}
	}
}