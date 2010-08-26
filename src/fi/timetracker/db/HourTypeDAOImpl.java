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
import fi.timetracker.entity.HourType;

/**
 * @author Petteri Parviainen
 */
public class HourTypeDAOImpl extends AbstractDAO implements HourTypeDAO {
	
	private static final String GET_PROJECT_HOUR_TYPES  = "SELECT hour_type_id " +
			"FROM project_hour_types WHERE project_id = ?";
	private static final String GET_HOUR_TYPE = "SELECT * FROM hour_type WHERE id = ?";
	private static final String GET_ALL = "SELECT * FROM hour_type";
	private static final String UPDATE = "UPDATE hour_type SET name = ?, " +
			"description = ?, branch_of_activity = ?, " +
			"updated = CURRENT_TIMESTAMP WHERE id = ?";
	
	private static final String DELETE_JOINS = "DELETE FROM project_hour_types " +
			"WHERE project_id = ?";
	private static final String JOIN_TO_PROJECT = "INSERT INTO " +
			"project_hour_types (project_id, hour_type_id) VALUES (?,?)";

	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Entity getById(int id) {
		return this.getHourType(id);
	}

	@Override
	public HourType getHourType(int id) {
		return (HourType) this.jdbcTemplate.queryForObject(GET_HOUR_TYPE, new Object[] { id }, new HourTypeRowMapper());
	}

	@Override
	public List<HourType> getHourTypes() {
		return this.jdbcTemplate.query(GET_ALL, new HourTypeRowMapper());
	}

	@Override
	public Integer saveHourType(HourType type) {
		int id = -1;
		if (type.getId() == null) {
			id = this.insertHourType(type);
		} else {
			this.optimisticLockingCheck(type); // OptimisticLocking
			id = this.updateHourType(type);
		}
		return id;
	}
	
	private int insertHourType(HourType type){
		SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(this.jdbcTemplate)
		.withTableName("hour_type").usingGeneratedKeyColumns("id");
		jdbcInsert.usingColumns("name","description","branch_of_activity");
		MapSqlParameterSource parameters = new MapSqlParameterSource();		
		parameters.addValue("name", type.getName());
		parameters.addValue("description", type.getDescription());
		parameters.addValue("branch_of_activity", type.getBranchOfActivity());		
		Number id = jdbcInsert.executeAndReturnKey(parameters);
		return id.intValue();
		
	}
	
	private int updateHourType(HourType type){
		LinkedList<Object> args = new LinkedList<Object>();
		args.add(type.getName());
		args.add(type.getDescription());
		args.add(type.getBranchOfActivity());		
		args.add(type.getId());
		
		this.jdbcTemplate.update(UPDATE, args.toArray());
		return type.getId();
	}
	
	@Override
	public List<Integer> getProjectHourTypes(Integer projectId) {
		return this.jdbcTemplate.queryForList(GET_PROJECT_HOUR_TYPES,
				new Object[] { projectId }, Integer.class);
		
	};

	@Override
	public void joinHourTypesToProject(int projectId,
			Set<Integer> hourTypeIds) {
		if(hourTypeIds != null && hourTypeIds.size() > 0){
			this.jdbcTemplate.update(DELETE_JOINS, new Object[] {projectId});
			for(Integer hourtypeId:hourTypeIds){
				this.jdbcTemplate.update(JOIN_TO_PROJECT, new Object[] {projectId, hourtypeId});
			}
		}
	}
	
	private static class HourTypeRowMapper implements RowMapper {
		public HourType mapRow(ResultSet rs, int rowNum) throws SQLException {
			HourType hourType = new HourType(rs.getInt("id"));
			hourType.setName(rs.getString("name"));
			hourType.setDescription(rs.getString("description"));
			hourType.setBranchOfActivity(rs.getString("branch_of_activity"));
			hourType.setUpdated(rs.getDate("updated"));
			return hourType;
		}
	}	
}