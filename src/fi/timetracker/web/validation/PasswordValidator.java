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
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "oldPassword", "", "Vanha salasana puuttuu");		
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "password", "", "Uusi salasana puuttuu");		
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "retypePassword", "", "Uuden salasana varmistus puuttuu");		
		if(err.hasErrors() == false){
			if(command.getPassword().length()<6){
				err.reject("", "Salasana pituus tulee olla vähintään 6 merkkiä");
			}else if(command.getPassword().equals(command.getRetypePassword()) == false){
				err.reject("", "Antamasi uudet salasanat eivät olleet samoja");
			}			
		}
	}
}