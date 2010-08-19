package fi.timetracker.entity;

import java.util.Date;

/** 
 * @author Petteri Parviainen
 */
public class WorkHour extends Entity{
	
	private Project project;
	private Worker worker;
	private HourType hourType;
	
	private boolean overtime;
	private Date workDate;
	private double amount;
	
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public Worker getWorker() {
		return worker;
	}
	public void setWorker(Worker worker) {
		this.worker = worker;
	}
	public HourType getHourType() {
		return hourType;
	}
	public void setHourType(HourType hourType) {
		this.hourType = hourType;
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
}