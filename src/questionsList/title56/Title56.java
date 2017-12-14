package questionsList.title56;

import java.util.HashMap;

public class Title56 {
	/*
	 * @param numbers: An array of Integer
	 * 
	 * @param target: target = numbers[index1] + numbers[index2]
	 * 
	 * @return: [index1 + 1, index2 + 1] (index1 < index2)
	 */
	public int[] twoSum(int[] numbers, int target) {
		// write your code here
		HashMap<Integer, Integer> map = new HashMap<>(numbers.length);
		for (int i = 0; i < numbers.length; i++) {
			map.put(numbers[i], i);
		}
		int[] a = new int[2];
		for (int i = 0; i < numbers.length; i++) {
			Integer num = target - numbers[i];
			if (map.containsKey(num)) {
				if (i < map.get(num)) {
					a[0] = i + 1;
					a[1] = map.get(num) + 1;
				} else {
					a[1] = i + 1;
					a[0] = map.get(num) + 1;
				}
				break;
			}
		}
		return a;

	}
}
