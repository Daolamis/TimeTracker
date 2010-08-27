package fi.timetracker.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import fi.timetracker.db.DatabaseFacade;
import fi.timetracker.entity.HourType;
/** 
 * @author Petteri Parviainen
 */
public class HourTypeController extends SimpleFormController{

	private DatabaseFacade facade;

	public void setFacade(DatabaseFacade facade) {
		this.facade = facade;
	}
	
	@Override
	protected ModelAndView onSubmit(Object command) throws Exception {
		HourType  hourType = (HourType) command;		
		hourType = facade.saveHourType(hourType);
		ModelAndView map =  new ModelAndView(getSuccessView());	
		map.addObject(getCommandName(), hourType);
		map.addObject("message", "Tuntityypin tiedot tallennettu");		
		return map;
	}
	
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		HourType hourType = null;
		Integer id = null;
		try{
			id = new Integer(request.getParameter("id"));
		}catch(Exception nfe){}
		if(id != null){			
			hourType = facade.getHourType(id);			
		}else{
			hourType = new HourType(null);
		}
		return hourType;
	}
}