package fi.timetracker.web.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import fi.timetracker.web.PasswordCommand;
/** 
 * @author Petteri Parviainen
 */
public class PasswordValidator implements Validator{

	@Override
	public boolean supports(Class clazz) {		
		return  PasswordCommand.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors err) {
		PasswordCommand command = (PasswordCommand) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "oldPassword", "", "Anna vanha salasana");		
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "password", "", "Anna uusi salasana");		
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "retypePassword", "", "Anna uusi salasana uudestaan");		
		if(err.hasErrors() == false){
			if(command.getPassword().length()<6){
				err.reject("", "Salasana pituus tulee olla vähintään 6 merkkiä");
			}else if(command.getPassword().equals(command.getRetypePassword()) == false){
				err.reject("", "Antamasi uudet salasant eivät olleet samoja");
			}			
		}
	}
}