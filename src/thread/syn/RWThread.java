package thread.syn;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写lock:
 * 可存在多个线程获取读锁
 * 只能有一个线程获取写锁
 * 读锁和写锁只能有一类被线程获取.得到了写锁,则其他线程得不到读锁,反之亦然
 */
public class RWThread {
	public static void main(String[] args) {
		final RWData data = new RWData();
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
		new Thread(){
			public void run() {
				data.read(Thread.currentThread());
			}
		}.start();
		new Thread(){
			public void run() {
				data.read(Thread.currentThread());
			}
		}.start();
	}
}

class RWData {
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	private ArrayList<Integer> list = new ArrayList<>();
	public void read(Thread thread) {
		System.out.println(thread.getName() + "运行");
		lock.readLock().lock();
		System.out.println(thread.getName() + "读,获取锁");
		try {
			Thread.sleep(1000);
			for(int i =0;i < list.size();i ++) {
				System.out.println(list.get(i));
			}
			System.out.println(thread.getName() + "读操作");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println(thread.getName() + "读完了,释放锁");
			lock.readLock().unlock();
		}
	}
	
	public void write(Thread thread) {
		System.out.println(thread.getName() + "运行");
		lock.writeLock().lock();
		System.out.println(thread.getName() + "写,获取锁");
		try {
			Thread.sleep(1000);
			for(int i =0;i < 30;i ++) {
				list.add(i);
			}
			System.out.println(thread.getName() + "写操作");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println(thread.getName() + "写完了,释放锁");
			lock.writeLock().unlock();
		}
	}
}