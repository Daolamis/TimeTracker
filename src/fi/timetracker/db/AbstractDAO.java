package fi.timetracker.db;

import org.springframework.dao.OptimisticLockingFailureException;

import fi.timetracker.entity.Entity;

/** 
 * @author Petteri Parviainen
 */
public abstract class AbstractDAO {

	public abstract Entity getById(int id);
	
	public void optimisticLockingCheck(Entity entity) {
		Entity inDb = this.getById(entity.getId());
		if(inDb.getUpdated().equals(entity.getUpdated()) == false){
			throw new OptimisticLockingFailureException("Entity: "+entity.getClass()+" id: "+entity.getId()+" was changed");
		}		
	}
	
	protected static String generateInCriteria(int numberOfParams){
		StringBuffer inCriteria=new StringBuffer();
		inCriteria.append("  AND person_project.projects_id IN (?");
		for(int i = 1; i < numberOfParams; i++){
			inCriteria.append(",?");
		}
		return inCriteria.append(")").toString();
	}
}