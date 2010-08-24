package fi.timetracker.web;

import org.springframework.web.servlet.mvc.SimpleFormController;

import fi.timetracker.db.DatabaseFacade;
/** 
 * @author Petteri Parviainen
 */
public class FindPersonsController extends SimpleFormController {
	
	private DatabaseFacade facade;

	public void setFacade(DatabaseFacade facade) {
		this.facade = facade;
	}
	
	

}
