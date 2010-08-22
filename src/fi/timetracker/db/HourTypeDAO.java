package fi.timetracker.db;

import java.util.List;
import java.util.Set;

import fi.timetracker.entity.HourType;

/** 
 * @author Petteri Parviainen
 */
public interface HourTypeDAO {
	
	public List<HourType> getHourTypes();
	
	public HourType getHourType(int id);
	
	public Integer saveHourType(HourType type);
	
	public List<Integer> getProjectHourTypes(Integer projectId);
	
	public void joinHourTypesToProject(int projectId, Set<Integer> hourTypeIds);
}