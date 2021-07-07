package com.vhais.ttts;

import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		final String keyboard = "abcdefghijklmnopqrstuvwxyz";
		final Map<Character, Integer> keyboardLayoutMapping = new HashMap<>();
		for (int i = 0; i < keyboard.length(); i++) keyboardLayoutMapping.put(keyboard.charAt(i), i);
		final String text = "abcdeaz";
		int previousIndex = 0, time = 0;
		for (char c : text.toCharArray()) {
			time += Math.abs(previousIndex - keyboardLayoutMapping.get(c));
			previousIndex = keyboardLayoutMapping.get(c);
		}
		System.out.println(time);
	}
}
