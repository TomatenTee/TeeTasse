package refugeeApp.controller;

import javax.validation.Valid;

import org.salespointframework.useraccount.Role;
import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.UserAccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import refugeeApp.model.validation.RegistrationForm;
import refugeeApp.model.User;
import refugeeApp.model.UserRepository;

@Controller
public class StartpageController extends RegistrationForm{
	
	private final UserAccountManager userAccountManager;
	private final UserRepository userRepository;

	@Autowired
	public StartpageController(UserAccountManager userAccountManager, UserRepository userRepository) {

		Assert.notNull(userAccountManager, "UserAccountManager must not be null!");
		Assert.notNull(userRepository, "UserRepository must not be null!");

		this.userAccountManager = userAccountManager;
		this.userRepository = userRepository;
	}
	
	@RequestMapping(value={"/startpage"})
	public String startpage(ModelMap modelMap) {
		modelMap.addAttribute("registrationForm", new RegistrationForm());
		return "startpage";
	}
	
	// Über @Valid können wir die Eingaben automagisch prüfen lassen, ob es Fehler gab steht im BindingResult, dies muss direkt nach dem @Valid Parameter folgen.
	@RequestMapping("/registerNew")
	public String registerNew(@ModelAttribute("registrationForm") @Valid RegistrationForm registrationForm,
			BindingResult result) {

		String refugee = new String("Refugee");
		
		if (result.hasErrors()) {
			return "startpage";
		}
		
		int password = registrationForm.getPassword().hashCode();
		int passwordwdh = registrationForm.getPasswordwdh().hashCode();
		
		if (password != passwordwdh){
			return "startpageError";
		}
		else {
			
		
			if (registrationForm.getRole().equals(refugee)){
			@SuppressWarnings("deprecation")
			//hier wird ein Benutzer erstellt vom Typ "Refugee"
			UserAccount userAccount = userAccountManager.create(registrationForm.getUsername(), registrationForm.getPassword(), new Role("ROLE_REFUGEE"));
			userAccountManager.save(userAccount);
	
			User user = new User(userAccount, registrationForm.getRole());
			userRepository.save(user);
	
			return "redirect:/";
			}
			
			else {
				@SuppressWarnings("deprecation")
				//hier wird ein Benutzer erstellt vom Typ "Supporter"
				UserAccount userAccount = userAccountManager.create(registrationForm.getUsername(), registrationForm.getPassword(), new Role("ROLE_SUPPORTER"));
				userAccountManager.save(userAccount);
	
				User user = new User(userAccount, registrationForm.getRole());
				userRepository.save(user);
	
				return "redirect:/";
			}
		}
		
	}
	
	/*@RequestMapping("/register")
	public String register(ModelMap modelMap) {
		modelMap.addAttribute("registrationForm", new RegistrationForm());
		return "startpage";
	}*/
}
