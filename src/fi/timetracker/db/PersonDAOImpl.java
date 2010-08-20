package fi.timetracker.db;

import java.util.List;

import fi.timetracker.entity.Entity;
import fi.timetracker.entity.Person;

public class PersonDAOImpl extends AbstractDAO implements PersonDAO {

	@Override
	public Entity getById(int id) {
		return this.getPerson(id);
	}

	@Override
	public List<Person> findPersons() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person getPerson(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person savePerson(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

}
