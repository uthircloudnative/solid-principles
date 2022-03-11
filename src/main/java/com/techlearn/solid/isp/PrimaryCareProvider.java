package com.techlearn.solid.isp;
/**
 * @author Uthiraraj Saminathan
 *
 */
public class PrimaryCareProvider implements IDoctor{

	@Override
	public void checkPatient() {
		System.out.println("checkPatient implementation");
	}

	@Override
	public void prescribeMedicine() {
		System.out.println("prescribeMedicine implementation");
	}
}
