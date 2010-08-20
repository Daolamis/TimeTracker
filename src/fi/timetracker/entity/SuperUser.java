package fi.timetracker.entity;

/**
 * 
 * @author Petteri Parviainen
 *
 */
public class SuperUser extends Person {
	
	protected SuperUser(Integer id){
		super(id);
		super.setRole(Role.SUPERUSER);
	}
}