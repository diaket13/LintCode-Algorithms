package questionsList.title633;

public class Title633 {
    /*
     * @param nums: an array containing n + 1 integers which is between 1 and n
     * @return: the duplicate one
     */
	/**
	 * ¹êÍÃËã·¨ ²Î¿¼ http://bookshadow.com/weblog/2015/09/28/leetcode-find-duplicate-number/
	 * @param nums
	 * @return
	 */
	public int findDuplicate(int[] nums) {
		// write your code here
		int slow = 0, fast = 0;
		int count = 0;
		while (count++ < nums.length) {
			slow = nums[slow];
			fast = nums[nums[fast]];
			if (slow == fast) {
				break;
			}
		}
		int finder = 0;
		while (count-- > 0) {
			finder = nums[finder];
			slow = nums[slow];
			if (slow == finder) {
				break;
			}
		}
		return finder;
	}
}
