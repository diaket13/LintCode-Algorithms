package questionsList.title570;

public class Title570 {
	boolean[] existence;

	/*
	 * @param n: An integer
	 * 
	 * @param str: a string with number from 1-n in random order and miss one number
	 * 
	 * @return: An integer
	 */
	public int findMissing2(int n, String str) {
		// write your code here
		char[] nums = str.toCharArray();
		existence = new boolean[n + 1];
		for (int i = 0; i < existence.length; i++) {
			existence[i] = false;
		}
		findNum(n, nums, 0);
		for (int i = 1; i < existence.length; i++) {
			if (!existence[i]) {
				return i;
			}
		}
		return 0;

	}

	/**
	 * �����������ע��ĵ�
	 * 1:��֤������ɵĽ������ȷ��
	 * 2:���˵�ʱ����Ҫ��֤���е�״̬����Ҳ���˵���ʱ���
	 * P.S.�������������,�ô������,�����������û����if-else��������
	 */
	boolean findNum(int n, char[] nums, int location) {
		if (location == nums.length) {
			int count = 0;
			for (int i = 1; i < existence.length; i++) {
				if (!existence[i]) {
					count++;
				}
			}
			if (count > 1) {
				return false;
			}
			return true;
		}
		int num = nums[location++] - 48;
		if (num == 0) {
			return false;
		}
		if (num <= n / 10) {
			num = num * 10 + (nums[location++] - 48);
			if (num <= n && !existence[num]) {
				existence[num] = true;
				if (findNum(n, nums, location)) {
					return true;
				}
				existence[num] = false;
			}
			num /= 10;
			--location;
		}
		if (existence[num]) {
			return false;
		}
		existence[num] = true;
		if (findNum(n, nums, location)) {
			return true;
		}
		existence[num] = false;
		return false;
	}

	public static void main(String[] args) {
		Title570 ss = new Title570();
		System.out.println(ss.findMissing2(30, "1110986543271213130292826252423222120191817161514"));
	}
}
