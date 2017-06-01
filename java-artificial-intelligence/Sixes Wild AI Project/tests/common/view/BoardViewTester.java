package common.view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import common.model.AbstractContainer;
import common.model.Board;
import common.model.FrequencyList;
import common.model.RandomSource;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoardViewTester {

	private JFrame frame;
	protected BoardView panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BoardViewTester window = new BoardViewTester();
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
	public BoardViewTester() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Board board=new Board(5);
		
		FrequencyList spawn=new FrequencyList(new int[] {1,2,3,4,5,6});
		spawn.setFreq(2,20);
		spawn.setFreq(3,20);
		spawn.setFreq(4,15);
		spawn.setFreq(5,15);
		spawn.setFreq(6,10);
		
		FrequencyList bonus=new FrequencyList(new int[] {1,2,3});
		bonus.setFreq(2,10);
		bonus.setFreq(3, 5);
		
		for(int r=0;r<board.getSize();r++){
			for(int c=0;c<board.getSize();c++){
				if(r==0){
					AbstractContainer square=(AbstractContainer)board.getSquare(r,c);
					square.setFillFrom(new RandomSource(spawn,bonus));
				}
				else{
					AbstractContainer square=(AbstractContainer)board.getSquare(r,c);
					square.setFillFrom(board.getSquare(r-1,c));
				}
			}
		}
		board.update();
		System.out.println(board);
		
		frame = new JFrame();
		frame.setBounds(100, 100, 730, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new BoardView(board);
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				System.out.println(BoardViewTester.this.panel.squareAt(arg0.getX(),arg0.getY()));
			}
		});
		frame.getContentPane().add(panel);
		panel.initialize();
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
		);
		frame.getContentPane().setLayout(groupLayout);
	}

}
