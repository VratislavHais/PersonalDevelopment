package com.vhais.customsort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		String order = "cba";
		String str = "abcdgdsiutrqwesasrwqdfsater";
		System.out.println(customSortString(order, str));
	}

	public static String customSortString(String order, String str) {
		int[] count = new int[26];
		StringBuilder result = new StringBuilder();
		for (char c : str.toCharArray()) {
			count[c - 'a']++;
		}
		for (char c : order.toCharArray()) {
			while (count[c - 'a']-- > 0) result.append(c);
		}
		for (int i = 0; i < count.length; i++) {
			while (count[i]-- > 0) result.append((char) ('a' + i));
		}
		return result.toString();
	}

//	public static String customSortString(String order, String str) {
//		Map<Character, Integer> mapToIndex = new HashMap<>();
//		StringBuilder sb = new StringBuilder();
//		int i = 0;
//		while (i < order.length()) mapToIndex.put(order.charAt(i), i++);
//		int[] indexing = new int[26];
//		for (Character c : str.toCharArray()) {
//			if (!mapToIndex.containsKey(c)) mapToIndex.put(c, i++);
//			sb.insert(indexing[mapToIndex.get(c)]++, "" + c);
//			updateIndexes(indexing, mapToIndex.get(c), i);
//		}
//		return sb.toString();
//	}
//
//	private static void updateIndexes(int[] indexing, int fromIndex, int size) {
//		for (int i = fromIndex + 1; i <= size; i++) {
//			if (indexing[i] == 0) indexing[i] = indexing[i-1];
//			else indexing[i]++;
//		}
//	}
}
