package fi.timetracker.db;

import fi.timetracker.entity.Person;

/**
 * 
 * @author Petteri Parviainen
 *
 */
public interface PasswordDAO {
	
	public Person login(String login, String password);
	
	public boolean changePassword(Person person, String oldPassword, String newPassword);
	
	public String generatePassword(Person person);
}
