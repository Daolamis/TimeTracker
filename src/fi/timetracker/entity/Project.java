package fi.timetracker.entity;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author Petteri Parviainen
 *
 */
public class Project extends Entity{
	
	private enum ProjectStatus{ACTIVE, CLOSE};
	
	private String name;
	private String description;
	private ProjectStatus status;
	private Date created;
	
	private List<Worker> workers;
	private List<HourType> hourtypes;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ProjectStatus getStatus() {
		return status;
	}
	public void setStatus(ProjectStatus status) {
		this.status = status;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public List<Worker> getWorkers() {
		return workers;
	}
	public void setWorkers(List<Worker> workers) {
		this.workers = workers;
	}
	public List<HourType> getHourtypes() {
		return hourtypes;
	}
	public void setHourtypes(List<HourType> hourtypes) {
		this.hourtypes = hourtypes;
	}
	
}
