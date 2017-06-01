package common.model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class is used by Controllers when making selections on the board.
 * Squares are stored in the selection in the order they are added.
 * No square may be added twice to the same selection.
 * @author Nicholas Panzarino
 * @author jasirocki - jdoc
 *
 */
public class Selection implements Iterable<AbstractSquare>{

	/**
	 * Array list of squares in the selection.
	 */
	ArrayList<AbstractSquare> squares=new ArrayList<AbstractSquare>();
	
	/**
	 * Constructor for selection that adds a square.
	 * @param s
	 */
	public Selection(AbstractSquare s){
		add(s);
	}
	
	/**
	 * Adds a square to the selection.
	 * @param s
	 */
	public void add(AbstractSquare s){
		if(contains(s))
			return;
		
		s.setSelected(true);
		squares.add(s);
	}
	
	/**
	 * Gets the square in the array list with index i.
	 * @param i
	 * @return AbstractSquare
	 */
	public AbstractSquare get(int i){
		return squares.get(i);
	}
	
	/**
	 * Clear the selection.
	 */
	public void clear(){
		for(AbstractSquare s:this){
			s.setSelected(false);
		}
		for(int i=0;i<squares.size();i++){
			squares.get(i).setSelected(false);
		}
	}
	
	/**
	 * Return whether the selection contains square s.
	 * @param s
	 * @return boolean
	 */
	public boolean contains(AbstractSquare s){
		return squares.contains(s);
	}
	
	/**
	 * Iterate over the selection of squares.
	 */
	@Override
	public Iterator<AbstractSquare> iterator() {
		return squares.iterator();
	}
	
	/**
	 * Return the number of squares selected.
	 * @return int
	 */
	public int size(){
		return squares.size();
	}

}
