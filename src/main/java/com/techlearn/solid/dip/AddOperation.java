package com.techlearn.solid.dip;
/**
 * @author Uthiraraj Saminathan
 *
 */
public class AddOperation implements CalculateOperation{

	@Override
	public int calculate(int a, int b) {
		return a+b;
	}
}
