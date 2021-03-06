package fi.timetracker.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import fi.timetracker.entity.Entity;
import fi.timetracker.entity.Project;

/**
 * @author Petteri Parviainen
 * 
 */
public class ProjectDAOImpl extends AbstractDAO implements ProjectDAO {

	private static final String UPDATE = "UPDATE project SET name = ?, "
			+ "description = ?, status = ?, updated = CURRENT_TIMESTAMP WHERE id = ?  ";

	private static final String GET_ALL = "SELECT * FROM project";
	private static final String GET_ALL_ACTIVE = "SELECT * FROM project WHERE status = 'A'";
	private static final String GET_PROJECT = "SELECT * FROM project WHERE id = ?";
	private static final String GET_WORKERS_PROJECTS = "SELECT project_id FROM "
			+ "person_projects WHERE person_id = ? AND status = 'J'";

	// person_projects.status kertoo onko työntekijä liitetty projektiin
	// 'U' (unjoined) ja 'J' (joined)
	private static final String UNJOIN_WORKER = "UPDATE person_projects SET status = 'U' WHERE person_id = ?";
	private static final String REJOIN_WORKER = "UPDATE person_projects SET status = 'J' WHERE person_id = ? AND project_id = ?";
	private static final String JOIN_WORKER = "INSERT INTO person_projects (person_id, project_id, status) VALUES (?,?,'J')";

	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Project getProject(int id) {
		return (Project) this.jdbcTemplate.queryForObject(GET_PROJECT,
				new Object[] { id }, new ProjectRowMapper());

	}

	@Override
	public Integer saveProject(Project project) {
		int id = -1;
		if (project.getId() == null) {
			id = this.insertProject(project);
		} else {
			this.optimisticLockingCheck(project); // OptimisticLocking
			id = this.updateProject(project);
		}
		return id;
	}

	private int updateProject(Project project) {
		LinkedList<Object> args = new LinkedList<Object>();
		args.add(project.getName());
		args.add(project.getDescription());
		args.add(project.getStatusCode());
		args.add(project.getId());
		
		this.jdbcTemplate.update(UPDATE, args.toArray());
		return project.getId();
	}

	private int insertProject(Project project) {
		SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(this.jdbcTemplate)
				.withTableName("Project").usingGeneratedKeyColumns("id");
		jdbcInsert.usingColumns("status","name","description");
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("status", project.getStatus().getCode());
		parameters.addValue("name", project.getName());
		parameters.addValue("description", project.getDescription());
		Number id = jdbcInsert.executeAndReturnKey(parameters);
		return id.intValue();
	}

	@Override
	public Entity getById(int id) {
		return this.getProject(id);
	}

	@Override
	public List<Integer> findProjectsByWorker(Integer workerId) {
		return this.jdbcTemplate.queryForList(GET_WORKERS_PROJECTS,
				new Object[] { workerId }, Integer.class);
	}

	@Override
	public List<Project> getAllProjects(boolean onlyActive) {
		if(onlyActive){
			return this.jdbcTemplate.query(GET_ALL_ACTIVE, new ProjectRowMapper());
		}else{
			return this.jdbcTemplate.query(GET_ALL, new ProjectRowMapper());
		}
	}

	@Override
	public void joinWorkerToProjects(Integer workerId,
			Set<Integer> projectsToJoin, Set<Integer> focusProjects) {
		
		LinkedList<Object> args = new LinkedList<Object>();
		args.add(workerId);
		String unjoin = UNJOIN_WORKER;
		if(focusProjects != null && focusProjects.size() > 0){
			unjoin += generateInCriteria(focusProjects.size());
			for(Integer i:focusProjects){
				args.add(i);
			}
		}
		this.jdbcTemplate.update(unjoin, args.toArray());
		if(projectsToJoin != null){
			for(Integer projectId:projectsToJoin){
				try{
					this.jdbcTemplate.update(JOIN_WORKER, new Object[] {workerId, projectId});				
				}catch(Exception e){
					this.jdbcTemplate.update(REJOIN_WORKER, new Object[] {workerId, projectId});
				}			
			}		
		}
	}


	private static class ProjectRowMapper implements RowMapper{
		public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
			Project project = new Project(rs.getInt("id"));
			project.setName(rs.getString("name"));
			project.setDescription(rs.getString("description"));
			project.setStatusCode(rs.getString("status"));
			project.setCreated(rs.getDate("created"));
			project.setUpdated(rs.getTimestamp("updated"));
			return project;
		}
	};
}