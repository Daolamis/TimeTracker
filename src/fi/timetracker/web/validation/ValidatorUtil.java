package fi.timetracker.web.validation;
/** 
 * @author Petteri Parviainen
 */
public abstract class ValidatorUtil {
	
	public static boolean validateMaxLength(String value, int maxLength){		
		if(value != null){
			return value.length() > maxLength;
		}		
		return false;
	}
	
	public static boolean validateMinLength(String value, int minLength){
		if(value != null){
			return value.length() < minLength;
		}		
		return false;
	}
	
	public static boolean validateExactLength(String value, int length){	
		if(value != null){
			return value.length() != length;
		}		
		return false;
	}
	
	public static boolean isNumeric(String value){	
		try{
			new Integer(value);
			return true;
		}
		catch(NumberFormatException nfe){
			return false;
		}
	}
}
