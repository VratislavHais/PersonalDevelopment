package com.vhais.reduce.size;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		int[] arr = new int[] {3,3,3,3,5,5,5,2,2,7};
		int[] arr2 = new int[] {7,7,7,7,7,7};
		int[] arr3 = new int[] {1,9};
		int[] arr4 = new int[] {1000,1000,3,7};
		int[] arr5 = new int[] {1,2,3,4,5,6,7,8,9,10};
		int[] arr6 = new int[] {9,77,63,22,92,9,14,54,8,38,18,19,38,68,58,19};
		int[] arr7 = new int[] {55,58,60,66,99,90,92,54,11,47,43,12,94,2,83,31,81,1,55,86,51,75,2,23,51,18,5,84,94,5,11,31,27,81,33,34,1,42,79,80,3,22,85,53,21,47,10,35,77,100,9,3,28,93};
		System.out.println(minSetSize(arr));
		System.out.println(minSetSize(arr2));
		System.out.println(minSetSize(arr3));
		System.out.println(minSetSize(arr4));
		System.out.println(minSetSize(arr5));
		System.out.println(minSetSize(arr6));
		System.out.println(minSetSize(arr7));
	}

//	public static int minSetSize(int[] arr) {
//		int start = 0, end = arr.length - 1, threshold = arr.length/2, positionStart = 0, positionEnd = 1, result = 0;
//		Arrays.sort(arr);
//		List<AtomicInteger> numberOfOccurences = new ArrayList<>(Arrays.asList(new AtomicInteger(0), new AtomicInteger(0)));
//		while (start < end) {
//			if (arr[start] == arr[end]) {
//				numberOfOccurences.add(new AtomicInteger(end - start + 1));
//			}
//			if (arr[start] != arr[start+1]) {
//				numberOfOccurences.get(positionStart).incrementAndGet();
//				positionStart = numberOfOccurences.size();
//				numberOfOccurences.add(new AtomicInteger(0));
//			} else {
//				numberOfOccurences.get(positionStart).incrementAndGet();
//			}
//			if (arr[end] != arr[end-1]) {
//				numberOfOccurences.get(positionEnd).incrementAndGet();
//				positionEnd = numberOfOccurences.size();
//				numberOfOccurences.add(new AtomicInteger(0));
//			} else numberOfOccurences.get(positionEnd).incrementAndGet();
//			start++; end--;
//		}
//		numberOfOccurences = numberOfOccurences.stream().sorted(Comparator.comparingInt(AtomicInteger::get).reversed()).collect(Collectors.toList());
//		while (threshold > 0) {
//			threshold -= numberOfOccurences.get(result).get();
//			result++;
//		}
//		return result;
//	}
	public static int minSetSize(int[] arr) {
		int half = arr.length / 2, result = 0;
		Map<Integer, Integer> occurrences = new HashMap<>();
		for (int number : arr) {
			if (occurrences.containsKey(number)) {
				occurrences.replace(number, occurrences.get(number) + 1);
			} else {
				occurrences.put(number, 1);
			}
		}
		List<Integer> sorted = new ArrayList<>(occurrences.values());
		Collections.sort(sorted, Collections.reverseOrder());
		while (half > 0) {
			half -= sorted.get(result);
			result++;
		}
		return result;
	}

}
