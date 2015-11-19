package refugeeApp.controller;



import static org.salespointframework.core.Currencies.EURO;

import javax.validation.Valid;

import org.javamoney.moneta.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import refugeeApp.model.Activity;
import refugeeApp.model.Activity.ActivityType;
import refugeeApp.model.ActivityCatalog;
import refugeeApp.model.validation.ActivityForm;

@Controller
@PreAuthorize("hasRole('ROLE_SUPPORTER') or hasRole('ROLE_REFUGEE') or hasRole('ROLE_ADMIN')")
public class CreateControllerActivity extends ActivityForm {
	
	private final ActivityCatalog activityCatalog;
	
	@Autowired
	public CreateControllerActivity(ActivityCatalog activityCatalog) {
		
		this.activityCatalog = activityCatalog;
			
	}
	
	@RequestMapping("/createActivity")
	public String createActivity(ModelMap modelmap){
		
		modelmap.addAttribute("activityForm", new ActivityForm());
		
		return "createActivity";
	}
	
	@RequestMapping(value = "/createActivityNew", method=RequestMethod.POST)
	public String createActivityNew (@ModelAttribute("activityForm") @Valid ActivityForm activityForm, BindingResult result){
		
		activityCatalog.save(new Activity(activityForm.getName(),activityForm.getImage(),activityForm.getDescription(), Money.of(14.99, EURO),activityForm.getCategory(), ActivityType.Activity));
		
		return "redirect:/";
	}
	
	
	
	
	
	
	
	
	
	
}
