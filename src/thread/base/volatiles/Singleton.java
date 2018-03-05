package thread.base.volatiles;

public class Singleton {
	private volatile Singleton instance = null;
	public Singleton getInstance() {
		if(instance == null) {
			synchronized (this) {
				if(instance == null) {
					instance = new Singleton();
				}
			}
		}
		return instance;
	}
}
