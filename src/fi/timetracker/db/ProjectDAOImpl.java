package fi.timetracker.db;

import java.util.List;

import fi.timetracker.entity.Entity;
import fi.timetracker.entity.Project;
import fi.timetracker.entity.Worker;

/** 
 * @author Petteri Parviainen
 *
 */
public class ProjectDAOImpl extends AbstractDAO implements ProjectDAO{

	@Override
	public List<Project> findProjects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Project getProject(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void joinWorkerToProjects(Worker worker, List<Project> projects,
			List<Project> focusProjects) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Project saveProject(Project project) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entity getById(int id) {
		return this.getProject(id);
	}
}