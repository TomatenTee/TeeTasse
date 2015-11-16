package refugeeApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import refugeeApp.model.Good.GoodType;
import refugeeApp.model.GoodCatalog;

@Controller
@PreAuthorize("hasRole('ROLE_REFUGEE') or hasRole('ROLE_ADMIN')")
public class GoodCatalogController {
	
	private final GoodCatalog goodCatalog;
	
	@Autowired
	public GoodCatalogController(GoodCatalog goodCatalog) { 

		this.goodCatalog = goodCatalog;
	}
	
	
	@RequestMapping("/goodCatalog")
	public String gooodCatalog(ModelMap modelMap) {

		modelMap.addAttribute("goodcatalog", goodCatalog.findByType(GoodType.Good));

		return "goodCatalog";
	}
}
