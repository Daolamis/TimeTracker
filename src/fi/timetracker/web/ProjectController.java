package fi.timetracker.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import fi.timetracker.db.DatabaseFacade;
import fi.timetracker.entity.HourType;
import fi.timetracker.entity.Project;
import fi.timetracker.entity.Project.ProjectStatus;
/** 
 * @author Petteri Parviainen
 */
public class ProjectController extends SimpleFormController {
	
	private DatabaseFacade facade;
	
	public void setFacade(DatabaseFacade facade) {
		this.facade = facade;
	}
	
	@Override
	protected ModelAndView onSubmit(Object command) throws Exception {
		Project project = (Project) command;
		project = facade.saveProject(project);
		ModelAndView map =  new ModelAndView(getSuccessView());
		map.addAllObjects(getReferenceMap());
		map.addObject(getCommandName(), project);
		map.addObject("message", "Projekti on tallennettu");
		return map;
	}
	
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		Project project = null;
		Integer id = null;
		try{
			id = new Integer(request.getParameter("id"));
		}catch(Exception nfe){}		
		if(id!=null){
			project = facade.getProject(id);			
		}else{
			project = new Project();
			project.setStatus(ProjectStatus.ACTIVE);
		}
		return project;
	}
	
	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {	
		return getReferenceMap();
	}
	
	private Map getReferenceMap(){
		Map referenceData = new HashMap();
		List<HourType> hourTypes = facade.getAllHourTypes();
		HashMap<Integer, String> allHourTypes = new HashMap<Integer, String>();
		for(HourType type:hourTypes){
			allHourTypes.put(type.getId(), type.getName());
		}
		referenceData.put("allHourTypes", allHourTypes);
		return referenceData;
	}
}