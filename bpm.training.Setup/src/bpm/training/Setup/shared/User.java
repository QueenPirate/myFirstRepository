package bpm.training.Setup.shared;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long userId;
	private String firstName;
	private String lastName;
	private String emailAdd;
	private Date birthDate;
	private String country;
	private String password;
	
	public User() {
		
	}
	public User(String emailAdd, String password) {
		super();
		this.emailAdd = emailAdd;
		this.password = password;
	}
	public User(long userId, String firstName, String lastName,
			String emailAdd, Date birthDate, String country, String password) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAdd = emailAdd;
		this.birthDate = birthDate;
		this.country = country;
		this.password = password;
	}
	public User(String firstName, String lastName, String emailAdd,
			Date birthDate, String country, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAdd = emailAdd;
		this.birthDate = birthDate;
		this.country = country;
		this.password = password;
	}

	public long getUserId() {
		return userId;
	}


	public void setUserId(long userId) {
		this.userId = userId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmailAdd() {
		return emailAdd;
	}


	public void setEmailAdd(String emailAdd) {
		this.emailAdd = emailAdd;
	}


	public Date getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	

}
