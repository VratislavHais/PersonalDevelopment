package com.vhais.tep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		int[] arr = new int[] {1,0,1,0,1};
		int[] arr2 = new int[] {1,0,0,0,0,0,0,0,0,0,1,1};
		int[] arr3 = new int[] {1,1,0,1,1};
		int[] arr4 = new int[] {1,1,0,0,1};
		int[] arr5 = new int[] {0,0,0,0,0};
		int[] arr6 = new int[] {1,0,0};
		Arrays.stream(threeEqualParts(arr)).forEach(i -> System.out.print(i + " "));
		System.out.println();
		Arrays.stream(threeEqualParts(arr2)).forEach(i -> System.out.print(i + " "));
		System.out.println();
		Arrays.stream(threeEqualParts(arr3)).forEach(i -> System.out.print(i + " "));
		System.out.println();
		Arrays.stream(threeEqualParts(arr4)).forEach(i -> System.out.print(i + " "));
		System.out.println();
		Arrays.stream(threeEqualParts(arr5)).forEach(i -> System.out.print(i + " "));
		System.out.println();
		Arrays.stream(threeEqualParts(arr6)).forEach(i -> System.out.print(i + " "));
	}

	public static int[] threeEqualParts(int[] arr) {
		List<Integer> ones = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == 1) ones.add(i);
		}
		if (ones.isEmpty()) return new int[] {0, arr.length - 1};
		if (ones.size() % 3 > 0) return new int[] {-1, -1};
		int s = ones.get(0), i = ones.get(ones.size() / 3), j = ones.get((ones.size() / 3) * 2);
		while (j < arr.length && arr[s] == arr[i] && arr[s] == arr[j]) {
			s++;
			i++;
			j++;
		}
		if (j == arr.length) {
			return new int[] {s - 1, i};
		}
		return new int[] {-1, -1};
	}

//	public static int[] threeEqualParts(int[] arr) {
//		int[][] dp = new int[arr.length + 1][arr.length + 1];
//		int[] result = new int[] {-1, -1};
//		int start = 0;
//		int target = 0;
//		while (start < arr.length && arr[start] != 1) start++;
//		if (start == arr.length) return new int[] {0, arr.length - 1};
//		for (int i = start; i < arr.length; i++) {
//			target = constructVal(arr, start, i, dp);
//			for (int j = i + 1; j < arr.length; j++) {
//				if (constructVal(arr, i + 1, j, dp) == target) {
//					if (constructVal(arr, j + 1, arr.length - 1, dp) == target) {
//						return new int[] {i, j + 1};
//					}
//				}
//			}
//		}
//
//		return result;
//	}
//
//	private static int constructVal(int[] arr, int start, int end, int[][] dp) {
//		if (dp[start][end] != 0) return dp[start][end];
//		int val = 0;
//		for (int i = start; i <= end; i++) {
//			val = (val << 1) + arr[i];
//		}
//		dp[start][end] = val;
//		return val;
//	}
}
