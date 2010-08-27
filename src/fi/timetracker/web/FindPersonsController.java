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
/** 
 * @author Petteri Parviainen
 */
public class FindPersonsController extends SimpleFormController {
	
	private DatabaseFacade facade;

	public void setFacade(DatabaseFacade facade) {
		this.facade = facade;
	}
	
	@Override
	protected ModelAndView onSubmit(Object c) throws Exception {		
		FindPersonsCommand command = (FindPersonsCommand) c;
		//List<Person> persons = facade.findPersons("%", "%", "%", null);		
		List<Person> persons = facade.findPersons(command.getFirstname(), 
				command.getLastname(), 
				command.getEmail(), 
				command.getProjects());					
		ModelAndView map = new ModelAndView(getSuccessView());
		map.addObject("persons", persons);
		map.addAllObjects(getReferenceMap());
		map.addObject(getCommandName(), command);
		return map;
	}
	
	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {
		return getReferenceMap();
	}
	
	private Map getReferenceMap(){
		Map referenceData = new HashMap();
		List<Project> projects = facade.getAllProjects(false);
		HashMap<Integer, String> allProjects = new HashMap<Integer, String>();
		for(Project project:projects){
			allProjects.put(project.getId(), project.getName());
		}
		referenceData.put("allProjects", allProjects);
		return referenceData;
	}
}