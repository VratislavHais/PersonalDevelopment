package com.vhais;

import java.util.Deque;
import java.util.LinkedList;

public class Main {

	public static void main(String[] args) {
		System.out.println(calculate("(1+2)*4"));
	}

	public static int calculate(String s) {
		Deque<Character> operators = new LinkedList<>();
		Deque<Integer> operands = new LinkedList<>();
		int number = 0;
//		char prevSign = '+';
		char[] chArr = s.toCharArray();

		for (int i = 0; i < chArr.length; i++) {
			if (Character.isDigit(chArr[i])) {
				number = 0;
				while (i < chArr.length && Character.isDigit(chArr[i])) {
					number = number * 10 + Character.getNumericValue(chArr[i++]);
				}
				operands.push(number);
			}
			if (i + 1 >= chArr.length) break;
			if (chArr[i] == ' ') continue;
			if (!operators.isEmpty() && shouldExecute(operators.peek(), chArr[i])) {
				execute(operators.pop(), operands);
			}
			operators.push(chArr[i]);
		}
		while (!operators.isEmpty()) execute(operators.pop(), operands);
		return operands.pop();
	}

	public static boolean shouldExecute(char prev, char cur) {
		if (cur == '(') return false;
		else return (prev != '+' && prev != '-') || (cur != '*' && cur != '/');
	}

	public static void execute(char operator, Deque<Integer> operands) {
		int operand1 = operands.pop(), operand2 = operands.pop();
		switch (operator) {
			case '+':
				operands.push(operand1 + operand2);
				break;
			case '-':
				operands.push(operand1 - operand2);
				break;
			case '*':
				operands.push(operand1 * operand2);
				break;
			case '/':
				operands.push(operand1 / operand2);
				break;
		}
	}
}
