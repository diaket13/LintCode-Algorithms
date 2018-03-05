package thread.base.volatiles;

public class Scene1 {
	public static void main(String[] args) {
		final Data1 data = new Data1();
		new Thread() {
			public void run() {
				data.doit();
			}
		}.start();
		new Thread() {
			public void run() {
				data.change();
			}
		}.start();
	}
}
class Data1{
	private volatile boolean flag = true;
	
	public void doit() {
		while(flag) {
			System.out.println("doing");
		}
		System.out.println("¸ã¶¨");
	}
	
	public void change() {
		flag = false;
	}
}