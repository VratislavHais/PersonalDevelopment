package com.vhais;

import java.util.HashMap;
import java.util.Map;

public class Main {
	private static final Map<String, Boolean> cache = new HashMap<>();

	public static void main(String[] args) {
//		System.out.println(isPalindrome("ahoj"));
//		System.out.println(isPalindrome("phobiaibohp"));
//		System.out.println(isPalindrome("aa"));
		System.out.println(minCut("aab"));			// 1
		System.out.println(minCut("aa"));			// 0
		System.out.println(minCut("aabcc"));			// 2
		System.out.println(minCut("aaabaa"));		// 1
		System.out.println(minCut(
				"adabdcaebdcebdcacaaaadbbcadabcbeabaadcbcaaddebdbddcbdacdbbaedbdaaecabdceddccbdeeddccdaabbabbdedaaabcdadbdabeacbeadbaddcbaacdbabcccbaceedbcccedbeecbccaecadccbdbdccbcbaacccbddcccbaedbacdbcaccdcaadcbaebebcceabbdcdeaabdbabadeaaaaedbdbcebcbddebccacacddebecabccbbdcbecbaeedcdacdcbdbebbacddddaabaedabbaaabaddcdaadcccdeebcabacdadbaacdccbeceddeebbbdbaaaaabaeecccaebdeabddacbedededebdebabdbcbdcbadbeeceecdcdbbdcbdbeeebcdcabdeeacabdeaedebbcaacdadaecbccbededceceabdcabdeabbcdecdedadcaebaababeedcaacdbdacbccdbcece"
		));
	}

	public static int minCut(String s) {
		int[] cuts = new int[s.length()];
		boolean[][] palindrome = new boolean[s.length()][s.length()];
		int minCut = 0;
		for (int i = 0; i < s.length(); i++) {
			minCut = i;
			for (int j = 0; j <= i; j++) {
				// i - j < 2    => single character is always considered palindrome
				if (s.charAt(i) == s.charAt(j) && (i - j < 2 || palindrome[i - 1][j + 1])) {
					palindrome[i][j] = true;
					minCut = Math.min(minCut, j == 0 ? 0 : (cuts[j-1] + 1));
				}
			}
			cuts[i] = minCut;
		}
		return cuts[s.length() - 1];
	}
}
