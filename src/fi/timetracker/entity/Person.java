package fi.timetracker.entity;

import java.util.Date;

/**
 * 
 * @author Petteri Parviainen
 *
 */
public abstract class Person extends Entity {
	
	private enum Role {WORKER, MANAGER, ADMIN};
	private Role role;	
		
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
	private Person creator;
	
	//note! password is never used in entity 
}