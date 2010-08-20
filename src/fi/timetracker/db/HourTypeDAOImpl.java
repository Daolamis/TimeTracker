package fi.timetracker.db;

import java.util.List;

import fi.timetracker.entity.Entity;
import fi.timetracker.entity.HourType;
/** 
 * @author Petteri Parviainen
 */
public class HourTypeDAOImpl extends AbstractDAO implements HourTypeDAO {

	@Override
	public Entity getById(int id) {
		return this.getHourType(id);
	}

	@Override
	public HourType getHourType(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HourType> getHourTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveHourType(HourType type) {
		// TODO Auto-generated method stub
		return false;
	}

}
