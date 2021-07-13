package com.vhais.peak;

public class Main {

	public static void main(String[] args) {
		int[] arr1 = new int[] {1,2,1,3,5,6,4};	// 5
		int[] arr2 = new int[] {1,2,3,1};		// 2
		int[] arr3 = new int[] {2, 1};			// 0
		int[] arr4 = new int[] {1, 2};			// 1
		System.out.println(findPeakElement(arr1));
		System.out.println(findPeakElement(arr2));
		System.out.println(findPeakElement(arr3));
		System.out.println(findPeakElement(arr4));
	}

	public static int findPeakElement(int[] nums) {
		int start = 0, end = nums.length - 1;
		int mid = 0;
		while (start < end) {
			mid = (start + end) >> 1;
			if ((mid == 0 || nums[mid] > nums[mid - 1]) && (mid == nums.length - 1 || nums[mid] > nums[mid + 1])) return mid;
			else if (nums[mid] < nums[mid+1]) start = mid + 1;
			else end = mid - 1;
		}
		return start;
	}
}
