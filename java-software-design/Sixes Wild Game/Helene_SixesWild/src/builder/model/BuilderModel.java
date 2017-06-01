package builder.model;

import java.io.IOException;
import java.util.Stack;

import builder.actions.IAction;
import builder.controllers.BuilderViewReference;
import common.model.AbstractLevel;
import common.model.LevelList;
import common.model.SerializationUtil;

/**
 * The Model stored by a BuilderView Boundary object. 
 * Contains information and references to a list of allLevels, as well as the current level being modified.
 * @author Nicholas Panzarino
 */
public class BuilderModel {
	
	/**
	 * List of all levels for the game.
	 */
	LevelList allLevels;
	
	/**
	 * Current level being modified in the builder.
	 */
	AbstractLevel currentLevel;
	
	Stack<IAction> undoStack;
	Stack<IAction> redoStack;
	
	/**
	 * Constructor that creates a level list and place to store levels on disc.
	 */
	public BuilderModel(){
		undoStack = new Stack<IAction>();
		redoStack = new Stack<IAction>();
		
		allLevels=new LevelList();
//		for(int i=1;i<4;i++){
//			allLevels.addLevel(PuzzleLevel.class);
//			if(i<3)
//				allLevels.addLevel(ReleaseLevel.class);
//			if(i<2)
//				allLevels.addLevel(EliminationLevel.class);
//			if(i<1)
//				allLevels.addLevel(LightningLevel.class);
//		}
//		allLevels.addLevel(PuzzleLevel.class);
//		allLevels.addLevel(ReleaseLevel.class);
//		allLevels.addLevel(EliminationLevel.class);
//		allLevels.addLevel(LightningLevel.class);
		
		String fileName = "levelData.bin";


		try {
			allLevels =  (LevelList) SerializationUtil.deserialize(fileName);
		} catch (ClassNotFoundException | IOException e) {
			System.err.println("Builder could not load file: "+fileName);
			//e.printStackTrace();
		}
		
		//System.out.println(allLevels);
		
		currentLevel=null;
	}
	
	/**
	 * Constructor that takes in a LevelList and assigns it to the BuilderModel's LevelList. 
	 * @param allLevels
	 */
	public BuilderModel(LevelList allLevels){
		this.allLevels=allLevels;
	}
	
	/**
	 * Get all levels.
	 * @return LevelList
	 */
	public LevelList getAllLevels() {
		return allLevels;
	}

	/**
	 * Get current Level.
	 * @return AbstractLevel
	 */
	public AbstractLevel getCurrentLevel() {
		return currentLevel;
	}

	/**
	 * Set current level.
	 * @param AbstractLevel
	 */
	public void setCurrentLevel(AbstractLevel level){
		currentLevel=level;
	}
	
	/**
	 * Perform the given action by pushing the action onto the undo stack.
	 * @param act
	 */
	public void doAction(IAction act){
		if(act.doAction()){
			//System.out.println("Do: " + undoStack.size());
			undoStack.push(act);
			redoStack.clear();
			//System.out.println("Do: " + undoStack.size());
		}
	}
	
	/**
	 * Undo the most recent action, and push the undo onto the redo stack.
	 */
	public void undoAction(){
		//System.out.println("Undo: " + undoStack.size());
		if(!undoStack.isEmpty()){
			if(undoStack.peek().undoAction()){
				redoStack.push(undoStack.pop());
				//System.out.println("-Undo: " + undoStack.size());
				//System.out.println("+Redo: " + redoStack.size());
			}
		}
		BuilderViewReference.get().updateFields();
	}
	
	/**
	 * Redo the most recent undo, and push the redo action onto the undo stack.
	 */
	public void redoAction(){
		//System.out.println("Redo: " + redoStack.size());
		if(!redoStack.isEmpty()){
			if(redoStack.peek().doAction()){
				undoStack.push(redoStack.pop());
				//System.out.println("+Undo: " + undoStack.size());
				//System.out.println("-Redo: " + redoStack.size());
			}
		}
		BuilderViewReference.get().updateFields();
	}
}
