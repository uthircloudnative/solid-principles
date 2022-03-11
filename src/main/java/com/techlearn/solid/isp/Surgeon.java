package com.techlearn.solid.isp;
/**
 * @author Uthiraraj Saminathan
 *
 */
public class Surgeon implements IDoctor, ISurgeon{

	@Override
	public void checkPatient() {
		System.out.println("checkPatient implementation");
	}

	@Override
	public void prescribeMedicine() {
		System.out.println("prescribeMedicine implementation");
	}

	@Override
	public void orderSurgery() {
		System.out.println("orderSurgery implementation");
	}
}
