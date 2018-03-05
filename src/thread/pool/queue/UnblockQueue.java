package thread.pool.queue;

import java.util.PriorityQueue;

public class UnblockQueue {
	private int queueSize = 10;
	private PriorityQueue<Integer> queue = new PriorityQueue<Integer>(queueSize);
	
	public static void main(String[] args) {
		UnblockQueue test = new UnblockQueue();
		Producer pro = test.new Producer();
		Consumer con = test.new Consumer();
		pro.start();
		con.start();
	}
	
	class Consumer extends Thread{
		@Override
		public void run(){
			consume();
		}
		
		private void consume() {
			while(true) {
				synchronized(queue) {
					while(queue.size() == 0) {
						try {
							System.out.println("empty,waiting!");
							queue.wait();
						}catch(Exception e) {
							e.printStackTrace();
							queue.notify();
						}
					}
					Integer i = queue.poll();
					System.out.println("getElement: "+i);
					queue.notify();
					System.out.println("remain:"+queue.size());
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
				synchronized(queue) {
					while(queue.size() == queueSize) {
						try {
							System.out.println("full,waiting");
							queue.wait();
						}catch(Exception e) {
							e.printStackTrace();
							queue.notify();
						}
					}
					queue.offer((int) System.currentTimeMillis());
					queue.notify();
					System.out.println("added,size remain "+(queueSize - queue.size()));
				}
			}
		}
	}
}
