package com.vhais;

public class Main {

	public static void main(String[] args) {
		int[][] grid = new int[][] {
				{1, 1},
				{1, 0}
		};
		System.out.println(largestIsland(grid));
	}

//	public static int largestIsland(int[][] grid) {
//		int visitedToken = 1, largest = 0;
//		int[] visited = new int[grid.length * grid.length];
//		for (int row = 0; row < grid.length; row++) {
//			for (int col = 0; col < grid[0].length; col++) {
//				if (grid[row][col] == 0) {
//					largest = Math.max(largest, checkSize(row + 1, col, grid, visited, visitedToken) +
//							checkSize(row - 1, col, grid, visited, visitedToken) +
//							checkSize(row, col + 1, grid, visited, visitedToken) +
//							checkSize(row, col - 1, grid, visited, visitedToken) + 1);
//					visitedToken++;
//				}
//			}
//		}
//		return largest == 0 ? grid.length * grid.length : largest;
//	}
//
//	public static int checkSize(int row, int col, int[][] grid, int[] visited, int visitedToken) {
//		if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length ||
//				grid[row][col] == 0 || visited[row*grid.length + col] == visitedToken) return 0;
//		visited[row*grid.length + col] = visitedToken;
//		return 1 + checkSize(row + 1, col, grid, visited, visitedToken) + checkSize(row - 1, col, grid, visited, visitedToken) +
//				checkSize(row, col + 1, grid, visited, visitedToken) + checkSize(row, col - 1, grid, visited, visitedToken);
//	}

	public int largestIsland(int[][] grid) {
		int largest = 0;
		Component[] components = new Component[grid.length * grid.length];
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[0].length; col++) {
				if (grid[row][col] == 0) largest = Math.max(largest, componentSize(row, col, grid, components, null) + 1);
			}
		}
		return largest == 0 ? grid.length * grid.length : largest;
	}

	private Component componentSize(int row, int col, int[][] grid, Component[] components, Component current) {
		if (row < 0 || row >= grid.length || col < 0 || col >= grid.length || (current != null && grid[row][col] == 0)) return null;
		int index = row * grid.length + col;
		if (components[index] != null) return components[index];
		if (current == null) {
			current = new Component();
		} else {
			components[index] = current;
		}
		current.increment();
		componentSize(row + 1, col, grid, components, current);
		componentSize(row, col + 1, grid, components, current);
		componentSize(row - 1, col, grid, components, current);
		componentSize(row, col - 1, grid, components, current);
		return current;
	}

	class Component {
		private int size = 0;

		public void increment() {
			this.size++;
		}

		public int size() {
			return this.size;
		}
	}
}
