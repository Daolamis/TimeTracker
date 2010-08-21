package fi.timetracker.entity;

import java.util.List;
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
	private List<Integer> projects; //projektin pääavain, joka liitetty työntekijään
	
	public List<Integer> getProjects() {
		return projects;
	}
	public void setProjects(List<Integer> projects) {
		this.projects = projects;
	}
}