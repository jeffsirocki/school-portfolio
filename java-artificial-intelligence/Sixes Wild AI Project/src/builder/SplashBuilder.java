package builder;

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
public class SplashBuilder extends JWindow {

	/**
	 * Each has an ID.
	 */
	private static final long serialVersionUID = -6413859377639853648L;
	
	/**
	 * Each splash has a duration.
	 */
	private int duration = 0;
	
	/**
	 * Constructor for Splash screen.
	 * @param duration
	 */
	public SplashBuilder(int duration) {
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
	
	/**
	 * Launch LevelBuilder Application.
	 * @param args
	 */
	public static void main(String[] args) {
		new SplashBuilder(4000);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuilderApplication window = new BuilderApplication();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
