package fi.timetracker.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author Petteri Parviainen
 * 
 */
public class Project extends Entity {

	public enum ProjectStatus {
		ACTIVE('A'), CLOSED('C');
		
		private char code;
		private ProjectStatus(char code) {
			this.code = code;
		}
		
		public String getCode(){
			return ""+this.code;
		}
	};

	private String name;
	private String description;
	private ProjectStatus status;
	private Date created;

	private List<Worker> workers;
	private Set<Integer> hourtypes;
	
	public Project(){
		super(null);
	}
	
	public Project(Integer id){
		super(id);
	}

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
	
	public void setStatusFromCode(char code) {
		 switch (code) {
	      case 'A': 
	        this.status = ProjectStatus.ACTIVE;
	        break;   
	      case 'C': 
	    	  this.status = ProjectStatus.CLOSED;
		        break;
	      default:
	    	  throw new AssertionError("Unknown code: " + code);	        
	    }
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

	public Set<Integer> getHourtypes() {
		return hourtypes;
	}

	public void setHourtypes(Set<Integer> hourtypes) {
		this.hourtypes = hourtypes;
	}
}
