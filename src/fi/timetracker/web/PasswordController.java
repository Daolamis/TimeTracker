package fi.timetracker.web;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import fi.timetracker.db.DatabaseFacade;
import fi.timetracker.entity.Person;
/** 
 * @author Petteri Parviainen
 *
 */
public class PasswordController extends SimpleFormController{
	private DatabaseFacade facade;
	
	public void setFacade(DatabaseFacade facade) {
		this.facade = facade;
	}
	
	@Override
	protected ModelAndView onSubmit(Object command) throws Exception {
		LoginCommand login = (LoginCommand) command;
		Person person = (Person) getServletContext().getAttribute("loginData");
		boolean ok = this.facade.changePassword(person, login.getOldPassword(), login.getPassword());
		ModelAndView map = null; 
		if(ok){
			map = new ModelAndView("front_page");
			map.addObject("message", "Salasana on vaihdettu");
		}else{
			map = new ModelAndView(getFormView());
			map.addObject("message", "Vanha salasana oli väärin, yritä uudestaan");
		}
		return map;
	}
}