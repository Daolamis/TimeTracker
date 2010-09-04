package fi.timetracker.db;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import fi.timetracker.entity.Entity;
import fi.timetracker.entity.WorkHour;

/** 
 * @author Petteri Parviainen
 */
public class WorkHourDAOImpl extends AbstractDAO implements WorkHourDAO{
	
	private static final String GET_ALL = "SELECT * FROM hours WHERE " +
			"person_id = ? ORDER BY workdate DESC, project_id, hour_type_id";
	private static final String DELETE_WORK_HOUR = "DELETE FROM hours WHERE id = ?";
	private static final String INSERT = "INSERT INTO hours (person_id, " +
			"project_id, hour_type_id, workdate, hours, overwork) VALUES (?,?,?,?,?,?)";

	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Entity getById(int id) {
		// ei tarvita, koska updatea ei ole
		return null;
	}

	@Override
	public void deleteWorkHour(Integer workHourId) {
		this.jdbcTemplate.update(DELETE_WORK_HOUR, new Object[] {workHourId});		
	}

	@Override
	public List<WorkHour> getWorkHours(Integer workerId) {
		return this.jdbcTemplate.query(GET_ALL, new Object[]{workerId}, new WorkHourRowMapper());
	}

	@Override
	public void saveWorkHour(WorkHour hour) {
		this.jdbcTemplate.update(INSERT, new Object[] {hour.getWorkerId(), 
				hour.getProjectId(), hour.getHourTypeId(), hour.getWorkDate(), 
				new BigDecimal(hour.getAmount()), hour.getOvertime()});		
	}
	
	private static class WorkHourRowMapper implements RowMapper {
		public WorkHour mapRow(ResultSet rs, int rowNum) throws SQLException {
			WorkHour hour = new WorkHour(rs.getInt("id"));
			hour.setWorkerId(rs.getInt("person_id"));
			hour.setProjectId(rs.getInt("project_id"));
			hour.setHourTypeId(rs.getInt("hour_type_id"));			
			hour.setWorkDate(rs.getDate("workdate"));
			hour.setOvertime(rs.getBoolean("overwork"));
			hour.setAmount(rs.getString("hours"));
			hour.setUpdated(rs.getDate("updated"));
			return hour;
		}
	}	

}