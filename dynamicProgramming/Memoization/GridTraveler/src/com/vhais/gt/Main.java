package com.vhais.gt;

import java.util.HashMap;
import java.util.Map;

/*
	Goal is to travel on m*n grid from top-left corner to bottom right corner.
	We can only travel in two directions - right and down
 */
public class Main {

	public static void main(String[] args) {
		System.out.println(travel(18,18));
	}

	public static long travel(int x, int y) {
		final Map<String, Long> cache = new HashMap<>();
		return travel(x, y, cache);
	}

	public static long travel(int x, int y, Map<String, Long> cache) {
		final String key = x + "," + y;
		final String keyReversed = y + "," + x;
		if (cache.get(key) != null) return cache.get(key);
		else if (cache.get(keyReversed) != null) return cache.get(keyReversed);
		if (x == 1 && y == 1) return 1;
		else if (x < 1 || y < 1) return 0;
		cache.put(key, travel(x-1, y, cache) + travel(x, y-1, cache));
		return cache.get(key);
	}
}
