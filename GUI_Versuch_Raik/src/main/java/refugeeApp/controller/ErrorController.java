package refugeeApp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

	@RequestMapping("/startpage?error")
	public String settings(){
		return "startpageError";
	}
	
}