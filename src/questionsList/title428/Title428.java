package questionsList.title428;

public class Title428 {
    /*
     * @param x: the base number
     * @param n: the power number
     * @return: the result
     */
	public double myPow(double x, int n) {
		// write your code here
		if (n == 1) {
			return x;
		}
		if (n == 0) {
			return 1;
		}
		if (n == -1) {
			return 1 / x;
		}
		double pow1 = myPow(x, n/2);
		double pow2 = pow1;
		if ((n & 1) == 1) {
			if (n > 0) {
				pow2 *= x;
			} else {
				pow2 /= x;
			}
		}
		return pow1 * pow2;
	}
	
	public static void main(String[] args) {
		int n = -7;
		System.out.println(Integer.toBinaryString(n));
		System.out.println(n>>1);
	}
}
