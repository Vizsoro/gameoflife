package it.challenges.gameoflife.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ch.qos.logback.classic.Logger;
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
	private static final String ERROR_MESSAGE = "Something went wrong! Please restart the game!";
	private static final Logger logger = (Logger) LoggerFactory.getLogger(BaseController.class);
	
	@Autowired
	private CycleManagerInterface cycleManager;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome(ModelMap model) {
		cycle= 1;
		fillWithConstants(model);
		model.addAttribute("previous", false);
		model.addAttribute("currentCycle",cycle);
		try{
			cycleManager.startGame(BOARD_SIZE, probability);
			model.addAttribute("board", cycleManager.getCurrentState());			
		} catch(RuntimeException e){
			logger.error(e.getMessage());
			model.addAttribute("error", ERROR_MESSAGE);
		}
		return VIEW_INDEX;
	}

	
	@RequestMapping(value = "/nextcycle", method = RequestMethod.GET)
	public String nextCycle(ModelMap model){
		fillWithConstants(model);
		model.addAttribute("previous", true);
		try{
			cycleManager.moveToNextCycle();
			model.addAttribute("board", cycleManager.getCurrentState());
			cycle++;
			model.addAttribute("currentCycle",cycle);
		} catch(RuntimeException e){
			logger.error(e.getMessage());
			model.addAttribute("error", ERROR_MESSAGE);
		}
		return VIEW_INDEX;
	}
	
	@RequestMapping(value = "/previouscycle", method = RequestMethod.GET)
	public String previousCycle(ModelMap model){		
		fillWithConstants(model);
		model.addAttribute("previous", false);
		try{
			cycleManager.moveToPreviousCycle();
			model.addAttribute("board",cycleManager.getCurrentState());
			cycle--;
			model.addAttribute("currentCycle",cycle);
		} catch(RuntimeException e){
			logger.error(e.getMessage());
			model.addAttribute("error", ERROR_MESSAGE);
		}
		return VIEW_INDEX;
	}
	
	private void fillWithConstants(ModelMap model){
		model.addAttribute("probability", probability);
		model.addAttribute("dieUpper", dieUpper);
		model.addAttribute("dieLower", dieLower);
		model.addAttribute("comeAlive",comeAlive);
	}


}
