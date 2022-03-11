package com.techlearn.solid.ocp;

import java.util.List;

import com.techlearn.antipattern.srm.User;

/**
 * @author Uthiraraj Saminathan
 *
 */
public interface ReportGenerator {
	
	void generateReport(List<User> user);

}
