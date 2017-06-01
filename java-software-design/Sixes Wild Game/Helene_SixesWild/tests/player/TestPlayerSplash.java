package player;

import java.awt.EventQueue;

import player.controllers.PlayerAppReference;
import junit.framework.TestCase;

public class TestPlayerSplash extends TestCase {
	
	@Override
	protected void setUp() throws Exception {
		PlayerAppReference.clear();
	}

	@Override
	protected void tearDown() throws Exception {
		PlayerAppReference.clear();
	}

	public void testSimple()
	{
		SplashPlayer sb = new SplashPlayer(5000);
		assertEquals(sb.isVisible(), false);
		
		SplashPlayer.main(null);
		PlayerApplication.main(null);
	
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					PlayerApplication window = new PlayerApplication();
				
					assertEquals(window.getFrame().isVisible(), false);
					window.getFrame().setVisible(true);
					assertEquals(window.getFrame().isVisible(), true);
				
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
}
