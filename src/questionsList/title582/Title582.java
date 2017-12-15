package questionsList.title582;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Title582 {
	LinkedList<String> list;
	int maxLength;

	/*
	 * @param s: A string
	 * 
	 * @param wordDict: A set of words.
	 * 
	 * @return: All possible sentences.
	 */
	public List<String> wordBreak(String s, Set<String> wordDict) {
		// write your code here
		list = new LinkedList<>();
		maxLength = 0;
		for (String word : wordDict) {
			maxLength = maxLength > word.length() ? maxLength : word.length();
		}
		findWord(s.toCharArray(), wordDict, 0, new StringBuilder());
		return list;
	}

	void findWord(char[] c, Set<String> dict, int location, StringBuilder sentence) {
		if (location == c.length) {
			list.add(sentence.toString().trim());
			return;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = location; i < c.length; i++) {
			sb.append(c[i]);
			if (dict.contains(sb.toString())) {
				StringBuilder thisSentence = new StringBuilder(sentence);
				thisSentence.append(sb);
				thisSentence.append(" ");
				findWord(c, dict, i + 1, thisSentence);
			}
			if (sb.length() >= maxLength) {
				break;
			}
		}
	}
	public static void main(String[] args) {
		Title582 tit = new Title582();
		String a ="aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		String[] aa ={"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"};
		Set<String> wordDict = new HashSet<>();
		for(String aaa : aa) {
			wordDict.add(aaa);
		}
		List<String> list2 = tit.wordBreak(a, wordDict);
		System.out.println(list2.size());
	}
}
