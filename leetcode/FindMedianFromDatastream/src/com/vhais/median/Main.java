package com.vhais.median;

public class Main {

	public static void main(String[] args) {
		MedianFinderFaster mf = new MedianFinderFaster();
		mf.addNum(6);
		mf.addNum(10);
		mf.addNum(2);
		mf.addNum(6);
		mf.addNum(5);
		mf.addNum(0);
		mf.addNum(6);
		mf.addNum(3);
		mf.addNum(1);
		mf.addNum(0);
		mf.addNum(0);
		System.out.println(mf.findMedian());
	}
}
