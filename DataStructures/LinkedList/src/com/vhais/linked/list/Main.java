package com.vhais.linked.list;

import java.util.stream.IntStream;

public class Main {

	public static void main(String[] args) {
		final CustomLinkedList<Integer> customLinkedList = new CustomLinkedList<>();
		IntStream.range(0, 10).forEach(customLinkedList::add);
		customLinkedList.removeFirst();
		customLinkedList.removeLast();
		customLinkedList.removeLast();
		customLinkedList.remove(2);
		Object number = (Integer) 4;
		System.out.println(customLinkedList.contains(number));
		customLinkedList.remove(number);
		customLinkedList.forEach(System.out::println);
		System.out.println(customLinkedList.isEmpty());
		System.out.println(customLinkedList.contains(number));
		System.out.println(customLinkedList.size());
		System.out.println(customLinkedList.peekFirst());
		System.out.println(customLinkedList.peekLast());
	}
}
