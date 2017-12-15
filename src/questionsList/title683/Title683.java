package questionsList.title683;

import java.util.Set;

public class Title683 {
	int count = 0;

	/*
	 * @param : A string
	 * 
	 * @param : A set of word
	 * 
	 * @return: the number of possible sentences.
	 */
	public int wordBreak3(String s, Set<String> dict) {
		// Write your code here
		count = 0;
		char[] c = s.toCharArray();
		findWord(c, dict, 0);
		return count;
	}

	void findWord(char[] c, Set<String> dict, int location) {
		if (location == c.length) {
			count++;
			return;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = location; i < c.length; i++) {
			sb.append(c[i]);
			if (dict.contains(sb.toString())) {
				findWord(c, dict, i + 1);
			}
		}
	}

}
