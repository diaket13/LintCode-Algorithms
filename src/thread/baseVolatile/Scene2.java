package thread.baseVolatile;

public class Scene2 {
	public static void main(String[] args) {
		final Data2 data = new Data2();
		new Thread() {
			public void run() {
				data.doit();
			}
		}.start();
		new Thread() {
			public void run() {
				data.init("ÌìÍõ¸ÇµØ»¢");
			}
		}.start();
		
	}

}
class Data2{
	private String context = null;
	private volatile boolean flag = false;
	
	public void init(String s) {
		context = s;
		flag = true;
	}
	
	public void doit() {
		while(!flag) {
			System.out.println("sleeping");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(context);
	}
}