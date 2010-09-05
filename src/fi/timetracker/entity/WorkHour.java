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
	private String amount;
	
	public WorkHour(){
		super(null);
	}
	
	public WorkHour(Integer id){
		super(id);
	}		
	
	public boolean getOvertime() {
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
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
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
	
	//Huoma tämä metodi on jsp-sivua varten
	public String getProjectAndHourType(){
		String reply = "";
		if(projectId != null && hourTypeId != null){
			reply = ""+projectId+"_"+hourTypeId;
		}		
		return reply;
	}

	//Huoma tämä metodi on jsp-sivua varten
	public void setProjectAndHourType(String value){
		if(value!= null){
			String[] split = value.split("_");
			if(split.length == 2){
				this.projectId = new Integer(split[0]);
				this.hourTypeId = new Integer(split[1]);
			}
		}
	}
}