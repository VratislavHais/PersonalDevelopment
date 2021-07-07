package com.vhais.vowels.count;

import java.util.Arrays;

public class Main {
	public enum Vowels {
		A(0), E(1), I(2), O(3), U(4);

		private final int cachePosition;

		Vowels(final int cachePosition) {
			this.cachePosition = cachePosition;
		}

		public int getCachePosition() {
			return this.cachePosition;
		}
	}
	public static void main(String[] args) {
		int n = 144;
		int mod = 1000000007;
		long[][] cache = new long[n + 1][Vowels.values().length];
		long result = 0L;
//		recursive
		for (Vowels vowel : Vowels.values()) {
			result = result + count(n - 1, vowel, cache) % mod;
		}
//		non-recursive
//		Arrays.fill(cache[1], 1);
//		for (int permutation = 2; permutation <= n; permutation++) {
//			for (Vowels vowel : Vowels.values()) {
//				cache[permutation][vowel.getCachePosition()] = count(permutation, vowel, cache);
//			}
//		}
//		for (Vowels vowel : Vowels.values()) {
//			result += cache[n][vowel.getCachePosition()] % mod;
//		}
		System.out.println(result % mod);
	}

//	non-recursive
//	private static long count(int n, Vowels vowel, long[][] cache) {
//		int mod = 1000000007;
//		switch (vowel) {
//			case A:
//				return cache[n-1][Vowels.E.getCachePosition()] % mod;
//			case E:
//				return (cache[n-1][Vowels.A.getCachePosition()] % mod + cache[n-1][Vowels.I.getCachePosition()] % mod) % mod;
//			case I:
//				return (cache[n-1][Vowels.A.getCachePosition()] % mod +
//						cache[n-1][Vowels.E.getCachePosition()] % mod +
//						cache[n-1][Vowels.O.getCachePosition()] % mod +
//						cache[n-1][Vowels.U.getCachePosition()] % mod) % mod;
//			case O:
//				return (cache[n-1][Vowels.I.getCachePosition()] % mod + cache[n-1][Vowels.U.getCachePosition()] % mod) % mod;
//			case U:
//				return cache[n-1][Vowels.A.getCachePosition()] % mod;
//		}
//		return 0;
//	}

//	recursive
	private static long count(int n, Vowels vowel, long[][] cache) {
		if (cache[n][vowel.getCachePosition()] > 0) return cache[n][vowel.getCachePosition()];
		if (n == 0) {
			cache[n][vowel.getCachePosition()] = 1;
			return 1;
		}
		int mod = 1000000007;
		long cnt = 0;
		switch (vowel) {
			case A:
				cnt = count(n-1, Vowels.E, cache) % mod;
				break;
			case E:
				cnt = (count(n-1, Vowels.A, cache) % mod +
						count(n-1, Vowels.I, cache) % mod) % mod;
				break;
			case I:
				cnt = (count(n-1, Vowels.A, cache) % mod +
						count(n-1, Vowels.E, cache) % mod +
						count(n-1, Vowels.O, cache) % mod +
						count(n-1, Vowels.U, cache) % mod) % mod;
				break;
			case O:
				cnt = (count(n-1, Vowels.I, cache) % mod +
						count(n-1, Vowels.U, cache) % mod) % mod;
				break;
			case U:
				cnt = count(n-1, Vowels.A, cache) % mod;
		}
		cache[n][vowel.getCachePosition()] = cnt;
		return cnt;
	}
}
