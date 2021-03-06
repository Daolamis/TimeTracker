package fi.timetracker.web;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import fi.timetracker.db.DatabaseFacade;
import fi.timetracker.entity.Entity;
import fi.timetracker.entity.Person;
import fi.timetracker.entity.WorkHour;
/** 
 * @author Petteri Parviainen
 */
public class TimetrackController extends SimpleFormController{

	private DatabaseFacade facade;

	public void setFacade(DatabaseFacade facade) {
		this.facade = facade;		
	}
	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		WorkHour hour = (WorkHour) command;
		
		//koska vain kaksi kenttää pitää validoita, 
		//tehdään se poikkeuksellisesti controllerissa
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "workDate", "","Pvm puuttuu");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "amount", "","Tuntimäärä puuttuu");
		if(errors.getFieldErrorCount("amount")==0){
			try{
				new BigDecimal(hour.getAmount());			
			}catch(NumberFormatException e){
				errors.rejectValue("amount", "", "Tuntimäärän pitää olla numeerinen");
			}
		}		
		if(errors.hasErrors()){
			//Syötteessä virheitä
			return showForm(request, response, errors); 
		}
		
		this.facade.saveWorkHour(hour);		
		//tyhjennetaan lomake
		hour.setProjectId(null);
		hour.setHourTypeId(null);
		hour.setWorkDate(null);
		hour.setAmount(null);		
		ModelAndView mav = showForm(request, response, errors); 
		mav.addObject("message", "Tunti on kirjattu");
		return mav;		
	}
	
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
	    // Jotta lomakkeen päivämäärä saadaan muutettua objekti muotoon automaagisesti
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	    df.setLenient(false);
	    CustomDateEditor editor = new CustomDateEditor(df, true);
	    binder.registerCustomEditor(Date.class, editor);
	}
	
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		WorkHour hour = new WorkHour();
		Person login = (Person) request.getSession(false).getAttribute("loginData");
		hour.setWorkerId(login.getId());
		return hour;
	}
	
	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {
		Person login = (Person) request.getSession(false).getAttribute("loginData");
		Map referenceData = new HashMap();
		Map hourTypes = convertToMap(facade.getAllHourTypes());
		referenceData.put("hourTypes", hourTypes);
		Map projects = convertToMap(facade.getAllProjects(false));
		referenceData.put("projects", projects);
		List<WorkHour> workHours = facade.getWorkHours(login.getId());
		referenceData.put("workHours", workHours);
		return referenceData;
	}
	
	public static Map<Integer, Entity> convertToMap(List<? extends Entity> list){
		Map<Integer, Entity> map = new HashMap<Integer, Entity>();
		for(Entity e:list){
			map.put(e.getId(), e);
		}		
		return map;
	}
}