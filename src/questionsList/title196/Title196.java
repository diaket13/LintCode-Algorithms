package questionsList.title196;

public class Title196 {
    /*
     * @param nums: An array of integers
     * @return: An integer
     */
	public int findMissing(int[] nums) {
		// write your code here
		int count = 0;
		for (int i = 0; i <= nums.length; i++) {
			count ^= i;
		}
		for (int num : nums) {
			count ^= num;
		}
		return count;
	}
}
