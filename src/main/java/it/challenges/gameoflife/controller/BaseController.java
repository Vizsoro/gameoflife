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
	
	@Autowired
	private BoardHandler boardHandler;
	@Autowired
	private CycleManagerInterface cycleManager;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome(ModelMap model) {

		model.addAttribute("board", boardHandler.generateBoard(BOARD_SIZE, probability));
		model.addAttribute("currentCycle",0);
		return VIEW_INDEX;
	}

	
	@RequestMapping(value = "/nextcycle", method = RequestMethod.GET)
	public String nextCycle(ModelMap model){
		cycleManager.moveToNextCycle();
		model.addAttribute("board", boardHandler.getBoard());
		return VIEW_INDEX;
	}
	
	@RequestMapping(value = "/previouscycle", method = RequestMethod.GET)
	public String previousCycle(ModelMap model){
		
		model.addAttribute("board",boardHandler.getPreviousBoard());
		
		return VIEW_INDEX;
	}


}
