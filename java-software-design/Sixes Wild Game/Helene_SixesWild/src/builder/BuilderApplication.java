package builder;


import gameModes.elimination.EliminationLevel;
import gameModes.lightning.LightningLevel;
import gameModes.puzzle.PuzzleLevel;
import gameModes.release.ReleaseLevel;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;

import common.model.AbstractLevel;
import common.model.SerializationUtil;
import builder.controllers.BuilderViewReference;
import builder.model.BuilderModel;
import builder.view.BuilderView;

/**
 * Level Builder Application.
 * @author jasirocki
 */
public class BuilderApplication {

	/**
	 * Each application has a frame.
	 */
	private JFrame frame;
	
	/**
	 * Each application has a builder view.
	 */
	BuilderView builderView;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuilderApplication window = new BuilderApplication();
					
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Constructor for the application.
	 */
	public BuilderApplication() {
		//TODO Replace with Loading from disk
		BuilderViewReference.set(new BuilderView(new BuilderModel()));
		
		//Manually add Level types which may not have been added yet
		BuilderViewReference.get().addLevelType(PuzzleLevel.class);
		BuilderViewReference.get().addLevelType(EliminationLevel.class);
		BuilderViewReference.get().addLevelType(ReleaseLevel.class);
		BuilderViewReference.get().addLevelType(LightningLevel.class);
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//If no BuilderViewReference has been set, this will make a new one
		BuilderViewReference.set(new BuilderView(new BuilderModel()));
		
		frame = new JFrame();
		frame.setBounds(100, 100, 665, 524);
		frame.setTitle("Helene-SixesWILD Level Builder");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter(){

			@Override
			public void windowClosing(WindowEvent e) {
				String fileName = "levelData.bin";
				int i=JOptionPane.showConfirmDialog(frame,"Do You Wish to Save Changes \n"
						+ "Made in this Session?");
				if(i==JOptionPane.YES_OPTION){
					try {
						System.out.println(BuilderViewReference.get().getModel().getAllLevels());
						SerializationUtil.serialize(BuilderViewReference.get().getModel().getAllLevels(), fileName);
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
		
		builderView = BuilderViewReference.get();
		
		/*
		 * Automatically add all existing level types to the builder
		 */
		for(Class<? extends AbstractLevel> c:builderView.getModel().getAllLevels().getTypes()){
			builderView.addLevelType(c);
		}

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(builderView, GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(builderView, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
		);
		frame.getContentPane().setLayout(groupLayout);
	}

	/**
	 * Get JFrame.
	 * @return JFrame
	 */
	public JFrame getFrame() {
		return frame;
	}
}
