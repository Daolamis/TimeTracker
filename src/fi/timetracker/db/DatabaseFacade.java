package fi.timetracker.db;

import java.util.List;
import java.util.Set;

import fi.timetracker.entity.HourType;
import fi.timetracker.entity.Person;
import fi.timetracker.entity.Project;

/** 
 * @author Petteri Parviainen
 */
public interface DatabaseFacade {
	
	public Person login(String userid, String password);
	
	public Person savePerson(Person person);
	
	public Person getPerson(Integer id);
	
	public List<Person> findPersons(String firstname, String lastname, String email, Set<Integer> projects);
	
	public Project saveProject(Project project);
	
	public Project getProject(Integer id);
	
	public List<Project> getAllProjects();
	
	public HourType saveHourType(HourType hourType);
	
	public HourType getHourType(Integer id);
	
	public List<HourType> getAllHourTypes();
	
	public void joinWorkerToProject(Integer workerId, Set<Integer> projects, Set<Integer> focusProjects);
	
	public boolean changePassword(Person person, String oldPassword, String newPassword);
	
	public String generatePassword(Person person);
}