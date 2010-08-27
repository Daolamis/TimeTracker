package fi.timetracker.web;

import java.util.HashSet;
import java.util.Set;
/** 
 * @author Petteri Parviainen
 */
public class FindPersonsCommand {
	
	private String firstname;
	private String lastname;
	private String email;
	private Set<Integer> projects;
	
	public FindPersonsCommand(){
		this.projects = new HashSet<Integer>();
	}
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Set<Integer> getProjects() {
		return projects;
	}
	public void setProjects(Set<Integer> projects) {
		this.projects = projects;
	}
}