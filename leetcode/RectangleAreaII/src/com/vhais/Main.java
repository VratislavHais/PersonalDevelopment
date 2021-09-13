package com.vhais;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		int[][] rectangles = {{0,0,2,2},{1,0,2,3},{1,0,3,1}};
		System.out.println(rectangleArea(rectangles));
	}

	private static Set<Integer> components = new HashSet<>();

	public static int rectangleArea(int[][] rectangles) {
		for (int[] rectangle : rectangles) {
			createComponents(rectangle);
		}
		return components.size();
	}

	private static void createComponents(int[] rectangle) {
		createComponent(rectangle, rectangle[0], rectangle[1]);
	}

	private static void createComponent(int[] rectangleWhole, int x, int y) {
		if (isValid(rectangleWhole, x, y)) {
			Component c = new Component(x, y);
			components.add(new Component(x, y).hashCode());
			createComponent(rectangleWhole, x + 1, y);
			createComponent(rectangleWhole, x, y + 1);
		}
	}

	private static boolean isValid(int[] rect, int x, int y) {
		return rect[2] > x && rect[3] > y;
	}

	static class Component {
		int x, y;

		public Component(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}
	}
}
