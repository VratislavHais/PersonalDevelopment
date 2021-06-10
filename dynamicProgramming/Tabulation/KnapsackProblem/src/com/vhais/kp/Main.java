package com.vhais.kp;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		final String[] items = new String[] {"item1", "item2", "item3", "item4"};
		final int[] weights = new int[] {1, 3, 4, 5};
		final int[] values = new int[] {1, 4, 5, 7};
		final int knapsackSize = 7;
		final int[][] combinationsMatrix = generateCombinationsMatrix(weights, values, knapsackSize);
		System.out.print("Optimal combination of items is:");
		getItemsToTake(combinationsMatrix, items, values).forEach(i -> System.out.print(" " + i));
		System.out.println();
		printTable(combinationsMatrix);
	}

	private static int[][] generateCombinationsMatrix(int[] weights, int[] values, int knapsackSize) {
		final int[][] combinationsMatrix = new int[weights.length][knapsackSize + 1];
		// initialize with 0 knapsackSize
		for (int row = 0; row < weights.length; row++) {
			combinationsMatrix[row][0] = 0;
		}
		for (int size = 1; size <= knapsackSize; size++) {
			if (weights[0] > size) {
				combinationsMatrix[0][size] = 0;
			} else {
				combinationsMatrix[0][size] = values[0];
			}
		}
		for (int row = 1; row < weights.length; row++) {
			for (int size = 1; size <= knapsackSize; size++) {
				if (weights[row] <= size) {
					combinationsMatrix[row][size] = Math.max(values[row] + combinationsMatrix[row-1][size-weights[row]], combinationsMatrix[row-1][size]);
				} else {
					combinationsMatrix[row][size] = combinationsMatrix[row-1][size];
				}
			}
		}
		return combinationsMatrix;
	}

	private static List<String> getItemsToTake(int[][] combinationsMatrix, String[] items, int[] values) {
		int row = values.length - 1;
		int col = combinationsMatrix[0].length - 1;
		final List<String> result = new ArrayList<>();
		while (row > 0 && col > 0) {
			if (combinationsMatrix[row][col] == combinationsMatrix[row-1][col]) {
				row--;
			} else {
				result.add(items[row]);
				row--;
				col -= values[row];
			}
		}
		return result;
	}

	private static void printTable(int[][] combinations) {
		final StringBuilder sb = new StringBuilder();
		for (int row = 0; row < combinations.length; row++) {
			sb.append("+");
			for (int col = 0; col < combinations[0].length; col++) {
				sb.append("---+");
			}
			sb.append("\n");
			for (int col = 0; col < combinations[0].length; col++) {
				if (col == 0) {
					sb.append("|");
				}
				sb.append(" " + combinations[row][col] + " |");
			}
			sb.append("\n");
		}
		sb.append("+");
		for (int col = 0; col < combinations[0].length; col++) {
			sb.append("---+");
		}
		System.out.println(sb.toString());
	}

}
