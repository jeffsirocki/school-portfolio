package builder;

import static org.junit.Assert.*;

import java.awt.EventQueue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import builder.controllers.BuilderViewReference;
import builder.view.BuilderView;

public class TestBuilderSplash {

	SplashBuilder sb;
	BuilderApplication window;
	BuilderView bv;
	
	@Before
	public void setUp() throws Exception {
		BuilderViewReference.clear();
	}
	
	@After
	public void tearDown() throws Exception {
		BuilderViewReference.clear();
	}
	
	@Test
	public void test() {
		
		sb = new SplashBuilder(5000);
		assertEquals(sb.isVisible(), false);
		
		SplashBuilder.main(null);
		BuilderApplication.main(null);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new BuilderApplication();
					
					assertEquals(window.getFrame().isVisible(), false);
					window.getFrame().setVisible(true);
					assertEquals(window.getFrame().isVisible(), true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
