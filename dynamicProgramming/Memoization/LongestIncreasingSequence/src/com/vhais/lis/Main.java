package com.vhais.lis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		final int[] items = new int[] {3, 4, -1, 0, 6, 2, 3};
		final int[] dpTable = getDPTable(items);
		Arrays.stream(dpTable).forEach(l -> System.out.print(" " + l));
		System.out.println();
		getSequence(dpTable, items).forEach(i -> System.out.print(" " + i));
	}

	private static int[] getDPTable(int[] items) {
		final int[] dpTable = new int[items.length];
		// init with one because for every item we will have at least LIS of size 1
		Arrays.fill(dpTable, 1);
		int frontIndex = 1;
		int backIndex = 0;
		while (frontIndex < items.length) {
			if (items[backIndex] < items[frontIndex]) {
				dpTable[frontIndex] = Math.max(dpTable[frontIndex], dpTable[backIndex] + 1);
			}
			backIndex++;
			if (backIndex == frontIndex) {
				frontIndex++;
				backIndex = 0;
			}
		}
		return dpTable;
	}

	private static List<Integer> getSequence(int[] dpTable, int[] items) {
		final Stack<Integer> stack = new Stack<>();
		int maxLength = Arrays.stream(dpTable).max().getAsInt();
		int index = dpTable.length - 1;
		while (dpTable[index] != maxLength) {
			index--;
		}
		stack.push(items[index]);
		maxLength--;
		int currentItem = items[index];
		while (maxLength != 0) {
			index--;
			if (dpTable[index] == maxLength && items[index] < currentItem) {
				currentItem = items[index];
				stack.push(currentItem);
				maxLength--;
			}
		}
		final List<Integer> result = new ArrayList<>();
		while (!stack.isEmpty()) {
			result.add(stack.pop());
		}
		return result;
	}
}
