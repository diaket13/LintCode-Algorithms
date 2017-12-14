package questionsList.title82;

public class Title82 {
    /*
     * @param A: An integer array
     * @return: An integer
     */
	public int singleNumber(int[] A) {
        // write your code here
        int xor = 0;
        for(int i =0 ; i< A.length ; i++){
            xor ^= A[i];
        }
        return xor;
    }
}
