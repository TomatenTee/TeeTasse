package refugeeApp.model.validation;

import org.hibernate.validator.constraints.NotEmpty;

public class RegistrationForm {

	private String name;
	@NotEmpty(message = "{RegistrationForm.name.NotEmpty}")

	private String password;
	@NotEmpty(message = "{RegistrationForm.password.NotEmpty}")

	private String role;
	@NotEmpty(message = "{RegistrationForm.role.NotEmpty}")

	private String forename;
	@NotEmpty(message = "{RegistrationForm.forename.NotEmpty}")

	private String username;
	@NotEmpty(message = "{RegistrationForm.username.NotEmpty}")

	private String email;
	@NotEmpty(message = "{RegistrationForm.email.NotEmpty}")
	
	private String postcode;
	
	private String address;
	private String passwordwdh;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPasswordwdh() {
		return passwordwdh;
	}

	public void setPasswordwdh(String passwordwdh) {
		this.passwordwdh = passwordwdh;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public String getForename(){
		return forename;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}
	
	public void setPostcode(String postcode){
		this.postcode = postcode;
	}
	
	public String getPostcode(){
		return postcode;
	}
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
