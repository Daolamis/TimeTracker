package fi.timetracker.entity;

import java.util.Date;

/**
 * 
 * @author Petteri Parviainen
 *
 */
public abstract class Entity {
	
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public int getId() {
		return id;
	}
	private int id;
	private Date updated;
}
