package fi.timetracker.web.validation;
/** 
 * @author Petteri Parviainen
 */
public abstract class ValidatorUtil {
	
	public static boolean validateMaxLength(String value, int maxLength){		
		if(value != null){
			return value.length() > maxLength;
		}		
		return true;
	}
	
	public static boolean validateMinLength(String value, int minLength){
		if(value != null){
			return value.length() < minLength;
		}		
		return true;
	}
	
	public static boolean validateExactLength(String value, int length){	
		if(value != null){
			return value.length() == length;
		}		
		return true;
	}
}
