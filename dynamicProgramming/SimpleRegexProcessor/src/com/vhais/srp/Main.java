package com.vhais.srp;

import java.util.HashMap;
import java.util.Map;

// check if the provided string can be matched with provided regular expression
// both of them must be equal not partially equal
// regex contains only literals, . (match every character), * (0 or multiple times)
// example: str = aabcc, regex = a*bb*.*  - does match
public class Main {

	public static void main(String[] args) {
		final String s = "aabcc";
		final String regex = "a*bb*.*";
		System.out.println(doesMatch(s, regex));
	}

	private static boolean doesMatch(String s, String regex) {
		final Map<String, Boolean> cache = new HashMap<>();
		return doesMatch(s, regex, 0, 0, cache);
	}

	private static boolean doesMatch(String s, String regex, int sIndex, int regexIndex, Map<String, Boolean> cache) {
		final String key = sIndex + "," + regexIndex;
		if (cache.get(key) != null) return  cache.get(key);
		if (sIndex >= s.length() && regexIndex >= regex.length()) return true;
		else if (regexIndex >= regex.length()) return false;
		boolean match = sIndex < s.length() && (s.charAt(sIndex) == regex.charAt(regexIndex) || regex.charAt(regexIndex) == '.');
		if (regex.length() > regexIndex + 1 && regex.charAt(regexIndex+1) == '*') {
			cache.put(key, doesMatch(s, regex, sIndex, regexIndex+2, cache) || (match && doesMatch(s, regex, sIndex+1, regexIndex, cache)));
		} else if (match) {
			cache.put(key, doesMatch(s, regex, sIndex+1, regexIndex+1, cache));
		} else {
			cache.put(key, false);
		}
		return cache.get(key);
	}
}
