import java.awt.Point;

import junit.framework.TestCase;


public class MapTest extends TestCase {
	public void testMap() {
        Map map = new Map("test1.txt");
        map.printMap();
        assertEquals(map.getStart(), new Point(2,2));
        assertEquals(map.getGoal(), new Point(1,0));
        assertEquals((int)map.getComplexity(new Point(3, 0)), 6);
    }
}
