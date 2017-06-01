package common.model;

import java.awt.Point;
import java.io.Serializable;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;

/**
 * Stores information about where Squares in certain board positions should fill from in relation to other board positions.
 * Used to retain information on fillFrom references while Board squares are modified.
 * @author Nicholas Panzarino
 * @author jasirocki - jdoc
 *
 */
public class ReferenceMap implements Iterable<Point>, ICopy, Serializable{
	
	/**
	 * Serializable ID.
	 */
	private static final long serialVersionUID = -8303954416976988023L;
	
	public static final int[] FROM_UP={-1,0};
	public static final int[] FROM_DOWN={1,0};
	public static final int[] FROM_LEFT={0,-1};
	public static final int[] FROM_RIGHT={0,1};
	
	/**
	 * Default location to choose a fill from: [row,column].
	 */
	int[] defaultFill=ReferenceMap.FROM_UP;
	
	/**
	 * If this false, then if a Reference map points to an invalid source, a random Source will be added.
	 * If this is true, then it will follow the fillFrom references until it finds a valid source, or leaves the map.
	 */
	public boolean bridge=false;

	/**
	 * HashTable of points.
	 */
	Hashtable<Point,Point> map=new Hashtable<Point,Point>();
	
	/**
	 * Constructor for ReferenceMap.
	 */
	public ReferenceMap(){
		
	}
	
	/**
	 * Constructor for ReferenceMap.
	 * @param in
	 */
	public ReferenceMap(ReferenceMap in){
		for(Point p:in){
			map.put(new Point(p), new Point(in.getRef(p)));
		}
		System.arraycopy(in.getDefaultFill(), 0, defaultFill, 0, in.getDefaultFill().length);
		setBridge(in.isBridge());
	}
	
	/**
	 * Clears this ReferenceMap and replaces all references with their default references.
	 * @param size
	 */
	public void initialize(int size){
		map=new Hashtable<Point,Point>();
		for(int r=0;r<size;r++){
			for(int c=0;c<size;c++){
				Point p=new Point(r,c);
				map.put(p,getDefaultFillPoint(p));
			}
		}
	}
	
	/**
	 * Changes the size of this reference map. 
	 * Will only ADD to the size, not remove references that have already been added.
	 * Added References will be set to the default.
	 * @param size
	 */
	public void setSize(int size){
		for(int r=0;r<size;r++){
			for(int c=0;c<size;c++){
				Point key=new Point(r,c);
				if(!map.containsKey(key)){
					map.put(key,getDefaultFillPoint(key));
				}
			}
		}
	}
	
	/**
	 * Get default fillfrom sources at point P.
	 * @param p
	 * @return point
	 */
	public Point getDefaultFillPoint(Point p){
		if(defaultFill[0]==0&&defaultFill[1]==0){
			return new Point(-1,-1);
		}
		return new Point((int)(p.getX()+defaultFill[0]),(int)(p.getY()+defaultFill[1]));
	}
	
	/**
	 * get Default Fill int.
	 * @return
	 */
	public int[] getDefaultFill() {
		return defaultFill;
	}

	/**
	 * Set the default fill int.
	 * @param defaultFill
	 */
	public void setDefaultFill(int[] defaultFill) {
		this.defaultFill = defaultFill;
	}

	/**
	 * Get if fill is a bridge.
	 * @return
	 */
	public boolean isBridge() {
		return bridge;
	}

	/**
	 * Set fill as a bridge.
	 * @param bridgeGaps
	 */
	public void setBridge(boolean bridgeGaps) {
		this.bridge = bridgeGaps;
	}

	/**
	 * Get the reference Point and point P.
	 * @param p
	 * @return Point
	 */
	public Point getRef(Point p){
		return map.get(p);
	}
	
	/**
	 * Set the reference Point at point P.
	 * @param fill
	 * @param from
	 * @return Point
	 */
	public Point setRef(Point fill,Point from){
		if(map.containsKey(from)){
			map.remove(fill);
		}
		return map.put(fill, from);
	}
	
	/**
	 * Copy the ReferenceMap without references.
	 */
	public ReferenceMap makeCopy(){
		return new ReferenceMap(this);
	}

	/**
	 * Iterate through the map of reference points.
	 */
	@Override
	public Iterator<Point> iterator() {
		return Collections.list(map.keys()).iterator();
	}
	
	/**
	 * Makes a copy of the object passed.
	 */
	public void copy(ICopy object)
	{
		ReferenceMap in=(ReferenceMap)object;
		map=new Hashtable<Point,Point>();
		for(Point p:in){
			map.put(new Point(p), new Point(in.getRef(p)));
		}
		System.arraycopy(in.getDefaultFill(), 0, defaultFill, 0, in.getDefaultFill().length);
		setBridge(in.isBridge());
	}
}
