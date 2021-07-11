package com.vhais.median;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MedianFinder {
	private final List<Integer> list;

	public MedianFinder() {
		this.list = new ArrayList<>() {
			public boolean add(Integer value) {
				int index = Collections.binarySearch(list, value);
				int mask = index >> 31;
				index = index < 0 ? ((mask ^ index) - mask) - 1 : index;
				super.add(index, value);
				return true;
			}
		};
	}

	public void addNum(int num) {
		list.add(num);
	}

	public double findMedian() {
		int half = list.size() >> 1;
		if ((list.size() & 1) > 0) return (double) list.get(half);
		return (list.get(half) + list.get(half - 1)) / 2.0;
	}
}
