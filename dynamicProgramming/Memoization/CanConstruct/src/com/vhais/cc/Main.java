package com.vhais.cc;

import java.util.HashMap;
import java.util.Map;

/*
    Write a function `canConstruct(target, wordBank)` that accepts a target string
    and an array of strings.
    The function should return boolean indicating whether or not the `target`
    can be constructed by concatenating elements of the `wordBank` array.
    You may reuse elements of `wordBank` as many times as needed.
 */
public class Main {

	public static void main(String[] args) {
		System.out.println(canConstruct("abcdef", new String[] {"ab", "abc", "cd", "def", "abcd"}));
		System.out.println(canConstruct("skateboard", new String[] {"bo", "rd", "ate", "t", "ska", "sk", "boar"}));
		System.out.println(canConstruct("enterapotentpot", new String[] {"a", "p", "ent", "enter", "ot", "o", "t"}));
		System.out.println(canConstruct("eeeeeeeeeeeeeeeeeeeeeeeeeeeef", new String[] {"e", "ee", "eee", "eeee", "eeeee", "eeeee"}));
	}

	private static boolean canConstruct(String target, String[] wordBank) {
		final Map<String, Boolean> cache = new HashMap<>();
		return canConstruct(target, wordBank, cache);
	}

	private static boolean canConstruct(String target, String[] wordBank, Map<String, Boolean> cache) {
		if (cache.get(target) != null) return cache.get(target);
		if (target.isEmpty()) return true;
		for (String s : wordBank) {
			if (target.startsWith(s) && canConstruct(target.substring(s.length()), wordBank, cache)) {
				cache.put(target, true);
				return true;
			}
		}
		cache.put(target, false);
		return false;
	}
}
