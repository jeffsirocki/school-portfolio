package common.model;

import java.io.Serializable;

/**
 * Tile contains a value and a bonus value.
 * @author njpanzarino
 * @author jasirocki - jdoc
 *
 */
public class Tile implements ICopy, Serializable{
	
	/**
	 * Serializable ID.
	 */
	private static final long serialVersionUID = -8303954416976988023L;
	
	/**
	 * Tile value (between 1-6).
	 */
	int val;
	
	/**
	 * Tile bonus (between 1-3).
	 */
	int bonus;
	
	/**
	 * Constructor for Tile.
	 * @param val
	 * @param bonus
	 */
	public Tile(int val,int bonus){
		this.val=val;
		this.bonus=bonus;
	}
	
	/**
	 * Copy the tile without references.
	 */
	public Tile copy(){
		return new Tile(this.val,this.bonus);
	}

	/**
	 * Get the value of the tile.
	 * @return val
	 */
	public int getVal() {
		return val;
	}

	/**
	 * Get the bonus of the tile.
	 * @return bonus
	 */
	public int getBonus() {
		return bonus;
	}

	/**
	 * Empty copy
	 */
	public void copy(ICopy object)
	{
		
	}

	/**
	 * Copy the object.
	 */
	@Override
	public Tile makeCopy() {
		return new Tile(this.val, this.bonus);
	}
}
