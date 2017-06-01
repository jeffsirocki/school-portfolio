package player.controllers;

import player.model.AddToSixMove;
import common.controllers.BoardSelectionController;
import common.model.AbstractContainer;
import common.model.AbstractSquare;
import common.model.Selection;
import common.model.Square;
import common.model.Tile;
import common.view.BoardView;

/**
 * Controller for making selectSix Moves on the board.
 * @author Nicholas Panzarino
 *
 */
public class SelectSixController extends BoardSelectionController {

	/**
	 * Create a new SelectSixController to modify the given board.
	 * @param board
	 */
	public SelectSixController(BoardView board) {
		super(board);
	}

	/**
	 * Determine if the input square can be added to the selection.
	 */
	@Override
	public boolean isValid(AbstractSquare s) {
		if(!super.isValid(s))
			return false;
		
		//Only Squares can be selected
		if(s instanceof Square){
		
			//The square must have a tile
			if(((Square) s).peek()==null)
				return false;
			
			//Squares with a 6 tile cannot be selected
			if(((Square) s).peek().getVal()==6)
				return false;
			
			//If there is already a selection, the square must be adjacent to it
			if(getSelection()!=null){
				boolean b=false;
				for(AbstractSquare sel:getSelection()){
					if(isAdjacent(s, sel))
						b=true;
				}
				if(b==false)
					return false;
			}
			
			//The square and selection may not add up to greater than 6
			if(sum()+((Square) s).peek().getVal()>6){
				return false;
			}
			
			return true;
		}
		else
			return false;
	}

	/**
	 * When the mouse is released, create and push the move.
	 */
	@Override
	public void doReleased(Selection selection) {
		super.doReleased(selection);
		if(sum()==6){
			AddToSixMove m = new AddToSixMove(selection);
			PlayerAppReference.get().getGameView().getTheGame().doMove(m);
			PlayerAppReference.get().getGameView().getBoardView().updateBoard();
			PlayerAppReference.updateFields();
		}
	}
	
	int sum(){
		int sum=0;
		if(getSelection()==null)
			return 0;
		for(AbstractSquare s:getSelection()){
			if(s instanceof AbstractContainer){
				Tile t=((AbstractContainer) s).peek();
				if(t!=null)
					sum+=t.getVal();
			}
		}
		return sum;
	}

	/**
	 * Helper function for determining if two squares are adjacent to eachother.
	 */
	boolean isAdjacent(AbstractSquare s1,AbstractSquare s2){
		if(s1.getRow()==s2.getRow()){
			if(s1.getColumn()==s2.getColumn()+1)
				return true;
			else if(s1.getColumn()==s2.getColumn()-1)
				return true;
		}
		else if(s1.getColumn()==s2.getColumn()){
			if(s1.getRow()==s2.getRow()+1)
				return true;
			else if(s1.getRow()==s2.getRow()-1)
				return true;
		}
		return false;
	}
	
	/**
	 * overrides the current selection of this controller.
	 * @param selection
	 */
	public void setSelection(Selection selection)
	{
		this.selection = selection;
	}
}
