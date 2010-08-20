package fi.timetracker.db;

import fi.timetracker.entity.Person;

/**
 * 
 * @author Petteri Parviainen
 *
 */
public class PasswordDaoImpl implements PasswordDAO {

	@Override
	public Person login(String login, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean changePassword(Person person, String oldPassword,
			String newPassword) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String generatePassword(Person person) {
		// TODO Auto-generated method stub
		return null;
	}
}