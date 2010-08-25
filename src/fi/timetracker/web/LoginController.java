package fi.timetracker.web;

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
	protected ModelAndView onSubmit(Object command) throws Exception {
		LoginCommand loginCmd = (LoginCommand) command;
		Person person = facade.login(loginCmd.getUserId(), loginCmd.getPassword());
		ModelAndView mav = null;		
		if(person != null){
			this.getServletContext().setAttribute("loginData", person);
			mav = new ModelAndView(this.getSuccessView());
		}else{
			mav = new ModelAndView(this.getFormView(), this.getCommandName(), new LoginCommand());
			mav.addObject("message", "Käyttäjätunnus tai salasana oli virheellinen");
		}
		return mav;
	}
}