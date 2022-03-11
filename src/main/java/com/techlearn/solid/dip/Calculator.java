package com.techlearn.solid.dip;
/**
 * @author Uthiraraj Saminathan
 *
 */
public class Calculator {

	private CalculateOperation calculateOperation;

	public Calculator(CalculateOperation calculateOperation) {
		super();
		this.calculateOperation = calculateOperation;
	}
	
	public int calculate(int a, int b) {
		return calculateOperation.calculate(a, b);
	}
}
