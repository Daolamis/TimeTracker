package fi.timetracker.entity;

import java.util.Date;

/**
 * 
 * @author Petteri Parviainen
 *
 */
public abstract class Person extends Entity {
	
	protected Person(Integer id){
		super(id);
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
		public char getCode(){
			return this.code;
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
	
	public static Person createInstance(char rolecode, Integer id){
		 switch (rolecode) {
	      case 'W': 
	           return new Worker(id);
	      case 'M': 
	    	  return new Manager(id);
	      case 'S': 
	    	  return new SuperUser(id);
	      default:
	    	  throw new AssertionError("Unknown rolecode: " + rolecode);	        
	    }
	}
	
	public static Person createInstance(PersonCommand command){
		Person instance = createInstance(command.getRole().getCode(), null); 
		if(instance.getRole() != Role.SUPERUSER){
			((Worker) instance).setProjects(command.getProjects());
		}	    
		instance.setFirstname(command.getFirstname());
		instance.setLastname(command.getLastname());
		instance.setTitle(command.getTitle());
		instance.setAddress(command.getAddress());
		instance.setPostalcode(command.getPostalcode());
		instance.setCity(command.getCity());
		instance.setCountry(command.getCountry());
		instance.setEmail(command.getEmail());
		instance.setPhone(command.getPhone());
		instance.setDateOfBirth(command.getDateOfBirth());
		instance.setSocialSecuritySuffix(command.getSocialSecuritySuffix());		
		return instance;
		
	}
	
	public Role getRole() {
		return role;
	}
	
	protected void setRole(Role role) {
		this.role = role;
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
	
	public void setStatusFromCode(char code) {
		 switch (code) {
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