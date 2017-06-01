package common.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import common.model.AbstractContainer;
import common.model.AbstractSquare;
import common.model.ISource;

import javax.swing.JLabel;

/**
 * Contains functional methods for displaying/drawing AbstractSquares on a graphics object.
 * Different types of squares can have different drawers which change how the square is displayed by overriding methods in this class.
 * @author Nicholas Panzarino
 * @author jasirocki - jdoc
 *
 * @param <T>
 */
public abstract class AbstractSquareDrawer<T extends AbstractSquare> {
	
	/**
	 * Square needs to have an Parameterized type so that subclasses can access
	 * information not common to all AbstractSquares.
	 */
	protected T square;
	
	/** Tile Value. */
	JLabel lblText;
	
	/** Tile height. */
	protected int tileHeight;
	
	/** Tile width. */
	protected int tileWidth;
	
	//Fields used to draw squares
	/** Border thickness. */
	protected static int borderThickness;
	
	/** Background Color. */
	protected Color background;
	
	/** Foreground Color. */
	protected Color foreground;
	
	/** Border Color. */
	protected Color borderColor;
	
	/** Selection Color. */
	protected Color selectedColor;
	
	/** Text to Display. */
	protected String text;
	
	/** Multiplier Text. */
	protected String subText;
	
	/** Number of bordering tiles. */
	protected int numBorders;
	
	/** Location of square. */
	protected int atX,atY;
	
	/**
	 * Constructor takes in square.
	 * @param square
	 */
	public AbstractSquareDrawer(T square){
		this.square=square;
		updateFields();
	}
	
	/**
	 * Set square.
	 * @param square
	 */
	public final void setSquare(T square){
		this.square=square;
		updateFields();
	}
	
	/**
	 * Update display of fields for square.
	 */
	public final void updateFields(){
		background=getBackground();
		foreground=getForeground();
		text=getText();
		subText=getSubText();
		borderThickness=getBorderThickness();
		borderColor=getBorderColor();
		selectedColor=getSelectedColor();
		numBorders=getNumBorders();
	}
	
	/**
	 * returns number of borders to draw (one inside the other).
	 * DEFAULT=1
	 */
	public int getNumBorders(){
		return 1;
	}

	/**
	 * returns the thickness of the border (in pixels).
	 * DEFAULT=1
	 */
	public int getBorderThickness(){
		return 1;
	}
	
	/**
	 * returns the color of the border.
	 * DEFAULT=Color.BLACK
	 */
	public Color getBorderColor(){
		return Color.BLACK;
	}
	
	/**
	 * returns the color of the background when this square is selected.
	 * DEFAULT=AbstractSquare.SELECTED_COLOR
	 */
	public Color getSelectedColor(){
		return AbstractSquare.SELECTED_COLOR;
	}
	
	/**
	 * returns the text to draw in the bottom right corner of the square.
	 * DEFAULT=""
	 */
	public String getSubText(){
		return "";
	}

	/**
	 * returns the text to draw in the center of the square.
	 */
	public abstract String getText();

	/**
	 * returns the color of the text.
	 */
	public abstract Color getForeground();

	/**
	 * returns the color of the backGround.
	 */
	public abstract Color getBackground();
	
	/**
	 * Override-able method to add custom graphics to the square.
	 * Graphics will be added relative to the top left hand corner of the square.
	 * @param g
	 */
	public void drawMore(Graphics g){
		//drawReference(g);
	}
	
	/**
	 * Draws an arrow to this squares reference, if it exists
	 * Graphics will be added relative to the top left hand corner of the square.
	 * @param g
	 */
	public final void drawReference(Graphics g){
		//TODO
		g.translate(atX, atY);
		
		int radius=Math.min(tileHeight, tileWidth)/4;
		
		g.setColor(borderColor);
		if(square instanceof AbstractContainer){
			AbstractContainer fill=(AbstractContainer)this.square;
			ISource from=fill.getFillFrom();
			if(from instanceof AbstractContainer){
				AbstractContainer ref=(AbstractContainer)from;
				int x1,x2,y1,y2;
				
				x1=tileWidth/2;
				y1=tileHeight/2;
				
				x2=x1+tileWidth*(ref.getColumn()-square.getColumn());
				y2=y1+tileHeight*(ref.getRow()-square.getRow());
				
				//GOOD
				float ang=(float)Math.toDegrees((float)Math.atan2(y1-y2, x2-x1));
				
				int[] p1 = getPoint(x1,y1,radius,ang);
				int[] p2 = getPoint(x2,y2,-radius,ang);
				
				drawArrow(g,p2[0],p2[1],p1[0],p1[1]);
			}
			//If the reference is not on the board(i.e, it is random), draw an oval
			else{
				int offset=(Math.min(tileWidth, tileHeight)/5);
				g.drawOval(offset,offset,tileWidth-(offset*2),tileHeight-(offset*2));
			}
		}
		g.translate(-atX, -atY);
	}
	
	/**
	 * Draw arrow in level builder indicating gravity.
	 * @param g
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public final void drawArrow(Graphics g, int x1 ,int y1, int x2, int y2){
		int arrowLen=Math.min(tileHeight,tileWidth)/4;
		float arrowAng=45;
		
		float ang=(float)Math.toDegrees((float)Math.atan2(y1-y2, x2-x1));
		g.drawLine(x1, y1, x2, y2);
		
		int[] p1=getPoint(x2,y2,-arrowLen,ang-arrowAng);
		int[] p2=getPoint(x2,y2,-arrowLen,ang+arrowAng);
		
		g.drawLine(x2,y2,p1[0],p1[1]);
		g.drawLine(x2, y2, p2[0], p2[1]);
	}
	
	/**
	 * Get the point calculated by the given values.
	 * @param x
	 * @param y
	 * @param dist
	 * @param ang
	 * @return
	 */
	public final int[] getPoint(int x, int y, int dist, float ang){
		int[] out=new int[2];
		
		out[0]=(int)(x+dist*Math.cos(Math.toRadians(ang)));
		out[1]=(int)(y-dist*Math.sin(Math.toRadians(ang)));
		
		return out;
	}

	/**
	 * Get the tile height.
	 * @return int.
	 */
	public final int getTileHeight() {
		return tileHeight;
	}

	/**
	 * Get the tile width.
	 * @return int.
	 */
	public final int getTileWidth() {
		return tileWidth;
	}

	/**
	 * Set the tile width and height.
	 * @param tileWidth
	 * @param tileHeight
	 */
	public final void setTileSize(int tileWidth, int tileHeight){
		this.tileWidth=tileWidth;
		this.tileHeight=tileHeight;
	}
	
	/**
	 * Set the tile position.
	 * @param atX
	 * @param atY
	 */
	public final void setTilePos(int atX,int atY){
		//Tiles do not overlap at all:
		//this.atX=atX+square.getColumn();
		//this.atY=atY+square.getRow();
		
		//Borders overlap
		this.atX=atX+square.getColumn()*(1-borderThickness);
		this.atY=atY+square.getRow()*(1-borderThickness);
	}
	
	/**
	 * Set the bounds for the square.
	 * @param width
	 * @param height
	 */
	public final void setBounds(int width, int height){
		setTileSize(width,height);
		setTilePos((width)*square.getColumn(),(height)*square.getRow());
	}
	
	/**
	 * Set the bounds for the square.
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public final void setBounds(int x,int y, int width, int height){
		setTilePos(x,y);
		setTileSize(width,height);
	}
	
	/**
	 * Set the bounds for the square.
	 * @param n
	 * @param boardWidth
	 * @param boardHeight
	 */
	public final void setBounds(int n, int boardWidth, int boardHeight){
		int nW=Math.max(0,Math.min(boardWidth%n,square.getColumn()));
		int nH=Math.max(0,Math.min(boardHeight%n,square.getRow()));
		
		this.atX=boardWidth*square.getColumn()/n+nW;
		this.atY=boardHeight*square.getRow()/n+nH;
		
		if(nW+1>0)
			this.tileWidth=boardWidth/n+1;
		else
			this.tileWidth=boardWidth/n;
		
		if(nH+1>0)
			this.tileHeight=boardHeight/n+1;
		else
			this.tileHeight=boardHeight/n;
	}
	
	/**
	 * Draws the square using the parameters defined by the 'get' methods in this class.
	 * @param g
	 */
	public final void drawOn(Graphics g) {
		//Update from model before printing.
		//MAY cause lag? May not be necessary
		updateFields();
		
		g.translate(atX, atY);
		
		//DRAW THE SQUAREEEEEEEEEE
		//drawBackground
		g.setColor(background);
		g.fillRect(0, 0, tileWidth, tileHeight);
		
		//Highlight JUST the background
		if(square.isSelected()){
			g.setColor(selectedColor);
			g.fillRect(0, 0, tileWidth, tileHeight);
		}
		
		//drawBorders
		int borderGap=borderThickness;
		if(numBorders>0)
			borderGap+=(Math.min(tileHeight,tileWidth)/(10*numBorders));
		
		g.setColor(borderColor);
		for(int n=0;n<numBorders;n++){
			for(int i=0;i<borderThickness;i++){
				int offset=(n*(borderThickness+borderGap)+i);
				g.drawRect(offset,offset,tileWidth-(offset*2),tileHeight-(offset*2));
			}
		}
		
		int w,h;
		//draw text
		g.setColor(foreground);
		g.setFont(new Font("Tahoma", Font.PLAIN, Math.min(tileHeight, tileWidth)/2));
		w=g.getFontMetrics().stringWidth(text);
		h=g.getFontMetrics().getAscent()-g.getFontMetrics().getDescent();
		g.drawString(text, (tileWidth-w)/2, (tileHeight+h)/2);
		
		int subTextGap=3;
		g.setFont(new Font("Tahoma", Font.PLAIN, Math.min(tileHeight, tileWidth)/4));
		w=g.getFontMetrics().stringWidth(subText);
		h=g.getFontMetrics().getAscent()-g.getFontMetrics().getDescent();
		int offset=((numBorders-1)*(borderThickness+borderGap)+borderThickness+subTextGap);
		g.drawString(subText, tileWidth-w-offset, tileHeight-offset);
		
//		JLabel l=new JLabel(text);
//		l.setBounds(0,0,tileWidth,tileHeight);
//		l.setFont(new Font("Tahoma", Font.PLAIN, 20));
//		l.setHorizontalAlignment(SwingConstants.CENTER);
//		//l.paintComponent(g);
//		l.paintAll(g);
		
		drawMore(g);
		
		g.translate(-atX, -atY);
	}
}
