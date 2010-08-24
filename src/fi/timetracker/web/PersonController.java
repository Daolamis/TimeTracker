package fi.timetracker.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import fi.timetracker.db.DatabaseFacade;
import fi.timetracker.entity.Person;
import fi.timetracker.entity.PersonCommand;
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
		if(person.getId() == null){
			//uusi kayttaja, jolloin pitaa tehda instanssi roolin mukaan
			person = Person.createInstance((PersonCommand) person);
		}
		person = facade.savePerson(person);		
		return new ModelAndView("Jotakin");
	}
	
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		Person person = null;
		String id = request.getParameter("id");		
		if(id!=null){
			person = facade.getPerson(new Integer(id));			
		}else{
			person = new PersonCommand();
		}
		return person;
	}
}