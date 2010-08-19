package fi.timetracker.entity;
/** 
 * @author Petteri Parviainen
 */
public class Manager extends Worker{
	
	protected Manager(){
		super.setRole(Role.MANAGER);
	}
}
