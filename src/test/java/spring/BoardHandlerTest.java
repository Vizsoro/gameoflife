package spring;

import static org.junit.Assert.*;

import java.util.List;
import java.util.function.Predicate;

import org.junit.Test;

import it.challenges.gameoflife.board.BoardHandler;
import it.challenges.gameoflife.board.BoardHandlerImplementation;
import it.challenges.gameoflife.board.Cell;
import it.challenges.gameoflife.board.Position;

public class BoardHandlerTest {

	
	private BoardHandler boardHandler = new BoardHandlerImplementation();
	
	@Test
	public void neighbourTest(){
		boardHandler.saveBoard(boardHandler.generateBoard(4, 0.5));
		List<Cell> neighbours = boardHandler.getNeighbours(new Position(0,0));
		
		neighbours.stream().anyMatch(new Predicate<Cell>(){
			@Override
			public boolean test(Cell cell){
				return cell.getPosition().equals(new Position(0,1));
			}
		});

		
		assertTrue(neighbours.stream().anyMatch(new Predicate<Cell>(){
			@Override
			public boolean test(Cell cell){
				return cell.getPosition().equals(new Position(0,1));
			}
		}));
//				findAny((Cell e)-> e.getPosition().equals(new Position(0,1))))
//		assertTrue(neighbours.get(0).getPosition().getX() == 0 && neighbours.get(0).getPosition().getY() == 1);
//		assertTrue(neighbours.get(7).getPosition().getX() == 1 && neighbours.get(7).getPosition().getY() == 3);
	}
}
