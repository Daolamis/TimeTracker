package fi.timetracker.db;

import java.util.List;

import fi.timetracker.entity.Project;
import fi.timetracker.entity.Worker;

/**
 * 
 * @author Petteri Parviainen
 *
 */
public interface ProjectDAO {
	
	public Project saveProject(Project project);
	
	public List<Project> findProjects();
	
	public Project getProject(int id);
	
	public void joinWorkerToProjects(Worker worker, List<Project> projects, List<Project> focusProjects);
 
}
