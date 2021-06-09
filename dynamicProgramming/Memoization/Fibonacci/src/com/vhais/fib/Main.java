package com.vhais.fib;

import java.util.HashMap;

public class Main {

	public static void main(String[] args) {
		HashMap<Long, Long> cache = new HashMap<>();
//		System.out.println(fibonacci(50));
		System.out.println(fibonacciCache(50, cache));
	}

	private static long fibonacciCache(long n, HashMap<Long, Long> cache) {
		if (cache.get(n) != null) return cache.get(n);
		if (n <= 2) return 1;
		cache.put(n, fibonacciCache(n - 1, cache) + fibonacciCache(n - 2,cache));
		return cache.get(n);
	}

	private static long fibonacci(long n) {
		if (n <= 2) return 1;
		return fibonacci(n-1) + fibonacci(n-2);
	}
}
