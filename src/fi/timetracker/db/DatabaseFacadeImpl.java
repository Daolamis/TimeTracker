package fi.timetracker.db;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fi.timetracker.entity.HourType;
import fi.timetracker.entity.Person;
import fi.timetracker.entity.Project;
import fi.timetracker.entity.WorkHour;
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
	
	public void setWorkHourDAO(WorkHourDAO workHourDAO) {
		this.workHourDAO = workHourDAO;
	}
	@Override
	public List<Person> findPersons(String firstname, String lastname,
			String email, Set<Integer> projects) {
		if(firstname.length() == 0){
			firstname = "%";
		}else{
			firstname = firstname.replace('*','%').replace('?', '_');
		}
		
		if(lastname.length() == 0){
			lastname = "%";
		}else{
			lastname = lastname.replace('*','%').replace('?', '_');
		}
		
		if(email.length() == 0){
			email = "%";
		}else{
			email = email.replace('*','%').replace('?', '_');
		}
		return this.personDAO.findPersons(firstname, lastname, email, projects);
	}
	@Override
	public List<HourType> getAllHourTypes() {
		return this.hourTypeDAO.getHourTypes();
	}
	@Override
	public List<Project> getAllProjects(boolean onlyActive) {		
		List<Project> projects =  this.projectDAO.getAllProjects(onlyActive);
		for(Project project:projects){
			List<Integer> list =  this.hourTypeDAO.getProjectHourTypes(project.getId());		
			project.setHourtypes(new HashSet<Integer>(list));
		}
		return projects;
	}
	@Override
	public Person getPerson(Integer id) {
		Person person = this.personDAO.getPerson(id);
		if(person.getRole() != Person.Role.SUPERUSER){
			List<Integer> list = this.projectDAO.findProjectsByWorker(person.getId());
			person.setProjects(new HashSet<Integer>(list));			
		}
		return person;
	}
	@Override
	public Project getProject(Integer id) {
		Project project = projectDAO.getProject(id);
		List<Integer> list =  this.hourTypeDAO.getProjectHourTypes(id);		
		project.setHourtypes(new HashSet<Integer>(list));
		HashSet<Integer> projectId = new HashSet<Integer>();
		projectId.add(project.getId());
		project.setWorkers(this.findPersons("", "", "", projectId));
		return project;
	}
	@Override
	public HourType getHourType(Integer id) {	
		return this.hourTypeDAO.getHourType(id);
	}
	
	@Override
	public Person login(String userid, String password) {
		Person person = null;
		Integer id = this.passwordDAO.login(userid, password);
		if(id != null){
			person = this.getPerson(id);
		}
		return person;
	}
	@Override
	public HourType saveHourType(HourType hourType) {		
		Integer id = this.hourTypeDAO.saveHourType(hourType);
		return this.getHourType(id);
	}
	@Override
	public Person savePerson(Person person) {
		Integer id = this.personDAO.savePerson(person);
		if(person.getRole() != Person.Role.SUPERUSER){
			//Liitetaan työntekijä projekteihin			
			this.projectDAO.joinWorkerToProjects(id, person.getProjects(), null);
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
	public boolean isEmailFree(Integer personId, String email){
		List<Person> persons = this.findPersons("", "", email, null);
		if(persons.size() == 0){
			return true;
		}else if(personId == null){
			return false;
		}else{
			Person person = persons.get(0);
			return person.getId() == personId;
		}
	}
	@Override
	public boolean changePassword(Person person, String oldPassword,
			String newPassword) {
		return this.passwordDAO.changePassword(person, oldPassword, newPassword);		
	}
	@Override
	public String generatePassword(Integer id) {
		return this.passwordDAO.generatePassword(id);
	}
	@Override
	public void deleteWorkHour(Integer workHourId) {
		this.workHourDAO.deleteWorkHour(workHourId);
		
	}
	@Override
	public List<WorkHour> getWorkHours(Integer workerId) {		
		return this.workHourDAO.getWorkHours(workerId);
	}
	@Override
	public void saveWorkHour(WorkHour hour) {
		this.workHourDAO.saveWorkHour(hour);		
	}	
}