package com.vhais.cn;
/*
	The first few Catalan numbers for n = 0, 1, 2, 3, … are 1, 1, 2, 5, 14, 42, 132, 429, 1430, 4862, …
	More info here: https://www.geeksforgeeks.org/program-nth-catalan-number/
 */
public class Main {

	public static void main(String[] args) {
		System.out.println(catalanNumber(2));
	}

	private static int catalanNumber(int n) {
		final Integer[] cache = new Integer[n+1];
		return catalanNumber(n, cache);
	}

	private static int catalanNumber(int n, Integer[] cache) {
		if (cache[n] != null) return cache[n];
		if (n <= 1) return 1;
		int i = 1;
		int current = 0;
		while (i < n) {
			current += catalanNumber(i, cache) * catalanNumber(n - i, cache);
			i++;
		}
		cache[n] = current;
		return current;
	}
}
