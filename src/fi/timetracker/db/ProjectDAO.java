package fi.timetracker.db;

import java.util.List;
import java.util.Set;

import fi.timetracker.entity.Project;

/**
 * 
 * @author Petteri Parviainen
 *
 */
public interface ProjectDAO {
	
	public Integer saveProject(Project project);
		
	public List<Integer> findProjectsByWorker(Integer workerId);
	
	public Project getProject(int id);
	
	public List<Project> getAllProjects(boolean onlyActive);
	
	public void joinWorkerToProjects(Integer workerId, Set<Integer> projectsToJoin, Set<Integer> focusProjects);
 
}
