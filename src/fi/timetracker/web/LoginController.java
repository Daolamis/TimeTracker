package fi.timetracker.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import fi.timetracker.db.DatabaseFacade;
import fi.timetracker.entity.Person;
/** 
 * @author Petteri Parviainen
 */
public class LoginController extends SimpleFormController{
	
	private DatabaseFacade facade;
	
	public void setFacade(DatabaseFacade facade) {
		this.facade = facade;
	}
	
	

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors) throws Exception {
		LoginCommand loginCmd = (LoginCommand) command;
		Person person = facade.login(loginCmd.getUserId(), loginCmd.getPassword());
		ModelAndView mav = null;		
		if(person != null){
			this.getServletContext().setAttribute("loginData", person);
			mav = new ModelAndView(this.getSuccessView());
		}else{
			errors.reject("", "Käyttäjätunnus tai salasana oli virheellinen");
			Map model = new HashMap();			
			mav = showForm(request, response, errors, null);			
		}
		return mav;
	}
}