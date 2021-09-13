package com.vhais;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Main {

	public static void main(String[] args) {
//		int[] start = {1,3,3,3,5,5,13,5};
		int[] start = {1,2,3,4,6};
//		int[] end = {17,14,8,11,14,7,17,9};
		int[] end = {3,5,10,6,9};
//		int[] profit = {9,3,7,18,2,17,4,6};
		int[] profit = {20,20,100,70,60};
//		quickSort(end, 0, end.length-1, start, profit);
//		System.out.println(Math.abs(Arrays.binarySearch(end, 6) + 1));
		System.out.println(jobSchedulingBetter(start, end, profit));
	}

	public static void quickSort(int[] arr, int begin, int end, int[] endA, int[] profit) {
		if (begin < end) {
			int partitionIndex = partition(arr, begin, end, endA, profit);

			quickSort(arr, begin, partitionIndex-1, endA, profit);
			quickSort(arr, partitionIndex+1, end, endA, profit);
		}
	}

	private static int partition(int[] arr, int begin, int end, int[] endA, int[] profit) {
		int pivot = arr[end];
		int i = (begin-1);

		for (int j = begin; j < end; j++) {
			if (arr[j] <= pivot) {
				i++;

				swap(arr, i, j);
				swap(endA, i, j);
				swap(profit, i, j);
			}
		}

		swap(arr, i+1, end);
		swap(endA, i+1, end);
		swap(profit, i+1, end);

		return i+1;
	}

	private static void swap(int[] A, int i, int j) {
		int tmp = A[j];
		A[j] = A[i];
		A[i] = tmp;
	}


	public static int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
		int[] dp = new int[startTime.length + 1];
		int idx;
		for (int i = 1; i < dp.length; i++) {
			idx = Math.abs(Arrays.binarySearch(endTime, startTime[i-1]) + 1);
//			idx = Math.abs(Arrays.binarySearch(endTime, startTime[i]) + 1);
//			if (idx < 0) idx = Math.abs(idx)+1;
			dp[i] = Math.max(dp[i-1], dp[idx] + profit[i-1]);
			System.out.println(dp[i]);
		}
		return dp[dp.length-1];
	}

	private static final int START_TIME = 0;
	private static final int END_TIME = 1;
	private static final int PROFIT = 2;

	public static int jobSchedulingBetter(int[] startTime, int[] endTime, int[] profit) {
		int n = startTime.length, cur;
		int[][] jobs = new int[n][];
		for (int i = 0; i < n; i++) {
			jobs[i] = new int[] {startTime[i], endTime[i], profit[i]};
		}
		Arrays.sort(jobs, Comparator.comparingInt(a -> a[END_TIME]));
		TreeMap<Integer, Integer> dp = new TreeMap<>();
		dp.put(0,0);
		for (int[] job : jobs) {
			cur = dp.floorEntry(job[START_TIME]).getValue() + job[PROFIT];
			if (cur > dp.lastEntry().getValue()) dp.put(job[END_TIME], cur);
		}
		return dp.lastEntry().getValue();
	}
}
