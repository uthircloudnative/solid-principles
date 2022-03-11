package com.techlearn.antipattern.lsp;
/**
 * @author Uthiraraj Saminathan
 *
 */
public abstract class Engineer {
	
	public abstract void logHours(float hours);
	
	public abstract void submitPerformanceGoals();
	
	public abstract void reviewCode();
	
	public abstract void shareTestScripts(String assignee);
	
	public abstract void givePerformanceReview(String assignee);

}
