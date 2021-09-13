package com.vhais;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		System.out.println(complexNumberMultiply("1+1i", "1+1i"));
	}

	private static final int REAL = 0;
	private static final int IM = 1;

	public static String complexNumberMultiply(String s1, String s2) {
		int[] num1 = stringToComplex(s1), num2 = stringToComplex(s2);
		int[] multiplied = {
				num1[REAL]*num2[REAL] + (-1 * num1[IM] * num2[IM]),
				num1[REAL]*num2[IM] + num1[IM]*num2[REAL]
		};
		return String.format("%s+%si", multiplied[REAL], multiplied[IM]);
	}

	private static int[] stringToComplex(String s) {
		String[] split = s.substring(0, s.length() - 1).split("\\+");
		return new int[] {Integer.parseInt(split[0]), Integer.parseInt(split[1])};
	}
}
