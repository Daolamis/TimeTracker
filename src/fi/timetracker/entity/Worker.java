package fi.timetracker.entity;

import java.util.List;

/**
 * 
 * @author Petteri Parviainen
 *
 */
public class Worker extends Person{
	
	private List<Project> projects;
	private List<WorkHour> workhours;

}
