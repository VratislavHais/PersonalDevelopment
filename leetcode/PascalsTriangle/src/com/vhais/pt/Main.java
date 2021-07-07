package com.vhais.pt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		generate(5).forEach(row -> {
			row.forEach(cell -> System.out.print(cell + " "));
			System.out.println();
		});
	}

	public static List<List<Integer>> generate(int numRows) {
		final List<List<Integer>> triangle = new ArrayList<>();
		if (numRows == 0) return triangle;
		triangle.add(new ArrayList<>(Collections.singletonList(1)));
		if (numRows > 1) triangle.add(new ArrayList<>(Arrays.asList(1, 1)));
		int cell;
		for (int row = 2; row < numRows; row++) {
			final List<Integer> cells = new ArrayList<>();
			cells.add(1);
			for (cell = 1; cell < row; cell++) {
				cells.add(triangle.get(row - 1).get(cell - 1) + triangle.get(row - 1).get(cell));
			}
			cells.add(1);
			triangle.add(cells);
		}
		return triangle;
	}
}
