package fi.timetracker.entity;

/**
 * 
 * @author Petteri Parviainen
 *
 */
public class SuperUser extends Person {
	
	protected SuperUser(){
		super.setRole(Role.SUPERUSER);
	}
}