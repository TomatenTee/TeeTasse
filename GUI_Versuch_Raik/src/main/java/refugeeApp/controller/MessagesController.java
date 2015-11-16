package refugeeApp.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@PreAuthorize("hasRole('ROLE_SUPPORTER') or hasRole('ROLE_REFUGEE') or hasRole('ROLE_ADMIN')")
public class MessagesController {

	@RequestMapping("/messages")
	public String messages(){
		return "messages";
	}
	
}
