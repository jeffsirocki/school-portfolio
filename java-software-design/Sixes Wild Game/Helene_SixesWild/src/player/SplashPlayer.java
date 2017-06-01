package player;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

/**
 * Class to launch LevelBuilder Application.
 * @author tjbennett
 * @author jasirocki - javadoc
 *
 */
public class SplashPlayer extends JWindow {

	/** Serializable id. */
	private static final long serialVersionUID = -6413859377639853648L;
	
	/** Duration of splash. */
	private int duration = 0;
	
	/** Constructor with duration. */
	public SplashPlayer(int duration) {
		this.duration = duration;
		
		JPanel jpanel = (JPanel) getContentPane();
		ImageIcon img = new ImageIcon(getClass().getResource("/resource/splash.gif"));
		
		jpanel.add(new JLabel(img), BorderLayout.CENTER);
		setSize(img.getIconWidth(), img.getIconHeight());
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		setVisible(true);
		
		try{
			Thread.sleep(duration);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		setVisible(false);
	}
	
	/** Run main for player. */
	public static void main(String[] args) {
		new SplashPlayer(4000);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlayerApplication window = new PlayerApplication();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
