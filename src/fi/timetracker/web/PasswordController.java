package fi.timetracker.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
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
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors) throws Exception {
		PasswordCommand login = (PasswordCommand) command;
		Person person = (Person) request.getSession(false).getAttribute("loginData");
		boolean ok = this.facade.changePassword(person, login.getOldPassword(), login.getPassword());
		ModelAndView mav = null; 
		if(ok){
			mav = new ModelAndView(getSuccessView());
			mav.addObject("message", "Salasana on vaihdettu");
		}else{
			errors.reject("", "Vanha salasana oli virheellinen");			
			mav = showForm(request, response, errors, null);
		}
		return mav;
	}
}