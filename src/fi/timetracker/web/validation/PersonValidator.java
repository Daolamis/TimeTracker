package fi.timetracker.web.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import fi.timetracker.entity.Person;
/** 
 * @author Petteri Parviainen
 */
public class PersonValidator  implements Validator{

	@Override
	public boolean supports(Class clazz) {		
		return Person.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors err) {
		Person command = (Person) obj;
		ValidationUtils.rejectIfEmpty(err, "firstname", "", "Etunimi puuttuu");		
		if(err.getFieldErrorCount("firstname") == 0 && ValidatorUtil.validateMaxLength(command.getFirstname(), 30)){
			err.rejectValue("firstname", "", "Etunimen maksimipituus on 30 merkkiä");
		}
		if(err.getFieldErrorCount("firstname") == 0 && ValidatorUtil.validateMinLength(command.getFirstname(), 2)){
			err.rejectValue("firstname", "", "Etunimen minimipituus on 2 merkkiä");
		}
		
		ValidationUtils.rejectIfEmpty(err, "lastname", "", "Sukunimi puuttuu");		
		if(err.getFieldErrorCount("lastname") == 0 && ValidatorUtil.validateMaxLength(command.getLastname(), 30)){
			err.rejectValue("lastname", "", "Sukunimen maksimipituus on 30 merkkiä");
		}
		if(err.getFieldErrorCount("lastname") == 0 && ValidatorUtil.validateMinLength(command.getLastname(), 2)){
			err.rejectValue("lastname", "", "Sukunimen minimipituus on 2 merkkiä");
		}
		
		ValidationUtils.rejectIfEmpty(err, "title", "", "Tehtävänimike puuttuu");		
		if(err.getFieldErrorCount("title") == 0 && ValidatorUtil.validateMaxLength(command.getTitle(), 30)){
			err.rejectValue("title", "", "Tehtävänimikeen maksimipituus on 30 merkkiä");
		}
		if(err.getFieldErrorCount("title") == 0 && ValidatorUtil.validateMinLength(command.getTitle(), 4)){
			err.rejectValue("title", "", "Tehtävänimikeen minimipituus on 4 merkkiä");
		}
		
		ValidationUtils.rejectIfEmpty(err, "address", "", "Osoite puuttuu");		
		if(err.getFieldErrorCount("address") == 0 && ValidatorUtil.validateMaxLength(command.getAddress(), 30)){
			err.rejectValue("address", "", "Osoitteen maksimipituus on 30 merkkiä");
		}
		if(err.getFieldErrorCount("address") == 0 && ValidatorUtil.validateMinLength(command.getAddress(), 4)){
			err.rejectValue("address", "", "Osoitteen minimipituus on 4 merkkiä");
		}
		
		ValidationUtils.rejectIfEmpty(err, "postalcode", "", "Postinumero puuttuu");		
		if(err.getFieldErrorCount("postalcode") == 0 && ValidatorUtil.validateExactLength(command.getPostalcode(), 5)){
			err.rejectValue("postalcode", "", "Postinumeron pituus on 5 merkkiä");
		}
		if(err.getFieldErrorCount("postalcode") == 0 && ValidatorUtil.isNumeric(command.getPostalcode()) == false){
			err.rejectValue("postalcode", "", "Postinumeron pitää olla numeerinen");
		}
		
		ValidationUtils.rejectIfEmpty(err, "city", "", "Kaupunki puuttuu");		
		if(err.getFieldErrorCount("city") == 0 && ValidatorUtil.validateMaxLength(command.getCity(), 30)){
			err.rejectValue("city", "", "Kaupungin maksimipituus on 30 merkkiä");
		}
		if(err.getFieldErrorCount("city") == 0 && ValidatorUtil.validateMinLength(command.getCity(), 2)){
			err.rejectValue("city", "", "Kaupungin minimipituus on 2 merkkiä");
		}
		
		ValidationUtils.rejectIfEmpty(err, "country", "", "Maa puuttuu");		
		if(err.getFieldErrorCount("country") == 0 && ValidatorUtil.validateMaxLength(command.getCountry(), 30)){
			err.rejectValue("country", "", "Kaupungin maksimipituus on 30 merkkiä");
		}
		if(err.getFieldErrorCount("country") == 0 && ValidatorUtil.validateMinLength(command.getCountry(), 2)){
			err.rejectValue("country", "", "Kaupungin minimipituus on 2 merkkiä");
		}
		
		ValidationUtils.rejectIfEmpty(err, "email", "", "Sähköposti puuttuu");		
		if(err.getFieldErrorCount("email") == 0){
			if(command.getEmail().matches("^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Z]{2,4}$") == false){
				err.rejectValue("email", "", "Sähköposti on virheellinen");
			}
		}
		
		ValidationUtils.rejectIfEmpty(err, "phone", "", "Puhelinnumero puuttuu");		
		if(err.getFieldErrorCount("phone") == 0 && ValidatorUtil.validateMaxLength(command.getPhone(), 30)){
			err.rejectValue("phone", "", "Puhelinnumeron maksimipituus on 30 merkkiä");
		}
		if(err.getFieldErrorCount("phone") == 0 && ValidatorUtil.validateMinLength(command.getPhone(), 5)){
			err.rejectValue("phone", "", "Puhelinnumeron minimipituus on 5 merkkiä");
		}
		
		if(command.getRole() != Person.Role.SUPERUSER){
			ValidationUtils.rejectIfEmpty(err, "socialSecuritySuffix", "", "Sotun loppuosa puuttuu");
			if(command.getDateOfBirth() == null){
				err.rejectValue("dateOfBirth", "", "Sotun alkuosa puuttuu");
			}			
		}	
	}
}