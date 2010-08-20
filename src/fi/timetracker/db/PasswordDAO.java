package fi.timetracker.db;

import fi.timetracker.entity.Person;

/**
 * 
 * @author Petteri Parviainen
 *
 */
public interface PasswordDAO {
	
	public Person login(String login, String password);
	
	public boolean changePassword(String oldPassword, String newPassword);

}
