package refugeeApp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import refugeeApp.model.Activity;
import refugeeApp.model.ActivityCatalog;
import refugeeApp.model.validation.ActivityForm;

@Controller
@PreAuthorize("hasRole('ROLE_SUPPORTER') or hasRole('ROLE_REFUGEE') or hasRole('ROLE_ADMIN')")
public class ActivityController {

	private final ActivityCatalog activityCatalog;

	@Autowired
	public ActivityController(ActivityCatalog activityCatalog) {

		this.activityCatalog = activityCatalog;
	}

	@RequestMapping("/changeName")
	public String changedName(Activity activity, Model model, @ModelAttribute("activityForm") @Valid ActivityForm activityForm,
			BindingResult result) {
		
		activity.setName(activityForm.getName());
		model.addAttribute("activity", activity);
		
		return "redirect:/";
	}
	
	@RequestMapping("/changeDescription")
	public String changeDescription(@ModelAttribute("activityForm") @Valid ActivityForm activityForm, Activity activity, Model model){
	
		return"redirect:'/activity' + activity.getId()";
	}
}
