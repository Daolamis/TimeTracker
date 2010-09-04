package fi.timetracker.db;

import java.util.List;
import java.util.Set;

import fi.timetracker.entity.HourType;
import fi.timetracker.entity.Person;
import fi.timetracker.entity.Project;
import fi.timetracker.entity.WorkHour;

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
	
	public List<Project> getAllProjects(boolean onlyActive);
	
	public HourType saveHourType(HourType hourType);
	
	public HourType getHourType(Integer id);
	
	public List<HourType> getAllHourTypes();
	
	public boolean isEmailFree(Integer workerId, String email);
	
	public boolean changePassword(Person person, String oldPassword, String newPassword);
	
	public String generatePassword(Integer id);
	
	public List<WorkHour> getWorkHours(Integer workerId);
	
	public void saveWorkHour(WorkHour hour);
	
	public void deleteWorkHour(Integer workHourId);
}