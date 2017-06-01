package builder.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import builder.actions.ChangeBuilderLevelAction;
import builder.controllers.AddNewLevelController;
import builder.controllers.BoardSizeChangeController;
import builder.controllers.BuilderViewReference;
import builder.controllers.DeleteLevelController;
import builder.controllers.ResetBoardController;
import builder.controllers.TypeSelectionController;
import builder.model.BuilderModel;

import common.model.AbstractLevel;
import common.model.AbstractSquare;
import common.view.BoardView;
import common.view.IUpdateFields;

/**
 * BuilderView object that holds LevelBuilder application.
 * @author njpanzarino
 * @author jasirocki
 * @author jmmckinney
 */
public class BuilderView extends JPanel implements IUpdateFields{
	
	/**
	 * Serializable ID.
	 */
	private static final long serialVersionUID = -8303954416976988023L;

	/**
	 * Level Type list.
	 */
	ArrayList<Class<? extends AbstractLevel>> levelTypes=new ArrayList<Class<? extends AbstractLevel>>();
	
	/**
	 * Level types in string.
	 */
	String[] levelStringTypes;
	
	/**
	 * Square types.
	 */
	ArrayList<Class<? extends AbstractSquare>> squareTypes=new ArrayList<Class<? extends AbstractSquare>>();
	
	/** 
	 * Square types in string.
	 */
	String[] squareStringTypes;
	
	/**
	 * BoardView object.
	 */
	BoardView boardView;
	
	/**
	 * Builder model that holds entities.
	 */
	BuilderModel model;
	
	/**
	 * Parameter View.
	 */
	LevelParameterView paramView;
	
	/**
	 * LevelType combo box.
	 */
	JComboBox<String> levelTypeCombo;
	
	/**
	 * Level Number combo box.
	 */
	JComboBox<Integer> levelNumCombo;
	
	/**
	 * Size slider (Sizes: 2-9).
	 */
	JSlider sizeSlider;
	
	/**
	 * Square type combo box.
	 */
	JComboBox<String> squareTypeCombo;
	
	/**
	 * Store the controller attached to the boardView so that it can be enabled/disabled.
	 */
	TypeSelectionController typeController;
	
	/**
	 * Determines if controllers should listen to events.
	 */
	public boolean listen=true;
	
	/**
	 * Create the panel.
	 */
	public BuilderView(BuilderModel model) {
		this.model=model;
		
		setBackground(Color.GRAY);
		
		//If you click on the boardView, change they type of the square to the type selected
		boardView = new BoardView(null);
		typeController=new TypeSelectionController(boardView);

//		boardView.addMouseListener(new BoardSelectionController(){
//			public void doAction(Selection selection){
//				for(AbstractSquare s:selection){
//					boardView.changeType(s,selectedSquareClass());
//				}
//				getModel().getCurrentLevel().getParams().updateRef();
//				boardView.updateBoard();
//				updateFields();
//			}
//		});
//		boardView.addMouseMotionListener(new BoardSelectionController());
		
		//boardView.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		boardView.setBackground(this.getBackground());
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 153, 255));
		panel.setBorder(null);
		
		JLabel levelTypeLabel = new JLabel("type:");
		levelTypeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		levelTypeLabel.setFont(new Font("Righteous", Font.PLAIN, 13));
		
		JLabel levelNumLabel = new JLabel("level #:");
		levelNumLabel.setHorizontalAlignment(SwingConstants.CENTER);
		levelNumLabel.setFont(new Font("Righteous", Font.PLAIN, 13));
		
		JLabel sizeLabel = new JLabel("size:");
		sizeLabel.setFont(new Font("Righteous", Font.PLAIN, 13));
		sizeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		BoardSizeChangeController sizeChangeController = new BoardSizeChangeController(this);
		
		sizeSlider = new JSlider();
		sizeSlider.addMouseListener(sizeChangeController);

		sizeSlider.setSnapToTicks(true);
		sizeSlider.setBackground(new Color(51, 153, 255));
		sizeSlider.setValue(9);
		sizeSlider.setMinimum(2);
		sizeSlider.setMaximum(9);
		
		sizeSlider.addChangeListener(sizeChangeController);
		
		//If Level number selection is modified, Load the new level
		levelNumCombo = new JComboBox<Integer>();
		levelNumCombo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(BuilderView.this.listen){
					AbstractLevel level=BuilderViewReference.get().getModel().getAllLevels().getLevel(selectedLevelClass(),selectedLevelNumber());
					ChangeBuilderLevelAction act=new ChangeBuilderLevelAction(level);
					BuilderViewReference.get().getModel().doAction(act);
					
					loadFields();
				}
			}
		});
		//levelNumCombo.setModel(new DefaultComboBoxModel<Integer>(new Integer[] {1}));
		levelNumCombo.setFont(new Font("Righteous", Font.PLAIN, 13));
		
		//If the level type selection is modified, update the available level numbers and load the new level
		levelTypeCombo = new JComboBox<String>();
		levelTypeCombo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(BuilderView.this.listen){
					AbstractLevel level=BuilderViewReference.get().getModel().getAllLevels().getLevel(selectedLevelClass(),selectedLevelNumber());
					ChangeBuilderLevelAction act=new ChangeBuilderLevelAction(level);
					BuilderViewReference.get().getModel().doAction(act);
					
					loadLevelFields();
					loadFields();
				}
			}
		});
		levelTypeCombo.setFont(new Font("Righteous", Font.PLAIN, 13));
		//levelTypeCombo.setModel(new DefaultComboBoxModel<String>(levelTypes.toArray(new String[levelTypes.size()])));
		
		squareTypeCombo = new JComboBox<String>();
		squareTypeCombo.setFont(new Font("Righteous", Font.PLAIN, 14));
		//selectionModeCombo.setModel(new DefaultComboBoxModel<String>(new String[] {"selection mode", "delete mode", "sixes mode"}));
		
		JButton undoButton = new JButton("undo");
		undoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuilderViewReference.get().getModel().undoAction();
				BuilderViewReference.get().updateFields();
			}
		});
		undoButton.setFont(new Font("Righteous", Font.PLAIN, 14));
		
		JButton redoButton = new JButton("redo");
		redoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuilderViewReference.get().getModel().redoAction();
				BuilderViewReference.get().updateFields();
			}
			
		});
		redoButton.setFont(new Font("Righteous", Font.PLAIN, 14));
		
		//Add ResetBoardController to the Reset Button
		JButton resetButton = new JButton("reset");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		resetButton.addMouseListener(new ResetBoardController());
		resetButton.setFont(new Font("Righteous", Font.PLAIN, 14));
		
		JButton deleteButton = new JButton("delete level");
		deleteButton.addMouseListener(new DeleteLevelController());
		deleteButton.setFont(new Font("Righteous", Font.PLAIN, 14));
		
		//If the preview button is pressed, re-initialize the board with the selected parameters
		JButton previousButton = new JButton("preview");
		previousButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				BuilderViewReference.get().resetBoard();
			}
		});
		previousButton.setFont(new Font("Righteous", Font.PLAIN, 14));
		
		JButton addNewLevelButton = new JButton("add new level");
		addNewLevelButton.addMouseListener(new AddNewLevelController());
			
		addNewLevelButton.setFont(new Font("Righteous", Font.PLAIN, 14));
		
		paramView = new LevelParameterView();
		paramView.setBackground(new Color(51, 153, 255));
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(levelTypeLabel, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
						.addComponent(levelNumLabel, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
						.addComponent(sizeLabel, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(sizeSlider, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
							.addGap(32)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(levelNumCombo, 0, 129, Short.MAX_VALUE)
								.addComponent(levelTypeCombo, 0, 133, Short.MAX_VALUE))))
					.addGap(5))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(levelTypeLabel, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
								.addComponent(levelTypeCombo, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
							.addGap(5)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(levelNumLabel, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
								.addComponent(levelNumCombo, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
							.addGap(12)
							.addComponent(sizeLabel, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(47)
							.addComponent(sizeSlider, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.SOUTH, addNewLevelButton, 49, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, resetButton, 49, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, deleteButton, 49, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, undoButton, 6, SpringLayout.SOUTH, addNewLevelButton);
		springLayout.putConstraint(SpringLayout.NORTH, redoButton, 6, SpringLayout.SOUTH, deleteButton);
		springLayout.putConstraint(SpringLayout.WEST, redoButton, 0, SpringLayout.WEST, deleteButton);
		springLayout.putConstraint(SpringLayout.EAST, redoButton, 0, SpringLayout.EAST, deleteButton);
		springLayout.putConstraint(SpringLayout.EAST, undoButton, 0, SpringLayout.EAST, addNewLevelButton);
		springLayout.putConstraint(SpringLayout.WEST, resetButton, 6, SpringLayout.EAST, deleteButton);
		springLayout.putConstraint(SpringLayout.WEST, deleteButton, 6, SpringLayout.EAST, addNewLevelButton);
		springLayout.putConstraint(SpringLayout.WEST, undoButton, 0, SpringLayout.WEST, addNewLevelButton);
		springLayout.putConstraint(SpringLayout.WEST, addNewLevelButton, 12, SpringLayout.EAST, panel);
		springLayout.putConstraint(SpringLayout.NORTH, previousButton, 4, SpringLayout.SOUTH, resetButton);
		springLayout.putConstraint(SpringLayout.WEST, previousButton, 6, SpringLayout.EAST, redoButton);
		springLayout.putConstraint(SpringLayout.SOUTH, previousButton, 0, SpringLayout.SOUTH, panel);
		springLayout.putConstraint(SpringLayout.EAST, previousButton, 0, SpringLayout.EAST, resetButton);
		springLayout.putConstraint(SpringLayout.SOUTH, paramView, -44, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, squareTypeCombo, 7, SpringLayout.SOUTH, paramView);
		springLayout.putConstraint(SpringLayout.SOUTH, squareTypeCombo, -12, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, paramView, 12, SpringLayout.SOUTH, panel);
		springLayout.putConstraint(SpringLayout.NORTH, boardView, 0, SpringLayout.NORTH, paramView);
		springLayout.putConstraint(SpringLayout.WEST, boardView, 251, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, boardView, -12, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, boardView, -12, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.NORTH, resetButton, 4, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, resetButton, 645, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, deleteButton, 4, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, deleteButton, 521, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, addNewLevelButton, 4, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, addNewLevelButton, 384, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, redoButton, 98, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, undoButton, 98, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, panel, 4, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, panel, 12, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, panel, 98, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, panel, 242, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, paramView, 12, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, paramView, 242, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, squareTypeCombo, 40, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, squareTypeCombo, 225, SpringLayout.WEST, this);
		setLayout(springLayout);
		add(squareTypeCombo);
		add(paramView);
		add(panel);
		//add(saveLevelButton);
		add(previousButton);
		add(undoButton);
		add(redoButton);
		add(addNewLevelButton);
		add(deleteButton);
		add(resetButton);
		add(boardView);
		
		updateLevelTypes();
		updateLevelNums();
		loadFields();
	}
	
	/**
	 * Sync all buttons/boxes/sliders with the currentLevel parameters.
	 */
	public void updateFields(){
		
		//To make board re-display correctly, make invisible then make re-visible.. weird
		//TODO ask why this is necessary and if it can be fixed?
		boardView.setVisible(false);
		if(model.getCurrentLevel()!=null){
			boardView.setBoard(model.getCurrentLevel().getParams().getBoard());
			
			listen=false;
			sizeSlider.setValue(model.getCurrentLevel().getParams().getBoard().getSize());
			listen=true;
			
			boardView.setVisible(true);
		}
		

		
		paramView.updateFields();
	}
	
	/**
	 * Updates the "currentLevel" model from disk based on selected levelType and number.
	 * Updates all fields.
	 */
	public void loadFields(){
		
		if(levelNumCombo.getItemCount()<1)
			return;
		
		//TODO make currentLevel be a COPY of the selected level, not a direct reference
		//TODO save previous state of "currentLevel" before overriding so that it can undo
		//Save button will overwrite it
		AbstractLevel level=model.getAllLevels().getLevel(selectedLevelClass(),selectedLevelNumber());
		
		setCurrentLevel(level);
		
//		if(level!=null){
//			model.setCurrentLevel(level);//.makeCopy());
//			sizeSlider.setValue(level.getParams().getBoard().getSize());
//		}
//		else
//			model.setCurrentLevel(null);
		
		//Update the Board view
//		try{
//			boardView.setBoard(model.getCurrentLevel().getParams().getBoard());
//			boardView.redraw();
//		}
//		catch(Exception e){
//			//System.err.println("No board found: disabling preview");
//			boardView.setVisible(false);
//		}
		
		
		updateFields();
	}
	
	/**
	 * Re-Loads all fields which can vary by level type.
	 */
	public void loadLevelFields(){
		updateLevelNums();
		updateParameterPanel();
		updateSquareTypes();
	}

	/**
	 * Clears and Re-Initializes the board in a new state using the given level parameters.
	 */
	public void resetBoard(){
		try{
			model.getCurrentLevel().getParams().updateRef();
			model.getCurrentLevel().getParams().getBoard().removeAllTiles();
			model.getCurrentLevel().getParams().getBoard().initialize();
			updateFields();
		}catch(Exception e){
			//System.err.println("reset board failed");
		}
	}
	
	/**
	 * Update the parameter panel content and then update the fields within the comboBox in LevelParameterView.
	 */
	public void updateParameterPanel() {
		for(Class<? extends AbstractLevel> t:levelTypes){
			try {
				paramView.removeParameterPanel((ParameterPanel)t.getMethod("getAdditionalParameterPanel").invoke(null));
			} catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException
					| SecurityException e) {
				System.err.println("could not get ParameterPanel from class: "+t.getSimpleName());
				//e.printStackTrace();
			}
		}
		
		try {
			paramView.addParameterPanel((ParameterPanel)selectedLevelClass().getMethod("getAdditionalParameterPanel").invoke(null));
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			System.err.println("Could not get ParameterPanel from current class: "+selectedLevelClass().getSimpleName());
			//e.printStackTrace();
		}
		paramView.updateFields();
	}

	/**
	 * Update the available level numbers in the JComboBox to match the selected level type.
	 * Based on the selectedLevelClass and available levels of that type in the LevelList.
	 */
	public void updateLevelNums(){
		
		if(levelTypeCombo.getItemCount()<1)
			return;
		
		ArrayList<AbstractLevel> levels=model.getAllLevels().getType(selectedLevelClass());
		Collections.sort(levels);
		Integer[] n=new Integer[levels.size()+1];
		n[0]=null;
		for(int i=0;i<levels.size();i++){
			n[i+1]=levels.get(i).getLevelID();
		}
		levelNumCombo.setModel(new DefaultComboBoxModel<Integer>(n));
	}
	
	/**
	 * Update the list of allowable squares in the JComboBox to match the selected level type.
	 * Based on the selected Level type and the getSquares() method in that level's class.
	 */
	public void updateSquareTypes(){
		squareTypes=new ArrayList<Class<? extends AbstractSquare>>();
		squareTypes = AbstractLevel.getSquareTypes(selectedLevelClass());
		
		squareStringTypes=new String[squareTypes.size()];
		for(int i=0;i<squareStringTypes.length;i++){
			squareStringTypes[i]=AbstractSquare.type(squareTypes.get(i));
		}
		squareTypeCombo.setModel(new DefaultComboBoxModel<String>(squareStringTypes));
	}
	
	/**
	 * Updates the list of available level types. 
	 * Only called during initialization.
	 */
	public void updateLevelTypes(){
		//Update the Level Type Strings
		levelStringTypes=new String[levelTypes.size()];
		for(int i=0;i<levelStringTypes.length;i++){
			//levelStringTypes[i]=AbstractLevel.getType(levelTypes.get(i));
			levelStringTypes[i]=AbstractLevel.type(levelTypes.get(i));
		}
		levelTypeCombo.setModel(new DefaultComboBoxModel<String>(levelStringTypes));
	}

	/**
	 * Adds the selected level type to the list of possible level types that can be built.
	 * This both makes them visible in the JComboBox, and gives the BuilderView all it needs to construct them.
	 * @param c
	 */
	public void addLevelType(Class<? extends AbstractLevel> c){
		if(!levelTypes.contains(c)){
				levelTypes.add(c);
		}
		updateLevelTypes();
		loadLevelFields();
		loadFields();
	}
	
	/**
	 * Determines the Level class corresponding to the String selected in the levelTypeCombo JComboBox.
	 * @return
	 */
	public Class<? extends AbstractLevel> selectedLevelClass(){
		return levelTypes.get(levelTypeCombo.getSelectedIndex());
	}
	
	/**
	 * Determines the Level number corresponding to the integer selected in levelNumCombo.
	 * @return
	 */
	public Integer selectedLevelNumber(){
		return levelNumCombo.getItemAt(levelNumCombo.getSelectedIndex());
	}
	
	/**
	 * Determines the Square class corresponding to the String selected in the levelTypeCombo JComboBox.
	 * @return
	 */
	public Class<? extends AbstractSquare> selectedSquareClass(){
		return squareTypes.get(squareTypeCombo.getSelectedIndex());
	}
	
	/**
	 * Get Model.
	 * @return BuilderModel
	 */
	public BuilderModel getModel() {
		return model;
	}

	/**
	 * Get BoardView.
	 * @return BoardView
	 */
	public BoardView getBoardView() {
		return boardView;
	}

	/**
	 * Set Current level to one selected.
	 * @param l
	 */
	public void setCurrentLevel(AbstractLevel l) {
		listen=false;
		
		model.setCurrentLevel(l);
		
		if(l==null){
			levelNumCombo.setSelectedItem(null);
			model.setCurrentLevel(null);
			boardView.setVisible(false);
			
			listen=true;
			return;
		}
		
		levelTypeCombo.setSelectedItem(l.getType());
		levelNumCombo.setSelectedItem(l.getLevelID());
		sizeSlider.setValue(l.getParams().getBoard().getSize());
		boardView.setVisible(true);
		
		listen=true;
	}
	
	/**
	 * Get the size slider value.
	 * @return
	 */
	public int getSizeSliderValue()
	{
		return BuilderView.this.sizeSlider.getValue();
	}
}
