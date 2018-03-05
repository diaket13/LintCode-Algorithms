package thread.base.volatiles;

public class Automicity {
	public static void main(String[] args) {
		final Data data = new Data();
		for (int i = 0; i < 10; i++) {
			new Thread() {
				public void run() {
					for (int j = 0; j < 1000; j++) {
						data.add();
					}
				}
			}.start();
		}
		while (Thread.activeCount() > 1) {
			Thread.yield();
		}
		System.out.println(data.num);
	}
}

class Data {
	public volatile int num = 0;

	public void add() {
		num++;
	}
}