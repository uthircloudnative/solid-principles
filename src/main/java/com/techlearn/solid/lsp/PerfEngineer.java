package com.techlearn.solid.lsp;
/**
 * @author Uthiraraj Saminathan
 *
 */
public class PerfEngineer implements IEngineer, IPerfEngineer {

	@Override
	public void logHours(float hours) {
		System.out.println("logHours Implementation");
	}

	@Override
	public void submitPerformanceGoals() {
		System.out.println("submitPerformanceGoals implementation");
	}
	
	@Override
	public void givePerformanceReview(String assignee) {
		System.out.println("givePerformanceReview implementation");
		
	}
}
