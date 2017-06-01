package common.model;

import java.awt.Color;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import common.view.AbstractSquareDrawer;

/**
 * AbstractSquare defines any object which can be placed on a board.
 * 
 * @author Nicholas Panzarino
 * @author jasirocki - jdoc
 */
public abstract class AbstractSquare extends AbstractType implements ICopy,Serializable{
	
	/**
	 * Serializable ID.
	 */
	private static final long serialVersionUID = -8303954416976988023L;
	
	/**
	 * Color list for squares.
	 */
	public static Color[] SQUARE_COLOR={Color.WHITE,Color.PINK,Color.RED,Color.ORANGE, Color.YELLOW,Color.GREEN,Color.CYAN};
	
	/**
	 * Color of selection highlight.
	 */
	public static Color SELECTED_COLOR=new Color(100,100,100,200);
	
	/**
	 * Row of square.
	 */
	int row;
	
	/**
	 * Column of square.
	 */
	int column;
	
	/**
	 * Boolean indicating whether square is selected.
	 */
	boolean selected=false;

	/**
	 * Constructor for AbstractSquare given (row, column).
	 * @param row
	 * @param column
	 */
	public AbstractSquare(int row,int column){
		this.row=row;
		this.column=column;
	}
	
	/**
	 * Constructor for AbstractSquare given an AbstractSquare.
	 * @param s
	 */
	public AbstractSquare(AbstractSquare s){
		this.row=s.getRow();
		this.column=s.getColumn();
	}
	
	/**
	 * Get row of square.
	 * @return row
	 */
	public final int getRow() {
		return row;
	}

	/**
	 * get column of square.
	 * @return column
	 */
	public final int getColumn() {
		return column;
	}
	
	/**
	 * Set selected square.
	 * @param selected
	 */
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	
	/**
	 * Get selected flag.
	 * @return selected
	 */
	public boolean isSelected() {
		return selected;
	}
	
	/**
	 * Static Method that Determines the String value of this type of Square.
	 * This will be displayed in ComboBoxes.
	 * All subclasses should overwrite/hide this method.
	 * @return
	 */
	public static String type(){
		throw new IllegalArgumentException("A Subclass of AbstractSquare does not declare a 'public static String type()' Method.");
	}
	
	/**
	 * Update method for square.
	 * @return boolean
	 */
	public abstract boolean update();
	
	/**
	 * Get square drawer.
	 * @return AbstractSquareDrawer
	 */
	public abstract <T extends AbstractSquareDrawer<E>, E extends AbstractSquare> T getDrawer();

	/**
	 * If any square must initialize to a specific state, it should overwrite this function.
	 * This will be the last thing board calls before starting a game. 
	 */
	public void initialize() {
		
	}
	
	/**
	 * Make copy of the square.
	 */
	public AbstractSquare makeCopy(){
		try {
			return this.getClass().getDeclaredConstructor(this.getClass()).newInstance(this);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			System.err.println("Could not copy class: "+this.getClass().getSimpleName()+" using memento constructor");
			//e.printStackTrace();
		}
		return null;
	}
	
}
