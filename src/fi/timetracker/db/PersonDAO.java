package fi.timetracker.db;

import java.util.List;
import java.util.Set;

import fi.timetracker.entity.Person;

/**
 * 
 * @author Petteri Parviainen
 *
 */
public interface PersonDAO {
	
	public Person savePerson(Person person);
	
	public List<Person> findPersons(String firstname, String lastname, String email, Set<Integer> projects);
	
	public Person getPerson(int id);
}