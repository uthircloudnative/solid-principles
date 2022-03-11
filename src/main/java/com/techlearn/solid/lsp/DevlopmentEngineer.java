package com.techlearn.solid.lsp;
/**
 * @author Uthiraraj Saminathan
 *
 */
public class DevlopmentEngineer implements IEngineer, IDevEngineer{

	@Override
	public void logHours(float hours) {
		System.out.println("logHours Implementation");
	}

	@Override
	public void submitPerformanceGoals() {
		System.out.println("submitPerformanceGoals implementation");
	}

	@Override
	public void reviewCode() {
		System.out.println("reviewCode implementation");
	}

}
