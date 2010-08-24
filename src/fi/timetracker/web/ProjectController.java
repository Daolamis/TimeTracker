package fi.timetracker.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import fi.timetracker.db.DatabaseFacade;
import fi.timetracker.entity.Project;
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
		facade.saveProject(project);
		return super.onSubmit(command);
	}
	
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		Project project = null;
		String id = request.getParameter("id");		
		if(id!=null){
			project = facade.getProject(new Integer(id));			
		}else{
			project = new Project();
		}
		return project;
	}
}