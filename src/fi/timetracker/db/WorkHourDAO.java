package fi.timetracker.db;

import java.util.Date;
import java.util.List;

import fi.timetracker.entity.Person;
import fi.timetracker.entity.WorkHour;

/**
 * 
 * @author Petteri Parviainen
 *
 */
public interface WorkHourDAO {
	
	public List<WorkHour> getWorkHours(Date start, Date end, Person person);
	
	public boolean saveWorkHour(WorkHour workhours);
	
	public boolean deleteWorkHour(WorkHour workhours);
	
	public WorkHour getWorkHour(int id);

}
