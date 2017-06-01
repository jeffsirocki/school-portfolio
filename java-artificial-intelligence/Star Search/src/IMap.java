import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface IMap {
	
	//Returns a copy of this IMap, without a reference to the original
	IMap copy();
	
	ArrayList<String[]> getMatrix();
	
	void initialize(String filename) throws FileNotFoundException;
	
	Integer getComplexity(Point point);
	void setComplexity(Point point, Integer complexity);
	
	Point getStart();
	boolean isGoal(Point point);
	boolean isCliff(Point point);
	
}
