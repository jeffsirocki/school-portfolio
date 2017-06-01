package player;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import player.controllers.PlayerAppReference;
import player.view.GameView;
import player.view.StartScreenView;
import player.view.TypeSelectView;
import common.model.LevelList;
import common.model.SerializationUtil;
import common.view.IUpdateFields;

/**
 * Level Builder Application.
 * @author tjbennett
 */
public class PlayerApplication implements IUpdateFields{

	/** Jframe for app. */
	private JFrame frame;

	/** Layered pane for different views. */
	JLayeredPane layeredPane;
	
	/** Start screen. */
	StartScreenView startView;
	
	/** Type select view. */
	TypeSelectView selectView;
	
	/** Game view. */
	GameView gameView;
	
	/** List of all levels. */
	LevelList allLevels;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlayerApplication window = new PlayerApplication();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PlayerApplication() {
		//AbstractPlayerController.setApp(this);
		PlayerAppReference.set(this);
		allLevels=new LevelList();
		String fileName = "levelData.bin";
		
		try {
			allLevels =  (LevelList) SerializationUtil.deserialize(fileName);
		} catch (ClassNotFoundException | IOException e) {
			System.err.println("Player could not load file: "+fileName);
			//e.printStackTrace();
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 400);
		frame.setMinimumSize(new Dimension(450, 400));
		frame.setTitle("Helene-SixesWILD Player");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter(){

			@Override
			public void windowClosing(WindowEvent e) {
				String fileName = "levelData.bin";
				int i=JOptionPane.showConfirmDialog(frame,"Do You Wish to Save Progress \n"
						+ "Made in this Session?");
				if(i==JOptionPane.YES_OPTION){
					try {
						//System.out.println(BuilderViewReference.get().getModel().getAllLevels());
						SerializationUtil.serialize(PlayerAppReference.get().getAllLevels(), fileName);
					} 	catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if(i!=JOptionPane.CANCEL_OPTION){
					frame.dispose();
					System.exit(0);
				}
			}
		});
		
		layeredPane = new JLayeredPane();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(layeredPane, GroupLayout.DEFAULT_SIZE, 727, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(layeredPane, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
		);
		
		startView = new StartScreenView(this);
		
		selectView = new TypeSelectView();
		selectView.initialize(allLevels);
		selectView.setVisible(false);
		
		gameView = new GameView();
		gameView.setVisible(false);
		
		
		GroupLayout gl_layeredPane = new GroupLayout(layeredPane);
		gl_layeredPane.setHorizontalGroup(
			gl_layeredPane.createParallelGroup(Alignment.LEADING)
				.addComponent(selectView, GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
				.addComponent(startView, GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
				.addComponent(gameView, GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
		);
		gl_layeredPane.setVerticalGroup(
			gl_layeredPane.createParallelGroup(Alignment.LEADING)
				.addComponent(selectView, GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
				.addComponent(startView, GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
				.addComponent(gameView, GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
		);
		layeredPane.setLayout(gl_layeredPane);
		
		frame.getContentPane().setLayout(groupLayout);
	}

	/**
	 * Get the frame.
	 * @return
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Get the layered pane.
	 * @return
	 */
	public JLayeredPane getLayeredPane() {
		return layeredPane;
	}

	/**
	 * Get start view.
	 * @return
	 */
	public StartScreenView getStartView() {
		return startView;
	}

	/**
	 * Get the type view.
	 * @return
	 */
	public TypeSelectView getSelectView() {
		return selectView;
	}

	/**
	 * Get the game view.
	 * @return
	 */
	public GameView getGameView() {
		return gameView;
	}

	/**
	 * Get the list of all levels.
	 * @return
	 */
	public LevelList getAllLevels() {
		return allLevels;
	}

	/**
	 * Update all fields for the Application.
	 */
	@Override
	public void updateFields() {
		selectView.updateFields();
		gameView.updateFields();
	}
}
