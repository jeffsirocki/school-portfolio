package common.model;

import org.junit.Test;

import junit.framework.TestCase;

public class TestThresholds extends TestCase {

	ScoreThresholds thresholds1, thresholds2, copy;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		thresholds1 = new ScoreThresholds(10, 20, 30);
		thresholds2 = new ScoreThresholds();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	@Test
	public void test() {
		assertEquals(thresholds1.getNumStars(25), 2);
		assertEquals(thresholds2.getScoreThresh(1), 1);
		copy = thresholds1.makeCopy();
		
		thresholds1.setThreshold(0, 0);
		thresholds1.setThreshold(1, 20);
		thresholds1.setThreshold(2, 100);
		thresholds1.setThreshold(3, 1000);
		
		thresholds1.copy(thresholds1);
		
		
		assertEquals(thresholds1.getNumStars(25), 1);
		assertEquals(thresholds1.getScoreThresh(1), 20);
	}

}
