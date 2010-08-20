package fi.timetracker.entity;

import java.util.Date;

/**
 * 
 * @author Petteri Parviainen
 *
 */
public abstract class Entity {
	private Integer id;
	private Date updated;
	
	protected Entity(Integer id){
		this.id = id;
	}
	
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public int getId() {
		return id;
	}
	
}
