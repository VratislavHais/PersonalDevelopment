package com.vhais.dynamic;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		final DynamicArray<Integer> dynamicArray = new DynamicArray<>(new Integer[]{1, 2, 3, 4, 5, 7, 8, 9});
		dynamicArray.add(6);
		dynamicArray.remove(9);
		dynamicArray.removeAt(3);
		dynamicArray.add(9);
		dynamicArray.indexOf(2);
		dynamicArray.forEach(i -> System.out.print(i + " "));
		System.out.println();
		System.out.println(dynamicArray.isEmpty());
		System.out.println(dynamicArray.size());
		System.out.println(dynamicArray.contains(6));
		System.out.println(dynamicArray.get(2));
	}
}
