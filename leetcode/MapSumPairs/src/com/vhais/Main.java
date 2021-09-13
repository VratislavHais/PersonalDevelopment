package com.vhais;

public class Main {

	public static void main(String[] args) {
		MapSum mapSum = new MapSum();
		mapSum.insert("apple", 3);
		System.out.println(mapSum.sum("ap"));
		mapSum.insert("app", 2);
		System.out.println(mapSum.sum("ap"));
	}
}
