package com.vhais.isometric;

import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		System.out.println(isIsomorphic("add", "egg"));
		System.out.println(isIsomorphic("foo", "bar"));
		System.out.println(isIsomorphic("paper", "title"));
		System.out.println(isIsomorphic("badc", "baba"));
	}

//	public static boolean isIsomorphic(String s, String t) {
//		Map<Character, Character> mapping = new HashMap<>();
//		for (int i = 0; i < s.length(); i++) {
//			char sChar = s.charAt(i), tChar = t.charAt(i);
//			if (!mapping.containsKey(sChar) && mapping.containsValue(tChar)
//					|| mapping.containsKey(sChar) && tChar != mapping.get(sChar)) return false;
//			mapping.put(sChar, tChar);
//		}
//		return true;
//	}

	// faster
	public static boolean isIsomorphic(String s, String t) {
		Map<Character, Character> mappingS = new HashMap<>();
		Map<Character, Character> mappingT = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			char sChar = s.charAt(i), tChar = t.charAt(i);
			if (mappingS.containsKey(sChar)) {
				if (tChar != mappingS.get(sChar)) return false;
			}
			if (mappingT.containsKey(tChar)) {
				if (sChar != mappingT.get(tChar)) return false;
			}
			mappingS.put(sChar, tChar);
			mappingT.put(tChar, sChar);
		}
		return true;
	}
}
