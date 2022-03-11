package com.techlearn.antipattern.dip;
/**
 * @author Uthiraraj Saminathan
 *
 */
public class Calculator {
	
	public enum Operation{
		ADD, SUBTRACT
	}

	public int calculate(int a, int b, Operation operation) {
		int total = 0;
		if(operation.name().equals("ADD")) {
			//Tight coupling
			AddOperation addOperation = new AddOperation();
			total = addOperation.add(a, b);
		}else if(operation.name().equals("SUBTRACT")) {
			//Tight coupling
			SubtractOperation subOperation = new SubtractOperation();
			total = subOperation.subtract(a, b);
		}
		return total;
	}
}
