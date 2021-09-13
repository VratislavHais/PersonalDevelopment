package com.vhais;

import java.util.HashMap;
import java.util.Map;

public class MapSum {
	final Map<String, Integer> map;

	public MapSum() {
		map = new HashMap<>();
	}

	public void insert(String key, int val) {
		map.put(key, val);
	}

	public int sum(String prefix) {
		return map.entrySet().stream().filter(entry -> entry.getKey().startsWith(prefix)).mapToInt(Map.Entry::getValue).sum();
	}
}
