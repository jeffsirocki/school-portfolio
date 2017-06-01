package common.model;

import java.awt.Point;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;

/**
 * Board that contains all abstract squares for the game and is copyable.
 * @author njpanzarino
 * @author jasirocki - jdoc
 *
 */
public class Board implements Iterable<AbstractSquare>, ICopy, Serializable{
	
	/**
	 * Serializable ID.
	 */
	private static final long serialVersionUID = -8303954416976988023L;
	
	/**
	 * Determines the type of AbstractSquare that will be added when the board is generated or resized.
	 */
	protected Class<? extends AbstractSquare> defaultType=Square.class;
	
	/**
	 * Size of the board.
	 */
	protected int size;

	/**Stores squares in a hashtable with keys equal to: (row)+","+(column)"*/
	protected Hashtable<String,AbstractSquare> squares=new Hashtable<String,AbstractSquare>();
	
	/**
	 * Constructor for board given size.
	 * @param size
	 */
	public Board(int size){
		setSize(size);
	}
	
	/**
	 * Constructor for board given a board.
	 * @param board
	 */
	public Board(Board board){
		this.size=board.getSize();
		for(AbstractSquare s:board){
			//System.out.println(s);
			squares.put(s.getRow()+","+s.getColumn(),s.makeCopy());
		}
		this.defaultType=board.defaultType;
	}
	
	/**
	 * Set size for the board.
	 * @param size
	 */
	public void setSize(int size){
		this.size=size;
		Hashtable<String,AbstractSquare> s=new Hashtable<String,AbstractSquare>();
		for(int r=0;r<size;r++){
			for(int c=0;c<size;c++){
				if(squares.containsKey(r+","+c)){
					s.put(r+","+c,squares.get(r+","+c));
				} else
					try {
						s.put(r+","+c,defaultType.getDeclaredConstructor(int.class,int.class).newInstance(r,c));
					} catch (InstantiationException | IllegalAccessException
							| IllegalArgumentException
							| InvocationTargetException | NoSuchMethodException
							| SecurityException e) {
						System.err.println("could not create instance of default square class: "+defaultType.getSimpleName());
						//e.printStackTrace();
					}
			}
		}
		squares=s;
	}
	
	/**
	 * Returns the size of the board.
	 * @return
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * returns a copy of this board and all its squares.
	 * used to get a board without a reference to the original.
	 * @return
	 */
	public Board makeCopy()
	{
		return new Board(this);
	}
	
	/**
	 * Copy the board.
	 */
	public void copy(ICopy object){
		Board board=(Board)object;
		squares=new Hashtable<String,AbstractSquare>();
		this.size=board.getSize();
		for(AbstractSquare s:board){
			//System.out.println(s);
			squares.put(s.getRow()+","+s.getColumn(),s.makeCopy());
		}
		this.defaultType=board.defaultType;
	}
	
	/**
	 * returns the Abstract Square Object at the specified position.
	 * 	if no such square object exists, null will be returned.
	 * @param row
	 * @param column
	 * @return
	 */
	public AbstractSquare getSquare(int row,int column){
		return squares.get(row+","+column);
	}
	
	/**
	 * Get square at point P.
	 * @param p
	 * @return AbstractSquare
	 */
	public AbstractSquare getSquare(Point p){
		try{
			return getSquare((int)p.getX(),(int)p.getY());
		}catch(Exception e){
			return null;
		}
	}
	
	/**
	 * Set default type of board.
	 * @param defaultType
	 */
	public void setDefaultType(Class<? extends AbstractSquare> defaultType) {
		for(AbstractSquare s:this){
			if(s.getClass().equals(this.defaultType)){
				changeType(s,defaultType);
			}
		}
		this.defaultType = defaultType;
	}

	/**
	 * Change the board type.
	 * returns the new square;
	 */
	public void changeType(AbstractSquare s,Class<? extends AbstractSquare> c){
		AbstractSquare old=squares.get(s.getRow()+","+s.getColumn());
		AbstractSquare squ=old;
		try {
			squ=c.getDeclaredConstructor(int.class,int.class).newInstance(old.getRow(),old.getColumn());
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			System.err.println("Could not create instance of class: "+c.getSimpleName());
			//e.printStackTrace();
		}
		squares.remove(squ.getRow()+","+squ.getColumn());
		squares.put(squ.getRow()+","+squ.getColumn(), squ);
	}
	
	/**
	 * Remove a tile at row r and column c.
	 * @param r
	 * @param c
	 * @return boolean
	 */
	public boolean removeTile(int r,int c){
		AbstractSquare squ=squares.get(r+","+c);
		if(squ instanceof AbstractContainer){
			((AbstractContainer)squ).remove();
			return false;
		}
		else
			return true;
	}
	
	/**
	 * Removes the tiles from all Container objects in the board.
	 */
	public void removeAllTiles(){
		for(AbstractSquare s:this){
			if(s instanceof AbstractContainer){
				((AbstractContainer)s).remove();
			}
		}
	}
	
	/**
	 * sets "fillFrom" references of all Container objects in the board to "null".
	 */
	public void removeAllReferences(){
		for(AbstractSquare s:this){
			if(s instanceof AbstractContainer){
				((AbstractContainer)s).removeFillFrom();
			}
		}
	}
	
	/**
	 * Update this board.
	 */
	public void update(){
		for(AbstractSquare s:this){
			s.update();
		}
	}
	
	/**
	 * Initialize the board.
	 */
	public void initialize(){
		removeAllTiles();
		update();
		for(AbstractSquare s:this){
			s.initialize();
		}
	}

	/**
	 * Iterate through the board filled with AbstractSquares.
	 */
	@Override
	public Iterator<AbstractSquare> iterator() {
		return Collections.list(squares.elements()).iterator();
	}

	/**
	 * Return a print out of the board with tile numbers within squares.
	 */
	@Override
	public String toString() {
		String p="";
		for(int r=0;r<size;r++){
			p+="|";
			for(int c=0;c<size;c++){
				p+=squares.get(r+","+c).toString()+"|";
			}
			p+="\n";
		}

		return p;
	}
}
