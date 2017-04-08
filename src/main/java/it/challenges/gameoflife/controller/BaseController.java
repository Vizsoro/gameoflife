package it.challenges.gameoflife.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.challenges.gameoflife.board.BoardHandler;
import it.challenges.gameoflife.cycle.CycleManagerInterface;

@Controller
public class BaseController {

	private static final String VIEW_INDEX = "index";
	private static final int BOARD_SIZE = 100;
	private static final double probability = 0.5;
	private static final int dieUpper = 3;
	private static final int dieLower = 2;
	private static final int comeAlive = 3;
	private int  cycle;
	
	@Autowired
	private BoardHandler boardHandler;
	@Autowired
	private CycleManagerInterface cycleManager;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome(ModelMap model) {
		cycle= 1;
		model.addAttribute("probability", probability);
		model.addAttribute("dieUpper", 3);
		model.addAttribute("dieLower", 2);
		model.addAttribute("comeAlive",3);
		model.addAttribute("previous", false);
		model.addAttribute("board", boardHandler.generateBoard(BOARD_SIZE, probability));
		model.addAttribute("currentCycle",cycle);
		return VIEW_INDEX;
	}

	
	@RequestMapping(value = "/nextcycle", method = RequestMethod.GET)
	public String nextCycle(ModelMap model){
		cycleManager.moveToNextCycle();
		cycle++;
		model.addAttribute("probability", probability);
		model.addAttribute("dieUpper", 3);
		model.addAttribute("dieLower", 2);
		model.addAttribute("comeAlive",3);
		model.addAttribute("currentCycle",cycle);
		model.addAttribute("previous", true);
		model.addAttribute("board", boardHandler.getBoard());
		return VIEW_INDEX;
	}
	
	@RequestMapping(value = "/previouscycle", method = RequestMethod.GET)
	public String previousCycle(ModelMap model){
		cycle--;
		model.addAttribute("probability", probability);
		model.addAttribute("dieUpper", 3);
		model.addAttribute("dieLower", 2);
		model.addAttribute("comeAlive",3);
		model.addAttribute("currentCycle",cycle);
		model.addAttribute("previous", false);
		model.addAttribute("board",boardHandler.getPreviousBoard());
		
		return VIEW_INDEX;
	}


}
