package com.vhais.decode;

import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		System.out.println(numDecodings("1*")); //
		System.out.println(numDecodings("2*")); //
		System.out.println(numDecodings("11106")); // 2
		System.out.println(numDecodings("***")); // 999
		System.out.println(numDecodings("6*66*6")); // 121
		System.out.println(numDecodings("2839")); // 1
	}

//	public static int numDecodings(String s) {
//		return (int) (countCombinations(s, 0, new HashMap<>()) % 1000000007);
//	}

	private static long countCombinations(String s, int position, Map<String, Long> cache) {
		String sub = s.substring(position);
		if (cache.containsKey(sub)) return cache.get(sub);
		if (position >= s.length()) return 1L;
		else if (s.charAt(position) == '0') return 0L;
		int multiplier = s.charAt(position) == '*' ? 9 : 1;
		long countSingle = multiplier * countCombinations(s, position+1, cache) % 1000000007;
		long countDouble = 0L;
		// process two chars
		if (position + 1 < s.length()) {
			if (s.charAt(position+1) == '*') {
				if (s.charAt(position) == '*') countDouble = 15;
				else if (s.charAt(position) == '1') countDouble = 9;
				else if (s.charAt(position) == '2') countDouble = 6;
			} else if (s.charAt(position) == '*') {
				if (s.charAt(position+1) <= '6') countDouble = 2;
				else countDouble++;
			}
			else if (s.charAt(position) == '1') {
				countDouble++;
			} else if (s.charAt(position) == '2' && s.charAt(position + 1) < '7') countDouble++;
			else {
				cache.put(sub, countSingle);
				return countSingle;
			}
			countDouble = countDouble * countCombinations(s, position + 2, cache) % 1000000007;
		}
		cache.put(sub, (countSingle + countDouble) % 1000000007);
		return cache.get(sub);






//		if (chararray[position] == '*') {
//			for (int i = 1; i < 10; i++) {
//				chararray[position] = Character.forDigit(i, 10);
//				count += countCombinations(chararray, position);
//			}
//			chararray[position] = '*';
//			return count;
//		}
//		count += countCombinations(chararray, position+1);
//		if (position + 1 < chararray.length && chararray[position] < 3 + '0') {
//			if (chararray[position+1] == '*' && chararray[position] == '2') count += 5;
//			else if (chararray[position+1] == '*' && chararray[position] == '1') {
//				count += 8;
//			}
//			count += countCombinations(chararray, position + 2);
//		}
//		return count;
	}

	public static int numDecodings(String s) {
		long[] dp = new long[s.length() + 1];
		if (s.charAt(0) == '0') return 0;
		int mod = 1000000007;
		dp[0] = 1;
		dp[1] = s.charAt(0) == '*' ? 9 : 1;
		for (int i = 2; i <= s.length(); i++) {
			char current = s.charAt(i - 1), previous = s.charAt(i - 2);
			if (current == '*') dp[i] = dp[i] + ((9 * dp[i-1]) % mod) % mod;
			else if (current != '0') dp[i] = (dp[i] + dp[i-1]) % mod;
			if (previous == '*') {
				if (current == '*') {
					dp[i] = (dp[i] + (15 * dp[i-2]) % mod) % mod;
				} else if (current <= '6') {
					dp[i] = (dp[i] + (2 * dp[i-2]) % mod) % mod;
				} else {
					dp[i] = (dp[i] + dp[i-2]) % mod;
				}
			} else if (previous == '1') {
				if (current == '*') {
					dp[i] = (dp[i] + (9 * dp[i-2]) % mod) % mod;
				} else {
					dp[i] = (dp[i] + dp[i-2]) % mod;
				}
			} else if (previous == '2') {
				if (current == '*') {
					dp[i] = (dp[i] + (dp[i-2] * 6) % mod) % mod;
				} else {
					dp[i] = (dp[i] + dp[i-2]) % mod;
				}
			}
		}
		return (int) dp[dp.length - 1];
	}
}
