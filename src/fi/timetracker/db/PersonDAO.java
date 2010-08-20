package fi.timetracker.db;

import java.util.List;

import fi.timetracker.entity.Person;

/**
 * 
 * @author Petteri Parviainen
 *
 */
public interface PersonDAO {
	
	public Person savePerson(Person person);
	
	public List<Person> findPersons();
	
	public Person getPerson(int id);

}
