package fi.timetracker.entity;

import java.util.Set;
/**
 * 
 * @author Petteri Parviainen
 *
 */
public class Worker extends Person{
	
	protected Worker(Integer id){
		super(id);
		super.setRole(Role.WORKER);
	}	
	private Set<Integer> projects; //projektin pääavaimia (työntekijä kuuluu ko. projekteihin)
	
	public Set<Integer> getProjects() {
		return projects;
	}
	public void setProjects(Set<Integer> projects) {
		this.projects = projects;
	}
}