package player.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import player.controllers.SelectSixController;
import player.controllers.ToLevelSelectController;
import player.model.AbstractGame;

import common.controllers.BoardSelectionController;
import common.model.Board;
import common.model.ScoreThresholds;
import common.view.BoardView;
import common.view.IUpdateFields;

/**
 * PlayerApplication window for displaying the current game and allowing the user to play it.
 * @author Nicholas Panzarino
 *
 */
public class GameView extends JPanel implements IUpdateFields{

	private static final long serialVersionUID = -4995745960795274170L;
	
	/**
	 * The current game being played.
	 */
	AbstractGame theGame;
	
	/**
	 * The board being displayed by this panel.
	 */
	BoardView boardView;
	
	/**
	 * Label displaying level type and number.
	 */
	JLabel levelLabel;
	
	/**
	 * Button for ending the current game and returning to level Select.
	 */
	JButton exitButton;
	
	/**
	 * Label for displaying the current score of this game.
	 */
	JLabel scoreLabel;
	
	/**
	 * Label for displaying the all-time high score of the current level.
	 */
	JLabel lblHighscore;
	
	/**
	 * Panel displaying and selecting special moves.
	 */
	PlayMovePanel specialMoves;
	
	/**
	 * Panel for showing fields specific to each level type.
	 */
	GameFieldsView fields;
	
	/**
	 * Panels for looking nice (and organizing components).
	 */
	JPanel panel, panel_1;
	
	/**
	 * The default controller for making selections on the board.
	 * This controller will be active when no special moves are selected
	 */
	BoardSelectionController defaultController;
	
	/**Progress Bar for displaying star progress.*/
	private JProgressBar progressBar1;
	/**Progress Bar for displaying star progress.*/
	private JProgressBar progressBar2;
	/**Progress Bar for displaying star progress.*/
	private JProgressBar progressBar3;
	
	/**Label for identifying progress bars.*/
	private JLabel lbl3Star;
	/**Label for identifying progress bars.*/
	private JLabel lbl2Star;
	/**Label for identifying progress bars.*/
	private JLabel lbl1Star;
	
	/**
	 * Create the panel.
	 */
	public GameView(){
		setBackground(Color.GRAY);
		
		//theBoard = new BoardView(PlayerAppReference.get().getAllLevels().getLevel(PuzzleLevel.class,1).getParams().getBoard());
		boardView=new BoardView(null);
		//theBoard.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		boardView.setBackground(getBackground());
		defaultController=new SelectSixController(boardView);
		
		panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		levelLabel = new JLabel("[level type] level [level #]");
		levelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		levelLabel.setForeground(Color.WHITE);
		levelLabel.setFont(new Font("Righteous", Font.PLAIN, 18));
		
		panel_1 = new JPanel();
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		
		exitButton = new JButton("Exit to Menu");
		exitButton.addMouseListener(new ToLevelSelectController());
		
		scoreLabel = new JLabel("score: 00000");
		scoreLabel.setForeground(Color.WHITE);
		scoreLabel.setFont(new Font("Righteous", Font.PLAIN, 13));
		scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		//progressBar.setBorder(new LineBorder(Color.WHITE,2));
		
		//TODO: Replace with "Special Moves" Panel that updates based on allowed moves and level specifications
		specialMoves = new PlayMovePanel();
		specialMoves.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		
		//****************************************************************************
		
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.NORTH, scoreLabel, 12, SpringLayout.SOUTH, panel_1);
		springLayout.putConstraint(SpringLayout.NORTH, panel_1, 12, SpringLayout.SOUTH, panel);
		springLayout.putConstraint(SpringLayout.SOUTH, panel_1, -133, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.WEST, scoreLabel, 0, SpringLayout.WEST, panel);
		springLayout.putConstraint(SpringLayout.EAST, scoreLabel, -12, SpringLayout.WEST, boardView);
		springLayout.putConstraint(SpringLayout.NORTH, boardView, 12, SpringLayout.SOUTH, specialMoves);
		springLayout.putConstraint(SpringLayout.SOUTH, boardView, -12, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.WEST, boardView, 0, SpringLayout.WEST, specialMoves);
		springLayout.putConstraint(SpringLayout.EAST, boardView, -12, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.EAST, specialMoves, -12, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.WEST, panel_1, 0, SpringLayout.WEST, panel);
		springLayout.putConstraint(SpringLayout.EAST, panel_1, 0, SpringLayout.EAST, panel);
		springLayout.putConstraint(SpringLayout.NORTH, specialMoves, 0, SpringLayout.NORTH, panel);
		springLayout.putConstraint(SpringLayout.WEST, specialMoves, 12, SpringLayout.EAST, panel);
		springLayout.putConstraint(SpringLayout.SOUTH, specialMoves, 0, SpringLayout.SOUTH, panel);
		springLayout.putConstraint(SpringLayout.NORTH, panel, 12, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, panel, 12, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, panel, 102, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, panel, 256, SpringLayout.WEST, this);
		setLayout(springLayout);
		add(panel);
		
		lblHighscore = new JLabel("Highscore");
		lblHighscore.setForeground(Color.WHITE);
		lblHighscore.setHorizontalAlignment(SwingConstants.CENTER);
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(levelLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblHighscore, GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(22)
					.addComponent(levelLabel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblHighscore)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		add(panel_1);
		SpringLayout sl_panel_1 = new SpringLayout();
		sl_panel_1.putConstraint(SpringLayout.NORTH, exitButton, -34, SpringLayout.SOUTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, exitButton, 10, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, exitButton, -10, SpringLayout.SOUTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, exitButton, -10, SpringLayout.EAST, panel_1);
		panel_1.setLayout(sl_panel_1);
		panel_1.add(exitButton);
		
		fields = new GameFieldsView();
		fields.setOpaque(false);
		sl_panel_1.putConstraint(SpringLayout.NORTH, fields, 0, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, fields, 0, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, fields, -10, SpringLayout.NORTH, exitButton);
		sl_panel_1.putConstraint(SpringLayout.EAST, fields, 0, SpringLayout.EAST, panel_1);
		panel_1.add(fields);
		add(scoreLabel);
		add(boardView);
		add(specialMoves);
			
		specialMoves.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		progressBar1 = new JProgressBar();
		springLayout.putConstraint(SpringLayout.NORTH, progressBar1, -20, SpringLayout.SOUTH, boardView);
		springLayout.putConstraint(SpringLayout.WEST, progressBar1, 52, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, progressBar1, 0, SpringLayout.SOUTH, boardView);
		springLayout.putConstraint(SpringLayout.EAST, progressBar1, -12, SpringLayout.WEST, boardView);
		progressBar1.setForeground(Color.GREEN);
		progressBar1.setToolTipText("");
		progressBar1.setMinimum(0);
		progressBar1.setValue(0);
		progressBar1.setStringPainted(true);
		
		add(progressBar1);
		
		progressBar2 = new JProgressBar();
		springLayout.putConstraint(SpringLayout.WEST, progressBar2, 40, SpringLayout.WEST, panel);
		springLayout.putConstraint(SpringLayout.SOUTH, progressBar2, -6, SpringLayout.NORTH, progressBar1);
		springLayout.putConstraint(SpringLayout.EAST, progressBar2, 0, SpringLayout.EAST, panel);
		progressBar2.setValue(0);
		progressBar2.setToolTipText("");
		progressBar2.setMinimum(0);
		progressBar2.setForeground(Color.GREEN);
		progressBar2.setStringPainted(true);
		add(progressBar2);
		
		progressBar3 = new JProgressBar();
		springLayout.putConstraint(SpringLayout.NORTH, progressBar2, 6, SpringLayout.SOUTH, progressBar3);
		springLayout.putConstraint(SpringLayout.SOUTH, scoreLabel, -21, SpringLayout.NORTH, progressBar3);
		springLayout.putConstraint(SpringLayout.NORTH, progressBar3, -84, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, progressBar3, -64, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.WEST, progressBar3, 40, SpringLayout.WEST, panel);
		springLayout.putConstraint(SpringLayout.EAST, progressBar3, 0, SpringLayout.EAST, panel);
		progressBar3.setValue(0);
		progressBar3.setToolTipText("");
		progressBar3.setMinimum(0);
		progressBar3.setForeground(Color.GREEN);
		progressBar3.setStringPainted(true);
		add(progressBar3);
		
		lbl3Star = new JLabel("3 Star");
		springLayout.putConstraint(SpringLayout.NORTH, lbl3Star, 21, SpringLayout.SOUTH, scoreLabel);
		springLayout.putConstraint(SpringLayout.WEST, lbl3Star, 0, SpringLayout.WEST, panel);
		add(lbl3Star);
		
		lbl2Star = new JLabel("2 Star");
		springLayout.putConstraint(SpringLayout.NORTH, lbl2Star, 12, SpringLayout.SOUTH, lbl3Star);
		springLayout.putConstraint(SpringLayout.WEST, lbl2Star, 0, SpringLayout.WEST, panel);
		add(lbl2Star);
		
		lbl1Star = new JLabel("1 Star");
		springLayout.putConstraint(SpringLayout.NORTH, lbl1Star, 12, SpringLayout.SOUTH, lbl2Star);
		springLayout.putConstraint(SpringLayout.WEST, lbl1Star, 0, SpringLayout.WEST, panel);
		add(lbl1Star);
	}
	
	/**
	 * Re-initialize the gameView to start playing the given game.
	 * @param game
	 */
	public void initialize(AbstractGame game){
		theGame=game;
		Board b=theGame.getLevel().getParams().getBoard();
		theGame.getLevel().getParams().updateRef(b);
		//b.removeAllTiles();
		b.initialize();
		
		boardView.setBoard(b);
		
		ScoreThresholds thresh = theGame.getLevel().getParams().getThresholds(); 
		
		panel.setBackground(theGame.gameColor());
		panel_1.setBackground(theGame.gameColor());
		specialMoves.setBackground(theGame.gameColor());
		
		progressBar1.setMaximum(thresh.getScoreThresh(1));
		progressBar1.setValue(0);
		progressBar1.setString("" + thresh.getScoreThresh(1));
		
		progressBar2.setMaximum(thresh.getScoreThresh(2));
		progressBar2.setValue(0);
		progressBar2.setString("" + thresh.getScoreThresh(2));
		
		progressBar3.setMaximum(thresh.getScoreThresh(3));
		progressBar3.setValue(0);
		progressBar3.setString("" + thresh.getScoreThresh(3));
		
		levelLabel.setText(theGame.getLevel().toString());
		lblHighscore.setText("Highscore: "+theGame.getLevel().getHighscore());
		
		specialMoves.initialize(theGame.getLevel().getParams().getMoves());
				
		updateFields();
	}

	/**
	 * returns the current game being played by this gameView.
	 */
	public AbstractGame getTheGame() {
		return theGame;
	}

	/**
	 * Returns the current Board being displayed by this gameView.
	 */
	public BoardView getBoardView() {
		return boardView;
	}

	/**
	 * Returns the current SpecialMovesPanel being displayed by this gameView.
	 */
	public PlayMovePanel getSpecialMovesPanel() {
		return specialMoves;
	}

	/**
	 * returns the default controller used for making selections on the board.
	 */
	public BoardSelectionController getDefaultController() {
		return defaultController;
	}

	/**
	 * load states for this and all dependent classes.
	 * Update display to match new states.
	 */
	@Override
	public void updateFields() {
		boardView.updateBoard();
		
		fields.initialize(theGame.getFields());
		
		scoreLabel.setText("SCORE: "+theGame.getScore());
		progressBar1.setValue(theGame.getScore());
		progressBar2.setValue(theGame.getScore());
		progressBar3.setValue(theGame.getScore());
		
		setVisible(false);
		setVisible(true);
		
		if(theGame.isOver()){
			theGame.endGame();
			new ToLevelSelectController().go();
		}
	}
}
