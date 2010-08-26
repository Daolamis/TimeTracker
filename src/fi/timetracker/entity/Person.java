package fi.timetracker.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author Petteri Parviainen
 *
 */
public class Person extends Entity {
	
	public Person(Integer id){
		super(id);
		this.projects = new HashSet<Integer>();
	}
		
	public enum PersonStatus {ACTIVE('A'), CLOSED('C');
		private char code;
		
		private PersonStatus(char code){
			this.code = code;
		}
		
		public String getCode(){
			return ""+this.code;
		}	
	};
	
	public enum Role {WORKER('W'), MANAGER('M'), SUPERUSER('S');
		private char code;
		
		private Role(char code){
			this.code = code;
		}
		public String getCode(){
			return ""+this.code;
		}		
	};
	
	private Role role;
	private PersonStatus status;
		
	private String firstname;
	private String lastname;
	private String title;
	private String address;
	private String postalcode;
	private String city;
	private String country;
	private String email;
	private String phone;
	private Date dateOfBirth;
	private String socialSecuritySuffix;
	private Date lastlogin;
	private Date created;
	//private Person creator;
	private Set<Integer> projects; //projektin pääavaimia (työntekijä kuuluu ko. projekteihin)

	public List<Integer> getProjects() {
		return new LinkedList<Integer>(projects);
	}
	public void setProjects(List<Integer> projects) {
		this.projects = new HashSet<Integer>(projects);
	}
	
	public int getProjectSize(){
		return this.projects.size();
	}
	
	public Role getRole() {
		return role;
	}
	
	public String getRoleCode() {
		return this.role.getCode();
	}
	
	public void setRoleCode(String code) {
		switch (code.charAt(0)) {
		case 'S':
			this.role= Role.SUPERUSER;
			break;
		case 'M':
			this.role= Role.MANAGER;
			break;
		case 'W':
			this.role= Role.WORKER;
			break;
		default:
			throw new AssertionError("Unknown role: " + code);
		}
	}
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostalcode() {
		return postalcode;
	}
	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getSocialSecuritySuffix() {
		return socialSecuritySuffix;
	}
	public void setSocialSecuritySuffix(String socialSecuritySuffix) {
		this.socialSecuritySuffix = socialSecuritySuffix;
	}
	public Date getLastlogin() {
		return lastlogin;
	}
	public void setLastlogin(Date lastlogin) {
		this.lastlogin = lastlogin;
	}

	public PersonStatus getStatus() {
		return status;
	}

	public void setStatus(PersonStatus status) {
		this.status = status;
	}
	
	public void setStatusFromCode(String code) {
		 switch (code.charAt(0)) {
	      case 'A': 
	        this.status = PersonStatus.ACTIVE;
	        break;   
	      case 'C': 
	    	  this.status = PersonStatus.CLOSED;
		        break;
	      default:
	    	  throw new AssertionError("Unknown code: " + code);	        
	    }
	}
	
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
}