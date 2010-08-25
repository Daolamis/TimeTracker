package fi.timetracker.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import fi.timetracker.db.DatabaseFacade;
import fi.timetracker.entity.Person;
import fi.timetracker.entity.Project;
import fi.timetracker.entity.Person.PersonStatus;
/** 
 * @author Petteri Parviainen
 */
public class PersonController extends SimpleFormController{
	
	private DatabaseFacade facade;
	
	public void setFacade(DatabaseFacade facade) {
		this.facade = facade;
	}
	
	@Override
	protected ModelAndView onSubmit(Object command) throws Exception {
		Person person = (Person) command;
		boolean uusi = person.getId() == null;
		person = facade.savePerson(person);
		ModelAndView map =  new ModelAndView(getSuccessView());
		map.addAllObjects(getReferenceMap());
		map.addObject(getCommandName(), person);
		if(uusi){
			String password = facade.generatePassword(person);
			map.addObject("message", "Käyttäjä on tallennettu. Salasana \""+password+"\"");
		}else{
			map.addObject("message", "Käyttäjän muutokset on tallennettu");
		}
		return map;
	}
	
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		Person person = null;
		Integer id = null;
		try{
			id = new Integer(request.getParameter("id"));
		}catch(Exception nfe){}
		if(id != null){			
			person = facade.getPerson(id);			
		}else{
			person = new Person(null);			
			person.setStatus(PersonStatus.ACTIVE);
			person.setRoleCode("W");
		}
		return person;
	}
	
	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {
		return getReferenceMap();
	}
	
	private Map getReferenceMap(){
		Map referenceData = new HashMap();
		List<Project> projects = facade.getAllProjects();
		HashMap<Integer, String> allProjects = new HashMap<Integer, String>();
		for(Project project:projects){
			allProjects.put(project.getId(), project.getName());
		}
		referenceData.put("allProjects", allProjects);
		return referenceData;
	}
}