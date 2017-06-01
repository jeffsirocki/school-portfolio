package common.model;

import java.io.Serializable;

/**
 * Interface that describes behavior for fill from sources.
 * @author njpanzarino
 * @author jasirocki - jdoc
 *
 */
public interface ISource extends Serializable{
	
	/**
	 * Serializable ID.
	 */
	public static final long serialVersionUID = -8303954416976988023L;


	/**
	 * removes the tile from this source.
	 * fills this source by calling takeTile() from specified "fillFrom" source.
	 * returns the tile which was removed.
	 * @return
	 */
	public Tile takeTile();
	
	/**
	 * sets the tile in this source to the input tile.
	 * pushes the tile that was in this source to its "fillFrom" source.
	 * @param tile
	 */
	public void push(Tile tile);

	/**
	 * Peek at tile.
	 * @return Tile
	 */
	public Tile peek();
}
