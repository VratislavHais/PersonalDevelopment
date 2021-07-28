package com.vhais;

public class Main {

	public static void main(String[] args) {
		System.out.println(findIntegers(230));
	}

//	we are checking only legit numbers. Complexity O(log n)
	public static int findIntegers(int n) {
		return 1 + countValid(1, n);
	}

	public static int countValid(int value, int max) {
		boolean endsWithOne = (value & 1) > 0;
		if (value > max) return 0;
		else if (endsWithOne) return 1 + countValid(value << 1, max);
		else return 1 + countValid((value << 1) | 1, max) + countValid(value << 1, max);
	}

// too slow. O(n)
//	public static int findIntegers(int n) {
//		int result = 1;
//		for (int i = 1; i <= n; i++) result += containsConsecutiveOnes(i) ? 0 : 1;
//		return result;
//	}
//
//	public static boolean containsConsecutiveOnes(int numberToCheck) {
//		return ((numberToCheck >>> 1) & numberToCheck) > 0;
//	}
}
