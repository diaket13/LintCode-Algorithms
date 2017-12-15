package questionsList.title83;

public class Title83 {
    /*
     * @param A: An integer array
     * @return: An integer
     */
	public int singleNumberII(int[] A) {
		// write your code here
		int b = 0, bit;
		for (int i = 0; i < 32; i++) {
			bit = 0;
			for (int a : A) {
				bit += (a >> i) & 1;
			}
			b |= bit % 3 << i;
		}
		return b;
	}
    
    public static void main(String[] args) {
    	int i = Integer.MAX_VALUE;
    	System.out.println(Integer.toBinaryString(i));
    	int j = Integer.MIN_VALUE;
    	System.out.println(Integer.toBinaryString(j));
    	System.out.println(4%3<<3);
    	System.out.println(Integer.toBinaryString(i|j));
    }
}
