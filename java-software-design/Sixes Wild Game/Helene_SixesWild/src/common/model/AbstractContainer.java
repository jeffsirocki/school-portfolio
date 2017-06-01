package common.model;

import java.io.Serializable;

/**
 * Defines a subclass of AbstractSquares which can contain Tile Objects.
 * 
 * @author Nicholas Panzarino
 * @author jasirocki - jdoc
 */
public abstract class AbstractContainer extends AbstractSquare implements Serializable{
	
	/**
	 * Serializable ID.
	 */
	private static final long serialVersionUID = -8303954416976988023L;
	
	/**
	 * Tile in container.
	 */
	Tile tile=null;
	
	/**
	 * Fill from reference.
	 */
	ISource fillFrom=null;
	
	/**
	 * Constructor for container in (row,column).
	 * @param row
	 * @param column
	 */
	public AbstractContainer(int row,int column){
		super(row,column);
	}
	
	/**
	 * Constructor for given container.
	 * @param s
	 */
	public AbstractContainer(AbstractContainer s){
		super(s);
		tile=s.peek();
		fillFrom=s.getFillFrom();
	}
	
	/**
	 * Set fill from reference to source.
	 * @param source
	 */
	public void setFillFrom(ISource source){
		fillFrom=source;
	}
	
	/**
	 * Set fill from reference to square.
	 * @param square
	 * @return boolean
	 */
	public boolean setFillFrom(AbstractSquare square){
		if(square instanceof ISource){
			this.fillFrom=(ISource)square;
			return true;
		}
		return false;
	}
	
	/**
	 * Get fill from source.
	 * @return ISource
	 */
	public ISource getFillFrom(){
		return fillFrom;
	}
	
	/**
	 * Remove fill from reference.
	 */
	public void removeFillFrom(){
		fillFrom=null;
	}
	
	/**
	 * Removes the tile from this square and returns it. This is how moves should remove.
	 * tiles from the board (Except possibly for special moves).
	 * @return
	 */
	public Tile remove(){
		if(this.tile==null)
			return null;
		
		Tile t=this.tile.copy();
		this.tile=null;
		return t;
	}
	
	/**
	 * Peek at tile in container.
	 * @return Tile
	 */
	public final Tile peek(){
		return this.tile;
	}
	
	/**
	 * Set tile in container.
	 * @param tile
	 */
	public final void setTile(Tile tile) {
		this.tile = tile;
	}

	/**
	 * Return whether container has tile.
	 * @return boolean
	 */
	public final boolean hasTile(){
		return !(this.tile==(null));
	}
	
	/**
	 * Update tile using fill from reference.
	 */
	public boolean update() {
		if(tile==null){
			tile=fillFrom.takeTile();
			if(tile!=null)
				return true;
		}
		return false;
	}

}
