package fi.timetracker.db;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import fi.timetracker.entity.Entity;
import fi.timetracker.entity.Person;
import fi.timetracker.entity.WorkHour;

/** 
 * @author Petteri Parviainen
 */
public class WorkHourDAOImpl extends AbstractDAO implements WorkHourDAO{
	
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Entity getById(int id) {		
		return this.getWorkHour(id);
	}

	@Override
	public List<WorkHour> getWorkHours(Date start, Date end, Person person) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveWorkHour(WorkHour workhours) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public WorkHour getWorkHour(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteWorkHour(WorkHour workhours) {
		// TODO Auto-generated method stub
		return false;
	}

}