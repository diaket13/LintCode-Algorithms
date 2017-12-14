package questionsList.title140;

public class Title140 {
	/*
     * @param a: A 32bit integer
     * @param b: A 32bit integer
     * @param n: A 32bit integer
     * @return: An integer
     */
	public int fastPower(int a, int b, int n) {
		return (int) fastPower((long) a, (long) b, (long) n);
	}

	public long fastPower(long a, long b, long n) {
		// O(lgn)
		if (n == 0) {
			return 1 % b;
		}
		if (n == 1) {
			return a % b;
		}
		long c1 = fastPower(a, b, n >> 1);
		long c2;
		if ((n & 1) == 1) {
			c2 = c1 * a % b;
		} else {
			c2 = c1;
		}
		return c1 * c2 % b;
	}
	
	
	
	public int fastPowerNormal(int a, int b, int n) {
		//O(n)
		int result = 1;
		if (n == 0) {
			return result % b;
		}
		for (int i = 0; i < n; i++) {
			result *= a;
			if (result >= b) {
				result = result % b;
			}
			// a^(n-1) = b*k1 + c1;
			// a^n = b*k2 + c2 = b*k1*a + c1*a;
			// c2 = c1*a<b ? c1*a : c1*a%b;
		}
		return result;
	}	
    
    public static void main(String[] args) {
    	System.out.println(Integer.MIN_VALUE);
    	int n=4,k=3,l=n+k;
    	String a= "null";
    	String b= "\"null\"";
    	System.out.println(2 % 3);
    	Title140 as = new Title140();
    	as.fastPower(109 , 10000007, 1000001);
    }
}
