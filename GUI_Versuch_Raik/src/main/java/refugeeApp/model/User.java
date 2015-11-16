package refugeeApp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.salespointframework.useraccount.UserAccount;

@Entity
public class User {
	
	private @Id @GeneratedValue long id; //Id generieren
	private String role; //hier könnte man auch Adresse, Herkunft etc. noch spezifizieren
	
	@OneToOne private UserAccount userAccount;
	
	@SuppressWarnings("unused") //verhindert die Compiler Warnung
	private User() {}
	
	public User(UserAccount userAccount, String role){
		this.userAccount = userAccount;
		this.role = role;
	}
	public long getId(){
		return id;
	}
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	public UserAccount getUserAccount() {
		return userAccount;
	}
}
