package questionsList.title46;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Title46 {
	/*
	 * @param nums: a list of integers
	 * 
	 * @return: find a majority number
	 */
	public int majorityNumber(List<Integer> nums) {
		// write your code here
		return mooreVotingAlgorithm(nums);
	}

	/** time:O(n) space:O(n) 
	 *  存哈希map,key是数值,value是次数,然后找最大
	 */
	int hashMapWay(List<Integer> nums) {
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int num : nums) {
			Integer frq = map.get(num);
			if (frq == null) {
				frq = 0;
			}
			frq++;
			map.put(num, frq);
		}
		int temp = 0;
		int num = -1;
		for (int i : map.keySet()) {
			int count = map.get(i);
			if (count > temp) {
				temp = count;
				num = i;
			}
		}
		return num;
	}

	/** time:O(nlgn) space:O(1) 
	 *  排序,找中位数
	 */
	int sortWay(List<Integer> nums) {
		Integer[] numArrays = new Integer[nums.size()];
		numArrays = nums.toArray(numArrays);
		Arrays.sort(numArrays);
		return numArrays[numArrays.length / 2];
	}

	/** time:O(n) space:O(1)
	 * 	出现不同数则相消,相同则计数+1,最后留下的必然是主元素
	 */
	int mooreVotingAlgorithm(List<Integer> nums) {
		int count = 0;
		int candidate = 0;
		for (int num : nums) {
			if (count == 0) {
				candidate = num;
				count++;
			} else {
				if (candidate != num) {
					count--;
				} else {
					count++;
				}
			}
		}
		return candidate;
	}
}
