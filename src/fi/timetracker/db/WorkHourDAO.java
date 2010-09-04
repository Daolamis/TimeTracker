package fi.timetracker.db;

import java.util.List;

import fi.timetracker.entity.WorkHour;

/**
 * 
 * @author Petteri Parviainen
 *
 */
public interface WorkHourDAO {
	
	public List<WorkHour> getWorkHours(Integer workerId);
	
	public void saveWorkHour(WorkHour hour);
	
	public void deleteWorkHour(Integer workHourId);
}