package refugeeApp.controller;

import refugeeApp.model.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.ModelMap;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')") // Zugriff nur für diese Rolle spezifiziert
public class AdminController {
	
	 private final UserRepository userRepository;
	 
	 @Autowired
		public AdminController(UserRepository userRepository) {

			this.userRepository = userRepository;
		}
	 
	 @RequestMapping("/user") 
	 public String user (ModelMap modelMap){
		 modelMap.addAttribute("userList", userRepository.findAll()); //Daten für nehmen für die Ausgabe in der späteren Tabelle
		 return "user";
	 }

}
