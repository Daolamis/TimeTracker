package fi.timetracker.db;

import java.util.List;

import fi.timetracker.entity.Project;

/**
 * 
 * @author Petteri Parviainen
 *
 */
public interface ProjectDAO {
	
	public Project saveProject(Project project);
		
	public List<Integer> findProjectsByWorker(Integer workerId);
	
	public Project getProject(int id);
	
	public List<Project> getAllProjects();
	
	public void joinWorkerToProjects(Integer workerId, List<Integer> projectsToJoin, List<Integer> focusProjects);
 
}
