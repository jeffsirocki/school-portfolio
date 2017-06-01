package player.model;

import player.controllers.PlayerAppReference;
import player.controllers.Swap2Controller;
import common.controllers.BoardSelectionController;
import common.model.AbstractContainer;
import common.model.AbstractMove;
import common.model.AbstractSquare;
import common.model.Board;
import common.model.Tile;

/**
 * Move class for swapping the tiles in two adjacent square.
 * @author Nicholas Panzarino
 *
 */
public class Swap2Move extends AbstractMove {

	/**
	 * the board containting these squares.
	 */
	Board board;
	
	/**
	 * the first square.
	 */
	AbstractSquare square;
	
	/**
	 * the second square.
	 */
	AbstractSquare square2;
	
	/**
	 * Create a new Swap move using a board and two squares.
	 */
	public Swap2Move(Board board, AbstractSquare square,
			AbstractSquare square2) {
		this.board = board;
		this.square = square;
		this.square2 = square2;
	}

	/**
	 * Perform this move. Sqap the tiles in the two squares on the board.
	 */
	@Override
	public boolean doMove() {
		if((square instanceof AbstractContainer) && (square2 instanceof AbstractContainer)){
			Tile t1=((AbstractContainer) square).peek();
			Tile t2=((AbstractContainer) square2).peek();
			
			if((t1==null)||(t2==null))
				return false;
			
			((AbstractContainer) square2).setTile(t1);
			((AbstractContainer) square).setTile(t2);
			board.update();
			return true;
		}
		return false;
	}
	
	/**
	 * give this move type a name.
	 */
	public static String type(){
		return "Swap 2";
	}

	/**
	 * return a controller that is capable of performing this move.
	 */
	public static BoardSelectionController controller() {
		return new Swap2Controller(PlayerAppReference.get().getGameView().getBoardView());
	}

}
