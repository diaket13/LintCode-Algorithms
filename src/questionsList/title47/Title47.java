package questionsList.title47;

import java.util.HashMap;
import java.util.List;

public class Title47 {
	/*
	 * @param nums: a list of integers
	 * 
	 * @return: The majority number that occurs more than 1/3
	 */
	public int majorityNumber(List<Integer> nums) {
		// write your code here
		return hashMapWay(nums);
	}

	/**
	 * time:O(n) space:O(n) ���ϣmap,key����ֵ,value�Ǵ���,Ȼ�������
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

	/**
	 * time:O(n) space:O(1) ����3����ͬ��������,��ͬ�����+1,������µ�2���ٲ�һ��,������Ǹ���Ȼ����Ԫ��
	 */
	int mooreVotingAlgorithm(List<Integer> nums) {
		int count = 0;
		int candidate = 0;
		int count1 = 0;
		int candidate1 = 0;
		for (int num : nums) {
			if (count == 0) {
				if (num != candidate1) {
					candidate = num;
					count++;
				} else {
					count1++;
				}
			} else if (count1 == 0) {
				if (num != candidate) {
					candidate1 = num;
					count1++;
				} else {
					count++;
				}
			} else {
				if (candidate == num) {
					count++;
				} else if (candidate1 == num) {
					count1++;
				} else {
					count1--;
					count--;
				}
			}
		}
		count = 0;
		count1 = 0;
		for (int num : nums) {
			if (num == candidate) {
				count++;
				continue;
			}
			if (num == candidate1) {
				count1++;
				continue;
			}
		}
		if (count > count1) {
			return candidate;
		} else {
			return candidate1;
		}
	}
}
