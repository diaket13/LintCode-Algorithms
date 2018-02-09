package thread.baseLocal;

public class BaseLocal {
	private ThreadLocal<Integer> idLocal = new ThreadLocal<Integer>() {
		protected Integer initialValue() {
			return (int)Thread.currentThread().getId();
		}
	};
	private ThreadLocal<String> nameLocal = new ThreadLocal<String>() {
		protected String initialValue() {
			return Thread.currentThread().getName();
		}
	};
	
	public void set() {
		idLocal.set((int)Thread.currentThread().getId());
		nameLocal.set(Thread.currentThread().getName());
	}
	
	public void out() {
		System.out.println(nameLocal.get() +"~~" + idLocal.get());
	}
	public static void main(String[] args) {
		final BaseLocal base = new BaseLocal();
//		base.set();
		base.out();
		Thread t1 = new Thread() {
			public void run() {
				base.set();
				base.out();
			}
		};
		t1.start();
		try {
			t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		base.out();
	}
}
