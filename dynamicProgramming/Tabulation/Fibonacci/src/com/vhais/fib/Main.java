package com.vhais.fib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	private static final List<Long> cache = new ArrayList<>(Arrays.asList(0L, 1L));

	public static void main(String[] args) {
		System.out.println(fibonacci(6));
		System.out.println(fibonacci(7));
		System.out.println(fibonacci(8));
		System.out.println(fibonacci(50));
	}

	private static long fibonacci(int n) {
		while (n >= cache.size()) {
			// no need to worry about subtracting 2 and 1 from size as we always start with 2 values in ArrayList
			cache.add(cache.get(cache.size() - 2) + cache.get(cache.size() - 1));
		}
		return cache.get(n);
	}
}
