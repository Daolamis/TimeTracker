package fi.timetracker.entity;

import java.util.List;
/**
 *  @author Petteri Parviainen
 */
public class HourType extends Entity{
	
	private String name;
	private String description;
	
	//Projektit, joissa ko. tuntityyppiä käytetään
	private List<Project> projects;
	
	//toimiala
	private String branchOfActivity;

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

	public String getBranchOfActivity() {
		return branchOfActivity;
	}

	public void setBranchOfActivity(String branchOfActivity) {
		this.branchOfActivity = branchOfActivity;
	}
}
