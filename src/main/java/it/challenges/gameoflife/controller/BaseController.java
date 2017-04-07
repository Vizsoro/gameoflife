package it.challenges.gameoflife.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BaseController {

	private static int counter = 0;
	private static final String VIEW_INDEX = "index";
	private static final int BOARD_SIZE = 100;
	private static final int CELL_SIZE = 5;
	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome(ModelMap model) {
//
//		model.addAttribute("message", "Welcome");
//		model.addAttribute("counter", ++counter);
//		

		model.addAttribute("BOARD_SIZE",BOARD_SIZE);
		// Spring uses InternalResourceViewResolver and return back index.jsp
		return VIEW_INDEX;

	}

	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public String welcomeName(@PathVariable String name, ModelMap model) {

		model.addAttribute("message", "Welcome " + name);
		model.addAttribute("counter", ++counter);
		
		return VIEW_INDEX;

	}


}
