package common.model;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestRefMap {

	
	ReferenceMap ref;
	
	@Before
	public void setUp() throws Exception {
		ref=new ReferenceMap();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testRefMap() {
		ref.initialize(4);
		assertEquals(ref.map.size(),16);
		
		ref.setDefaultFill(ReferenceMap.FROM_UP);
		assertEquals(ref.getRef(new Point(3,3)),new Point(2,3));
		
		assertArrayEquals(ref.getDefaultFill(),new int[] {-1,0});
		
		ref.setDefaultFill(ReferenceMap.FROM_DOWN);
		assertArrayEquals(ref.getDefaultFill(),new int[] {1,0});
		assertEquals(ref.getRef(new Point(3,3)),new Point(2,3));
		
		ref.initialize(4);
		assertEquals(ref.getRef(new Point(2,3)),new Point(3,3));
		
		ref.setRef(new Point(2,3),new Point(2,2));
		assertEquals(ref.getRef(new Point(2,3)),new Point(2,2));
		
		ReferenceMap copy=ref.makeCopy();
		assertFalse(ref.equals(copy));
		for(Point p:ref){
			assertEquals(ref.getRef(p),copy.getRef(p));
		}
	}

	@Test
	public void testRefMapSize() {
		ref.initialize(4);
		assertEquals(ref.map.size(),16);
		
		ref.setSize(7);
		assertEquals(ref.map.size(),49);
		
		ref.setSize(5);
		assertEquals(ref.map.size(),49);
	}

	@Test
	public void testPointKeys() {
		Point p=new Point(1,2);
		Point q=new Point(3,5);
		ref.setRef(p, q);
		ref.setRef(q,new Point(7,1));
		
		assertTrue(ref.map.containsKey(new Point(1,2)));
		assertTrue(ref.map.contains(new Point(3,5)));
		assertEquals(ref.getRef(ref.getRef(p)),new Point(7,1));
	}

}
