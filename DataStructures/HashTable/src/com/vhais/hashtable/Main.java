package com.vhais.hashtable;

public class Main {

	public static void main(String[] args) {
		HashMap<String, Integer> hashMap = new HashMap<>();
		hashMap.put("jedna", 1);
		hashMap.put("dva", 2);
		hashMap.put("tri", 3);
		hashMap.put("ctyri", 4);
		hashMap.put("pet", 5);
		hashMap.put("sest", 6);
		hashMap.put("sedm", 7);
		hashMap.put("osm", 8);
		hashMap.put("devet", 9);
		hashMap.put("deset", 10);
		System.out.println(hashMap.remove("osm"));
		hashMap.entries().forEach(entry -> {
			System.out.print("key: " + entry.key + " value: " + entry.value);
			System.out.println();
		});
		hashMap.values().forEach(System.out::println);
		hashMap.keys().forEach(System.out::println);
		System.out.println(hashMap.containsKey("jedna"));
		hashMap.clear();
		System.out.println(hashMap.isEmpty());
	}
}
