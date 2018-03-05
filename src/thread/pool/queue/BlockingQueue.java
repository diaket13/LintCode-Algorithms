package thread.pool.queue;

import java.util.concurrent.ArrayBlockingQueue;


public class BlockingQueue {
	private int queueSize = 10;
	private ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(queueSize);
	
	public static void main(String[] args) {
		BlockingQueue test = new BlockingQueue();
		Producer pro = test.new Producer();
		Consumer con = test.new Consumer();
		pro.start();
		con.start();
	}
	class Consumer extends Thread{
		@Override
		public void run() {
			consume();
		}
		
		private void consume() {
			while(true) {
				try {
					Integer i = queue.take();
					System.out.println("get "+i+" size "+queue.size());
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	class Producer extends Thread{
		@Override
		public void run() {
			produce();
		}
		
		private void produce() {
			while(true) {
				try {
					queue.put((int) System.currentTimeMillis());
					System.out.println("added,size remain "+(queueSize - queue.size()));
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
