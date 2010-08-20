package fi.timetracker.entity;
/** 
 * @author Petteri Parviainen
 */
public class Manager extends Worker{
	
	protected Manager(Integer id){
		super(id);
		super.setRole(Role.MANAGER);
	}
}
