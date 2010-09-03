package fi.timetracker.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
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
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors) throws Exception {
		Person person = (Person) command;
		if(facade.isEmailFree(person.getId(), person.getEmail()) == false){
			errors.rejectValue("email", "", "Sähköposti on jo käytössä");
			return showForm(request, response, errors, null);
		}
		boolean uusi = person.getId() == null;
		person = facade.savePerson(person);
		ModelAndView map =  new ModelAndView(getSuccessView());
		map.addAllObjects(getReferenceMap());
		map.addObject(getCommandName(), person);
		if(uusi){
			String password = facade.generatePassword(person.getId());
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
		}catch(Exception nfe){
			//NullPointerException ja NumberFormatExceptionit...
		} 
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
	
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
	    // Jotta lomakkeen päivämäärä saadaan muutettua objekti muotoon automaagisesti
		DateFormat df = new SimpleDateFormat("ddMMyy");
	    df.setLenient(false);
	    CustomDateEditor editor = new CustomDateEditor(df, true);
	    binder.registerCustomEditor(Date.class, editor);
	}

	
	private Map getReferenceMap(){
		Map referenceData = new HashMap();
		List<Project> projects = facade.getAllProjects(true);
		HashMap<Integer, String> allProjects = new HashMap<Integer, String>();
		for(Project project:projects){
			allProjects.put(project.getId(), project.getName());
		}
		referenceData.put("allProjects", allProjects);
		return referenceData;
	}
}