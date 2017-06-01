package ebc.model;

/**
 * The model which contains all entity objects.
 * 
 * Often there is a need for a single class that becomes the container of all model
 * elements. This is such a class.
 */
public class Model {

	/** Height of rectangle. */
	Value height;
	
	/** Width of rectangle. */
	Value width;
	
	/** Color in which to color the rectangle (grayscale). */
	Value color;
	
	public Model () {
		height = new Value(10, 120);
		width = new Value(10, 120);
		color = new Value (0, 255);
	}
	
	/** Return height element. */
	public Value getHeight() { return height; }

	/** Return width element. */
	public Value getWidth () { return width; }

	/** Return color element. */
	public Value getColor() { return color; }
}
