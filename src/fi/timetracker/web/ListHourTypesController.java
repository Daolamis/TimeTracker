package fi.timetracker.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import fi.timetracker.db.DatabaseFacade;
import fi.timetracker.entity.HourType;
/** 
 * @author Petteri Parviainen
 */
public class ListHourTypesController extends AbstractController{

	private DatabaseFacade facade;
	
	public void setFacade(DatabaseFacade facade) {
		this.facade = facade;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		List<HourType> hourTypes = facade.getAllHourTypes();
		ModelAndView map = new ModelAndView("hourtype_list");
		map.addObject("hourtypes", hourTypes);
		return map;
	}

}
