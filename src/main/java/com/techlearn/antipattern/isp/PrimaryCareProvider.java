package com.techlearn.antipattern.isp;
/**
 * @author Uthiraraj Saminathan
 *
 */
public class PrimaryCareProvider implements Doctor{

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
		// A Primary Care Doctor can't order for surgery. So this function is not needed
		//for PrimaryCareProvider but due to Doctor interface this class must implement it.
	}
}
