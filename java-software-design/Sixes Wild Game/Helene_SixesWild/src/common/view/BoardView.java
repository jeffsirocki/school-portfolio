package common.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import common.model.AbstractSquare;
import common.model.Board;

/**
 * Displays the view of the board properly and is serializable.
 * @author npanzarino
 * @author jasirocki - jdoc
 *
 */
public class BoardView extends JPanel implements IUpdateFields{
	
	/** Serializable ID. */
	private static final long serialVersionUID = 2016456516978998290L;

	/** Board that is being displayed. */
	Board board;
	
	/** Drawers used to make the board. */
	ArrayList<AbstractSquareDrawer<AbstractSquare>> drawers;
	
	/**
	 * Determines whether or not to draw fill from references on the board.
	 */
	boolean drawRef=false;
	
	/** Constructor for the Boardview that takes in a board. */
	public BoardView(Board board) {
		setLayout(null);
		initialize();
	}
	
	/** Initialize the board view. */
	public void initialize(){
		setBoard(board);
	}
	
	/**
	 * @param x	x-coordinate of the point of a mouse event on the BoardView object
	 * @param y	y-coordinate of the point of a mouse event on the BoardView object
	 * @return	The Square object corresponding to the input mouse point
	 */
	public AbstractSquare squareAt(int x,int y){
		if(board==null){
			System.err.println("No Board to find SquareAt: "+x+","+y);
			return null;
		}
		if(x<0||y<0)
			return null;
		
		return board.getSquare(y*board.getSize()/this.getHeight(),x*board.getSize()/this.getWidth());
	}

	/**
	 * Takes in a board object and reconfigures this view to display it.
	 * @param board
	 */
	public void setBoard(Board board){
		this.board=board;
		drawers=new ArrayList<AbstractSquareDrawer<AbstractSquare>>();
		
		if(board==null)
			return;
		
		updateDrawers();
	}
	
	/**
	 * Show all references.
	 * @param show
	 */
	public void showReferences(boolean show){
		drawRef=show;
		this.redraw();
	}
	
	/**
	 * Returns the board.
	 * @return Board
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * Fills all empty tiles.
	 */
	public void updateBoard(){
		board.update();
	}

	/**
	 * Sets the bounds for the board.
	 */
	@Override
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		
		for(AbstractSquareDrawer<AbstractSquare> d:drawers){
			//d.setBounds(board.getSize(),this.getWidth(),this.getHeight());
			d.setBounds(this.getWidth()/board.getSize(),this.getHeight()/board.getSize());
		}
	}

	/**
	 * Sets the minimum size of the board.
	 */
	@Override
	public Dimension getMinimumSize() {
		return new Dimension(100,100);
	}
	
	/**
	 * Redraws the board given the parameters.
	 */
	public void redraw(){
		setVisible(false);
		setVisible(true);
	}

	/**
	 * Sets paint componants to color the board.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for(AbstractSquareDrawer<AbstractSquare> d:drawers){
			if(d.getClass().equals(EmptySquareDrawer.class))
				d.drawOn(g);
		}
		for(AbstractSquareDrawer<AbstractSquare> d:drawers){
			if(!d.getClass().equals(EmptySquareDrawer.class))
				d.drawOn(g);
		}
		
		if(drawRef){
			for(AbstractSquareDrawer<AbstractSquare> d:drawers){
				d.drawReference(g);
			}
		}
	}

	/**
	 * Allows for the board to change types.
	 * @param s
	 * @param c
	 */
	public void changeType(AbstractSquare s,Class<? extends AbstractSquare> c){
		board.changeType(s,c);
		setBoard(board);
	}

	/**
	 * Updates the drawers for each square on the board.
	 */
	public void updateDrawers() {
		for(AbstractSquare s:board){
			AbstractSquareDrawer<AbstractSquare> d=s.getDrawer();
			d.setBounds(this.getWidth()/board.getSize(),this.getHeight()/board.getSize());
			drawers.add(d);
		}
	}

	/**
	 * Updates all fields, drawers, and redraws the board.
	 */
	@Override
	public void updateFields() {
		updateDrawers();
		redraw();
	}
}
