package com.vhais;

public class Main {

	public static void main(String[] args) {
		String dominoes = ".L.R...LR..L..";
		System.out.println(pushDominoes(dominoes));
	}
// my solution. Works but it's slow and ugly
//	public static String pushDominoes(String dominoes) {
//		char[] chararray = dominoes.toCharArray();
//		boolean changesDone = false;
//		do {
//			char[] copy = chararray.clone();
//			changesDone = false;
//			for (int i = 0; i < chararray.length; i++) {
//				if (chararray[i] == '.') {
//					if (i == 0) {
//						if (i + 1 < chararray.length && chararray[i+1] == 'L') {
//							copy[i] = 'L';
//							changesDone = true;
//						}
//						continue;
//					} else if (i + 1 == chararray.length) {
//						if (chararray[i - 1] == 'R') {
//							copy[i] = 'R';
//							changesDone = true;
//						}
//						continue;
//					}
//					if (chararray[i-1] == 'R' && chararray[i+1] != 'L') {
//						copy[i] = 'R';
//						changesDone = true;
//					} else if (chararray[i-1] != 'R' && chararray[i+1] == 'L') {
//						copy[i] = 'L';
//						changesDone = true;
//					}
//				}
//			}
//			chararray = copy;
//		} while (changesDone);
//		return new String(chararray);
//	}

// solution after reading hints
	public static String pushDominoes(String dominoes) {
		int[] forces = new int[dominoes.length()];
		int force = 0;
		for (int i = 0; i < dominoes.length(); i++) {
			if (dominoes.charAt(i) == 'R') force = dominoes.length();
			else if (dominoes.charAt(i) == 'L') force = 0;
			else force = Math.max(force - 1, 0);
			forces[i] += force;
		}
		force = 0;
		for (int j = dominoes.length() - 1; j >= 0; j--) {
			if (dominoes.charAt(j) == 'L') force = dominoes.length();
			else if (dominoes.charAt(j) == 'R') force = 0;
			else force = Math.max(force - 1, 0);
			forces[j] -= force;
		}

		StringBuilder solution = new StringBuilder();
		for (int i : forces) {
			if (i > 0) solution.append("R");
			else if (i < 0) solution.append("L");
			else solution.append(".");
		}
		return solution.toString();
	}
}
