package xiongge;

public class Account {
	public static void main(String[] args) {
		System.out.println(Account.aaa());
	}
	
	private static int aaa() {
		try {
			Integer i = null;
			i.intValue();
			System.out.println("����try");
			return 111;
		}catch(Exception e) {
			System.out.println("����catch");
			e.printStackTrace();
			return 222;
		}finally {
			System.out.println("����finally");
		}
	}
}
