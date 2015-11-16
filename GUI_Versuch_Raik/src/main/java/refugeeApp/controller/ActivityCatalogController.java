package refugeeApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import refugeeApp.model.Activity;
import refugeeApp.model.Activity.ActivityType;
import refugeeApp.model.ActivityCatalog;

@Controller
@PreAuthorize("hasRole('ROLE_SUPPORTER') or hasRole('ROLE_REFUGEE') or hasRole('ROLE_ADMIN')")
public class ActivityCatalogController {

	private final ActivityCatalog activityCatalog;

	@Autowired
	public ActivityCatalogController(ActivityCatalog activityCatalog) {

		this.activityCatalog = activityCatalog;
	}

	@RequestMapping("/activityCatalog")
	public String activityCatalog(ModelMap modelMap) {

		modelMap.addAttribute("activitycatalog", activityCatalog.findByType(ActivityType.Activity));

		return "activityCatalog";
	}
	
	@RequestMapping("/activity/{pid}")
	public String activity(@PathVariable("pid") Activity activity, Model model) {
		
		model.addAttribute("activity", activity);
		
		return "activity";
	}
	
	@RequestMapping("/activity/delete/{pid}")
	public String activitydelete(@PathVariable("pid") Activity activity, Model model) {
		
		activityCatalog.delete(activity.getId());
		
		
		return "redirect:/activityCatalog";
	}
	
}
