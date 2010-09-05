package fi.timetracker.web;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import fi.timetracker.db.DatabaseFacade;
import fi.timetracker.entity.Person;
import fi.timetracker.entity.WorkHour;
/** 
 * @author Petteri Parviainen
 */
public class PersonTaskController extends MultiActionController{

	private DatabaseFacade facade;

	public void setFacade(DatabaseFacade facade) {
		this.facade = facade;		
	}
	
	public ModelAndView showPerson(HttpServletRequest req,
			HttpServletResponse res) throws Exception {		
		Integer id = new Integer(req.getParameter("id"));
		Person person = facade.getPerson(id);
		List<WorkHour> workHours = facade.getWorkHours(id);
		//referenssi dataa, jotta projektin nimi ja tuntityypin nimi 
		//osattaisiin näyttää jsp:n tuntilistauksessa
		Map hourTypes = TimetrackController.convertToMap(facade.getAllHourTypes());		
		Map projects = TimetrackController.convertToMap(facade.getAllProjects(false));
		
		ModelAndView mav = new ModelAndView("static_person");
		mav.addObject("person", person);
		mav.addObject("workHours", workHours);
		mav.addObject("hourTypes", hourTypes);
		mav.addObject("projects", projects);
		return mav;
	}
	
	public ModelAndView generateNewPassword(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		Integer id = new Integer(req.getParameter("id"));
		Person person = facade.getPerson(id);
		String password = this.facade.generatePassword(id);		
		ModelAndView mav = new ModelAndView("front_page");
		mav.addObject("message", person.getFirstname()+" "+person.getLastname()+
				" ("+person.getEmail()+") uusi salasana on \""+password+"\"");
		return mav;
	}
	
	public ModelAndView deleteWorkhour(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		Integer id = new Integer(req.getParameter("workHourId"));
		this.facade.deleteWorkHour(id);		
		ModelAndView mav = new ModelAndView("redirect:timetrackController");		
		return mav;
	}
	
	
	public ModelAndView listPersons(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		Person login = (Person) req.getSession(false).getAttribute("loginData");
		ModelAndView mav = null;
		if(login.getRole()==Person.Role.MANAGER){
			Set<Integer> projectIds = login.getProjects();
			if(projectIds != null && projectIds.size() > 0){
				List<Person> persons=facade.findPersons("", "", "", projectIds);
				mav = new ModelAndView("person_list");
				mav.addObject("persons", persons);
			}else{
				//hakua ei voitu tehdä koska manageria ei ole liitetty
				//mihinkään projektiin, jolloin haku olisi palauttanut kaikki
				//käyttäjät (koska käyttäjien projekteja ei olisi rajattu)
				mav = new ModelAndView("front_page");
				mav.addObject("message", "Sinulla ei ole projekteja, siten ei myöskään alaisia");
			}
		}else{
			//vain managereilla on oikeus käyttää tätä controlleria
			mav = new ModelAndView("front_page");
		}
		return mav;
	}
}