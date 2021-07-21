package com.vhais;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		int[] arr = new int[] {1,2,3};
		System.out.println(closestIntSameBitCount(8));
		Solution solution = new Solution(arr);
		print(solution.shuffle());
		print(solution.shuffle());
		print(solution.reset());
		print(solution.shuffle());
	}

	private static void print(int[] arr) {
		Arrays.stream(arr).forEach(i -> System.out.print(i + " "));
		System.out.println();
	}

	private static long closestIntSameBitCount(long x) {
		long notSet = ~x & (x+1);
		long set = x & ~(x-1);
		if (notSet > set) {
			x |= notSet;
			x ^= notSet >> 1;
		} else {
			x ^= set;
			x |= set >> 1;
		}
		return x;
	}
}
