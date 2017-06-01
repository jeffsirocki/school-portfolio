package player.model;

import player.controllers.PlayerAppReference;
import player.controllers.RemoveSquareController;
import common.controllers.BoardSelectionController;
import common.model.AbstractContainer;
import common.model.AbstractMove;
import common.model.AbstractSquare;
import common.model.Board;
import common.model.Tile;

/**
 * Move class for removing a single tile from the board.
 * @author Nicholas Panzarino
 *
 */
public class RemoveMove extends AbstractMove {

	/**
	 * the board to perform the move on.
	 */
	Board board;
	
	/**
	 * The square to remove the tile from.
	 */
	AbstractSquare square;
	
	/**
	 * the tile that was removed.
	 */
	Tile tile;
	
	/**
	 * Constructor given the board and square
	 * @param board
	 * @param square
	 */
	public RemoveMove(Board board, AbstractSquare square) {
		this.board = board;
		this.square = square;
	}

	/**
	 * removes the tile from the square and updates the board
	 */
	@Override
	public boolean doMove() {
		if(board==null)
			return false;
		if(square instanceof AbstractContainer){
			Tile t=((AbstractContainer) square).peek();
			if(t==null){
				return false;
			}
			if(t.getVal()==6){
				return false;
			}
			tile=((AbstractContainer) square).remove();
			board.update();
			return true;
		}
		return false;
	}
	
	/**
	 * Give this move a type name
	 */
	public static String type(){
		return "Remove";
	}

	/**
	 * Return a controller which can perform this move on a boardView object
	 */
	public static BoardSelectionController controller() {
		return new RemoveSquareController(PlayerAppReference.get().getGameView().getBoardView());
	}

}
