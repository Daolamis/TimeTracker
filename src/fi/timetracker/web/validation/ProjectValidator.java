package fi.timetracker.web.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import fi.timetracker.entity.Project;
/** 
 * @author Petteri Parviainen
 */
public class ProjectValidator  implements Validator{

	@Override
	public boolean supports(Class clazz) {		
		return  Project.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors err) {
		Project command = (Project) obj;
		ValidationUtils.rejectIfEmpty(err, "name", "", "Nimi puuttuu");		
		if(err.getFieldErrorCount("name") == 0 && ValidatorUtil.validateMaxLength(command.getName(), 30)){
			err.rejectValue("name", "", "Nimen maksimipituus on 30 merkkiä");
		}
		if(err.getFieldErrorCount("name") == 0 && ValidatorUtil.validateMinLength(command.getName(), 4)){
			err.rejectValue("name", "", "Nimen minimipituus on 4 merkkiä");
		}
		
		ValidationUtils.rejectIfEmpty(err, "description", "", "Kuvaus puuttuu");
		if(err.getFieldErrorCount("description") == 0 && ValidatorUtil.validateMaxLength(command.getDescription(), 255)){
			err.rejectValue("description", "", "Kuvauksen maksimipituus on 255 merkkiä");
		}
		if(err.getFieldErrorCount("description") == 0 && ValidatorUtil.validateMinLength(command.getDescription(), 10)){
			err.rejectValue("description", "", "Kuvauksen minimipituus on 10 merkkiä");
		}
	}
}