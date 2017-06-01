package player.model;

import java.util.ArrayList;

import player.controllers.PlayerAppReference;
import player.controllers.ShuffleBoardController;
import common.controllers.BoardSelectionController;
import common.model.AbstractContainer;
import common.model.AbstractMove;
import common.model.AbstractSquare;
import common.model.Board;
import common.model.Tile;

/**
 * Move class for shiffling all tiles on the board (Except sixes).
 * @author Nicholas Panzarino
 *
 */
public class ShuffleMove extends AbstractMove {

	/**
	 * The board to be shuffled
	 */
	Board board;
	
	/**
	 * Create a new move using the given board
	 */
	public ShuffleMove(Board board){
		this.board=board;
	}
	
	/**
	 * Shuffle the tiles on the board
	 */
	@Override
	public boolean doMove() {
		if(this.board==null){
			return false;
		}
		ArrayList<Tile> tiles=new ArrayList<Tile>();
		ArrayList<AbstractContainer> squares=new ArrayList<AbstractContainer>();
		//Store all tiles
		for(AbstractSquare s:board){
			if(s instanceof AbstractContainer){
				Tile t=((AbstractContainer) s).peek();
				if(t!=null && t.getVal()!=6){
					tiles.add(((AbstractContainer) s).remove());
					squares.add((AbstractContainer) s);
				}
			}
		}
		//Replace all tiles
		for(AbstractSquare s:squares){
			((AbstractContainer) s).setTile(tiles.get((int)(Math.random()*tiles.size())));
		}
		board.update();
		return true;
	}
	
	/**
	 * Give this move type a name
	 */
	public static String type(){
		return "Shuffle";
	}

	/**
	 * Return a controller that can perform this move
	 */
	public static BoardSelectionController controller() {
		return new ShuffleBoardController(PlayerAppReference.get().getGameView().getBoardView());
	}

}
