package com.vhais;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

	public static void main(String[] args) {
		var result = solveNQueens(4);
		result.forEach(list -> {
			list.forEach(i -> System.out.print(i + ", "));
			System.out.println();
		});
	}

	public static List<List<String>> solveNQueens(int n) {
		List<List<String>> result = new ArrayList<>();
		solve(n, new ArrayList<>(), result);
		return result;
	}

	private static void solve(int n, List<Integer> queens, List<List<String>> result) {
		List<Integer> queensCopy = new ArrayList<>(queens);
		if (queensCopy.size() == n) {
			result.add(listOfQueensToOutput(queensCopy));
		} else {
			for (int candidate : candidates(n, queens)) {
				queensCopy.add(candidate);
				solve(n, queensCopy, result);
				queensCopy.remove(queensCopy.size() - 1);
			}
		}
	}

	private static Set<Integer> candidates(int n, List<Integer> queens) {
		Set<Integer> candidates = IntStream.range(0, n)
				.boxed().collect(Collectors.toSet());
		int distance = 0;
		for (int row = 0; row < queens.size(); row++) {
			distance = queens.size() - row;
			candidates.remove(queens.get(row));
			candidates.remove(queens.get(row) + distance);
			candidates.remove(queens.get(row) - distance);
		}
		return candidates;
	}

	private static List<String> listOfQueensToOutput(List<Integer> queens) {
		List<String> result = new ArrayList<>();
		String[] template = new String[queens.size()];
		Arrays.fill(template, ".");
		for (Integer queen : queens) {
			template[queen] = "Q";
			result.add(String.join("", template));
			template[queen] = ".";
		}
		return result;
	}
}
