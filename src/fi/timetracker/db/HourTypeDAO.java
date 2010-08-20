package fi.timetracker.db;

import java.util.List;

import fi.timetracker.entity.HourType;

/** 
 * @author Petteri Parviainen
 */
public interface HourTypeDAO {
	
	public List<HourType> getHourTypes();
	
	public HourType getHourType(int id);
	
	public boolean saveHourType(HourType type);
}