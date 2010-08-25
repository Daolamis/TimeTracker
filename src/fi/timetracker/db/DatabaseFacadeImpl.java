package fi.timetracker.db;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fi.timetracker.entity.HourType;
import fi.timetracker.entity.Person;
import fi.timetracker.entity.Project;
/** 
 * @author Petteri Parviainen
 */
public class DatabaseFacadeImpl implements DatabaseFacade{
	
	private HourTypeDAO hourTypeDAO;
	private PasswordDAO passwordDAO;
	private PersonDAO personDAO;
	private ProjectDAO projectDAO;
	private WorkHourDAO workHourDAO;
	
	public void setHourTypeDAO(HourTypeDAO hourTypeDAO) {
		this.hourTypeDAO = hourTypeDAO;
	}
	public void setPasswordDAO(PasswordDAO passwordDAO) {
		this.passwordDAO = passwordDAO;
	}
	public void setPersonDAO(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}
	public void setProjectDAO(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}

	
	@Override
	public List<Person> findPersons(String firstname, String lastname,
			String email, Set<Integer> projects) {		
		return this.personDAO.findPersons(firstname, lastname, email, projects);
	}
	@Override
	public List<HourType> getAllHourTypes() {
		return this.hourTypeDAO.getHourTypes();
	}
	@Override
	public List<Project> getAllProjects() {		
		return this.projectDAO.getAllProjects();
	}
	@Override
	public Person getPerson(Integer id) {
		Person person = this.personDAO.getPerson(id);
		if(person.getRole() != Person.Role.SUPERUSER){
			List<Integer> list = this.projectDAO.findProjectsByWorker(person.getId());
			person.setProjects(list);			
		}
		return person;
	}
	@Override
	public Project getProject(Integer id) {
		Project project = projectDAO.getProject(id);
		List<Integer> list =  this.hourTypeDAO.getProjectHourTypes(id);		
		project.setHourtypes(new HashSet<Integer>(list));
		return project;
	}
	@Override
	public HourType getHourType(Integer id) {	
		return this.hourTypeDAO.getHourType(id);
	}
	
	@Override
	public Person login(String userid, String password) {
		return this.passwordDAO.login(userid, password);
	}
	@Override
	public HourType saveHourType(HourType hourType) {		
		Integer id = this.hourTypeDAO.saveHourType(hourType);
		return this.getHourType(id);
	}
	@Override
	public Person savePerson(Person person) {
		Integer id = this.personDAO.savePerson(person);
		if(person.getRole() != Person.Role.SUPERUSER && (person.getProjects()
				!= null && person.getProjects().size()>0)){
			//Liitetaan työntekijä projekteihin			
			this.projectDAO.joinWorkerToProjects(id, new HashSet(person.getProjects()), null);
		}
		return this.getPerson(id);
	}
	@Override
	public Project saveProject(Project project) {
		Integer id = this.projectDAO.saveProject(project);		
		this.hourTypeDAO.joinHourTypesToProject(id, project.getHourtypes());
		return this.getProject(id);
	}
	@Override
	public void joinWorkerToProject(Integer workerId, Set<Integer> projects,
			Set<Integer> focusProjects) {
		this.projectDAO.joinWorkerToProjects(workerId, projects, focusProjects);		
	}
	@Override
	public boolean changePassword(Person person, String oldPassword,
			String newPassword) {
		return this.passwordDAO.changePassword(person, oldPassword, newPassword);		
	}
	@Override
	public String generatePassword(Person person) {
		return this.passwordDAO.generatePassword(person);
	}	
}