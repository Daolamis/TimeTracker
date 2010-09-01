package fi.timetracker.web.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import fi.timetracker.web.LoginCommand;

/** 
 * @author Petteri Parviainen
 */
public class LoginValidator implements Validator{

	@Override
	public boolean supports(Class clazz) {		
		return  LoginCommand.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors err) {
		ValidationUtils.rejectIfEmpty(err, "userId", "userid.required", "Anna käyttäjätunnus");
		ValidationUtils.rejectIfEmpty(err, "password", "password.required", "Anna salasana");		
	}
}