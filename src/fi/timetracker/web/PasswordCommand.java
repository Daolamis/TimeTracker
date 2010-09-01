package fi.timetracker.web;
/** 
 * @author Petteri Parviainen
 */
public class PasswordCommand extends LoginCommand{
	
	private String retypePassword;
	private String oldPassword;
	
	public String getRetypePassword() {
		return retypePassword;
	}
	public void setRetypePassword(String retypePassword) {
		this.retypePassword = retypePassword;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

}
