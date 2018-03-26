package thread.syn;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ��дlock:
 * �ɴ��ڶ���̻߳�ȡ����
 * ֻ����һ���̻߳�ȡд��
 * ������д��ֻ����һ�౻�̻߳�ȡ.�õ���д��,�������̵߳ò�������,��֮��Ȼ
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
		System.out.println(thread.getName() + "����");
		lock.readLock().lock();
		System.out.println(thread.getName() + "��,��ȡ��");
		try {
			Thread.sleep(1000);
			for(int i =0;i < list.size();i ++) {
				System.out.println(list.get(i));
			}
			System.out.println(thread.getName() + "������");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println(thread.getName() + "������,�ͷ���");
			lock.readLock().unlock();
		}
	}
	
	public void write(Thread thread) {
		System.out.println(thread.getName() + "����");
		lock.writeLock().lock();
		System.out.println(thread.getName() + "д,��ȡ��");
		try {
			Thread.sleep(1000);
			for(int i =0;i < 30;i ++) {
				list.add(i);
			}
			System.out.println(thread.getName() + "д����");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println(thread.getName() + "д����,�ͷ���");
			lock.writeLock().unlock();
		}
	}
}