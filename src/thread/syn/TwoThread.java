package thread.syn;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

public class TwoThread {
	public static void main(String[] args) throws InterruptedException {
		final Data data = new Data();
		Thread th1 = new Thread() {
			public void run() {
				data.insert(Thread.currentThread());
			}
		};
		th1.start();
		Thread th2 = new Thread() {
			public void run() {
				data.insert(Thread.currentThread());
			}
		};
		th2.start();
		th1.join();
		th2.join();
		data.outList();
	}

}

class Data {
	private ArrayList<Integer> list = new ArrayList<>();

	public void insert(Thread thread) {
		synchronized (this) {
			for (int i = 0; i < 10; i++) {
				System.out.println(thread.getName() + "||" + thread.getId() + "||add" + i);
				list.add(i);
			}
		}
	}

	public void outList() {
		System.out.println("¿ªÊ¼");
		Iterator<Integer> iter = list.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
		System.out.println("½áÊø");
	}
}