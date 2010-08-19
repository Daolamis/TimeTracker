package fi.timetracker.entity;

import java.util.List;
/**
 * 
 * @author Petteri Parviainen
 *
 */
public class Worker extends Person{
	
	protected Worker(){
		super.setRole(Role.WORKER);
	}
	
	private List<Project> projects;
	private List<WorkHour> workhours;
	
	public List<Project> getProjects() {
		return projects;
	}
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	public List<WorkHour> getWorkhours() {
		return workhours;
	}
	public void setWorkhours(List<WorkHour> workhours) {
		this.workhours = workhours;
	}
	
	

}
