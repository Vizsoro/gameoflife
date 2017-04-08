package it.challenges.gameoflife.cycle;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.challenges.gameoflife.board.BoardHandler;
import it.challenges.gameoflife.board.Cell;
import it.challenges.gameoflife.rule.Rule;
import it.challenges.gameoflife.rule.RuleFactory;

@Component
public class CycleManager implements CycleManagerInterface {
	
	@Autowired
	private BoardHandler boardHandler;
	@Autowired
	private RuleFactory ruleFactory;
	
	/* (non-Javadoc)
	 * @see it.challenges.gameoflife.cycle.CycleManagerInterface#moveToNextCycle()
	 */
	@Override
	public void moveToNextCycle(){	
		
		boardHandler.prepareForNextCycle();
		
		List<List<Cell>> board = boardHandler.getBoard();
		
		fillNeighbourInfo(board);
		
		calculateCycle(board);
				
	}
	
	
	
	private void fillNeighbourInfo(List<List<Cell>> board){
		for(List<Cell> row : board){
			row.parallelStream().forEach(new Consumer<Cell>() {
				@Override
				public void accept(Cell e) {
					boardHandler.setNeighbourInfo(e);
				}
			});
		}
		
	}
	
	@Autowired
	private void calculateCycle(List<List<Cell>> board){
		for(List<Cell> row : board){
			row.parallelStream().forEach(new Consumer<Cell>(){
				@Override
				public void accept(Cell e){
					CycleManager.this.applyRules(e);
				}
			});
		}
	}
	
	private void applyRules(Cell cell){
		Set<Rule> rules = ruleFactory.findRules(cell);
		for(Rule rule : rules){
			rule.apply(cell);
		}
	}

}
