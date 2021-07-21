package com.vhais;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Solution {
	private int[] original, shuffled;
	private Random random = new Random();

	public Solution(int[] nums) {
		original = nums.clone();
		shuffled = nums.clone();
	}

	public int[] reset() {
		return original;
	}

	public int[] shuffle() {
		List<Integer> tmp = new ArrayList<>();
		for (int i : shuffled) tmp.add(i);
		int idx = 0;
		for (int i = 0; i < shuffled.length; i++) {
			idx = random.nextInt(tmp.size());
			shuffled[i] = tmp.get(idx);
			tmp.remove(idx);
		}
		return shuffled;
	}
}
