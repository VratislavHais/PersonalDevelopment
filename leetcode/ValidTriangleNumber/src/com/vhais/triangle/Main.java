package com.vhais.triangle;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		int[] arr1 = new int[] {1, 2, 3, 4, 5, 6, 7, 8};
		System.out.println(triangleNumber(arr1));
	}

	public static int triangleNumber(int[] nums) {
		Arrays.sort(nums);
		int result = 0, start, end;
		for (int i = 2; i < nums.length; i++) {
			start = 0;
			end = i - 1;
			while (start < end) {
				if (nums[i] < nums[start] + nums[end]) {
					result += (end - start);
					end--;
				} else {
					start++;
				}
			}
		}
		return result;
	}
}
