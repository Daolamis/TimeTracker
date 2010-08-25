package fi.timetracker.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import fi.timetracker.db.DatabaseFacade;
import fi.timetracker.entity.Person;
/** 
 * @author Petteri Parviainen
 */
public class FindPersonsController extends AbstractController {
	
	private DatabaseFacade facade;

	public void setFacade(DatabaseFacade facade) {
		this.facade = facade;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		List<Person> persons = facade.findPersons("%", "%", "%", null);
		ModelAndView map = new ModelAndView("person_list");
		map.addObject("persons", persons);
		return map;
	}
}