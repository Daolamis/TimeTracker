package fi.timetracker.entity;

import java.util.Date;

/** 
 * @author Petteri Parviainen
 */
public class WorkHour extends Entity{
	
	private Integer projectId;
	private Integer workerId;
	private Integer hourTypeId;
	
	private boolean overtime;
	private Date workDate;
	private double amount;
	
	public WorkHour(){
		super(null);
	}
	
	public WorkHour(Integer id){
		super(id);
	}		
	
	public boolean isOvertime() {
		return overtime;
	}
	public void setOvertime(boolean overtime) {
		this.overtime = overtime;
	}
	public Date getWorkDate() {
		return workDate;
	}
	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getWorkerId() {
		return workerId;
	}

	public void setWorkerId(Integer workerId) {
		this.workerId = workerId;
	}

	public Integer getHourTypeId() {
		return hourTypeId;
	}

	public void setHourTypeId(Integer hourTypeId) {
		this.hourTypeId = hourTypeId;
	}
}