package com.vhais;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
//		String beginWord = "hit", endWord = "cog";
//		List<String> wordList = new ArrayList<>(Arrays.asList("hot","dot","dog","lot","log","cog"));
		String beginWord = "a", endWord = "c";
		List<String> wordList = new ArrayList<>(Arrays.asList("a", "b", "c"));
//		String beginWord = "red", endWord = "tax";
//		List<String> wordList = new ArrayList<>(Arrays.asList("ted","tex","red","tax","tad","den","rex","pee"));
		findLadders(beginWord, endWord, wordList).forEach(list -> {
			list.forEach(i -> System.out.print(i + " "));
			System.out.println();
		});
	}

	public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
		Map<String, List<String>> graph = new HashMap<>();
		graph.put(beginWord, new ArrayList<>());
		for (String w : wordList) {
			if (differByOne(w, beginWord)) graph.get(beginWord).add(w);
			if (graph.containsKey(w)) continue;
			graph.put(w, new ArrayList<>());
			for (String w2: wordList) {
				if (differByOne(w, w2)) graph.get(w).add(w2);
			}
		}
		Set<String> visited = new HashSet<>();
		List<List<String>> result = new ArrayList<>();
		Queue<LinkedList<String>> queue = new LinkedList<>();
		queue.offer(new LinkedList<>(Arrays.asList(beginWord)));
		visited.add(beginWord);
		int resultSize = Integer.MAX_VALUE;
		do {
			LinkedList<String> list = new LinkedList<>(queue.poll());
			if (list.size() + 1 > resultSize) break;
			visited.add(list.getLast());
			for (String w : graph.get(list.getLast())) {
				if (!visited.contains(w)) {
					list.add(w);
					if (w.equals(endWord)) {
						resultSize = list.size();
						result.add(new LinkedList<>(list));
					} else queue.offer(new LinkedList<>(list));
					list.removeLast();
				}
			}
		} while (!queue.isEmpty());
		return result;
	}

	public static boolean differByOne(String s1, String s2) {
		boolean differenceFound = false;
		for (int i = 0; i < s1.length(); i++) {
			if (s1.charAt(i) != s2.charAt(i)) {
				if (differenceFound) return false;
				differenceFound = true;
			}
		}
		return differenceFound;
	}
}
