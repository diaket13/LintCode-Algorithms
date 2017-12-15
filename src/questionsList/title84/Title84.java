package questionsList.title84;

import java.util.ArrayList;
import java.util.List;

public class Title84 {
    /*
     * @param A: An integer array
     * @return: An integer array
     */
	public List<Integer> singleNumberIII(int[] A) {
		// write your code here
		int xor = singleNumber(A);
		int i = 0;
		while (i < 32) {
			if ((xor >> i & 1) == 1) {
				break;
			}
			i++;
		}
		int[] B = new int[A.length];
		int x = 0;
		for (int a : A) {
			if ((a >> i & 1) == 1) {
				B[x++] = a;
			}
		}
		int q = singleNumber(B);
		int p = xor ^ q;
		List<Integer> list = new ArrayList<>();
		list.add(q);
		list.add(p);
		return list;
	}
    
    public int singleNumber(int[] A) {
        // write your code here
        int xor = 0;
        for(int i =0 ; i< A.length ; i++){
            xor ^= A[i];
        }
        return xor;
    }
}
