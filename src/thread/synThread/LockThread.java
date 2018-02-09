package thread.synThread;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockThread {
	public static void main(String[] args) {
		final DataLock dataLock = new DataLock();
		Thread t1 = new Thread() {
			public void run() {
				try {
					dataLock.insert(Thread.currentThread());
				} catch (InterruptedException e) {
					System.out.println(Thread.currentThread().getName() + "||" + Thread.currentThread().getId() + "||中断");
				}
			}
		};
		Thread t2 = new Thread() {
			public void run() {
				try {
					dataLock.insert(Thread.currentThread());
				} catch (InterruptedException e) {
					System.out.println(Thread.currentThread().getName() + "||" + Thread.currentThread().getId() + "||中断");
					}
			}
		};
		t1.start();
		t2.start();
		 try {
	            Thread.sleep(200);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
		t2.interrupt();
		dataLock.outList();
	}
	
}
class DataLock{
	private ArrayList<Integer> list = new ArrayList<>();
	private Lock lock = new ReentrantLock();
	public void insert(Thread thread) throws InterruptedException {
		lock.lockInterruptibly();
		try {
			System.out.println(thread.getName() + "||" + thread.getId() + "||得到锁");
			System.out.println(thread.getName() + "读操作");
			long start = System.currentTimeMillis();
			while (System.currentTimeMillis() - start <= 10000) {
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println(thread.getName() + "||" + thread.getId() + "||释放锁");
			lock.unlock();
		}
	}

	public void tryInsert(Thread thread) {
		if (lock.tryLock()) {
			try {
				System.out.println(thread.getName() + "||" + thread.getId() + "||得到锁");
				for (int i = 0; i < 10; i++) {
					System.out.println(thread.getName() + "||" + thread.getId() + "||add" + i);
					list.add(i);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				System.out.println(thread.getName() + "||" + thread.getId() + "||释放锁");
				lock.unlock();
			}
		} else {
			System.out.println(thread.getName() + "||" + thread.getId() + "||没得到锁");
		}
	}
	
	public void outList() {
		lock.lock();
		try {
		System.out.println("开始");
		Iterator<Integer> iter = list.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("结束");
			lock.unlock();
		}
	}
}