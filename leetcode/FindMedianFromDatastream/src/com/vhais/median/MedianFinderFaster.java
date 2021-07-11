package com.vhais.median;

import java.util.Collections;
import java.util.PriorityQueue;

public class MedianFinderFaster {
	private final PriorityQueue<Integer> maxQ;
	private final PriorityQueue<Integer> minQ;

	public MedianFinderFaster() {
		maxQ = new PriorityQueue<>(Collections.reverseOrder());
		minQ = new PriorityQueue<>();
	}

	public void addNum(int number) {
		maxQ.offer(number);
		minQ.offer(maxQ.poll());
		if (minQ.size() > maxQ.size()) maxQ.offer(minQ.poll());
	}

	public double findMedian() {
		if (maxQ.size() == minQ.size()) return (maxQ.peek() + minQ.peek()) * 0.5;
		return (double) maxQ.peek();
	}
}
