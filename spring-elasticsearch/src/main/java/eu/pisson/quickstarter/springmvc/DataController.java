package eu.pisson.quickstarter.springmvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import eu.pisson.quickstarter.service.DataService;

/**
 * Spring MVC Controller
 * 
 * @author Bertrand Pisson
 *
 */
@Controller
public class DataController {

	@Autowired
	private DataService dataService;
	
	@RequestMapping("list.do")
	public String getList(Model model) {
		model.addAttribute("list", dataService.getLastElements(10));
		return "list";
	}
	
	@RequestMapping("add.do")
	public String addElement(String element, Model model) {
		dataService.addElement(element);
		return getList(model);
	}
}
