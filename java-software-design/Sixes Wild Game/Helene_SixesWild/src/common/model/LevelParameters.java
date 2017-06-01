package common.model;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Holds all the level parameters in a level(moves, thresholds, frequencies, board, and tile fillfrom sources).
 * @author njpanzarino
 * @author jasirocki - jdoc
 *
 */
public class LevelParameters implements ICopy, Serializable{
	
	/**
	 * Serializable ID.
	 */
	private static final long serialVersionUID = -8303954416976988023L;
	
	/**
	 * Array list of all moves.
	 */
	protected ArrayList<Class<? extends AbstractMove>> moves=new ArrayList<Class<? extends AbstractMove>>();
	
	/**
	 * Score thresholds for 1,2,and 3 stars.
	 */
	protected ScoreThresholds thresholds=new ScoreThresholds();
	
	/**
	 * Regular tile spawn frequencies.
	 */
	protected FrequencyList spawn=new FrequencyList(new int[] {1,2,3,4,5,6});
	
	/**
	 * Bonus frequencies for each tile.
	 */
	protected FrequencyList bonus=new FrequencyList(new int[] {1,2,3});
	
	/**
	 * Board for the game.
	 */
	protected Board board=new Board(9);
	
	/**
	 * Map that holds all the fill from references and sources.
	 */
	protected ReferenceMap ref=new ReferenceMap();
	
	/**
	 * Constructor for level parameters.
	 */
	LevelParameters(){
		bonus.setFreq(1,Frequency.MAX_FREQ);
	}
	
	/**
	 * Constructor for Level Parameters given parameters.
	 * @param levelParameters
	 */
	public LevelParameters(LevelParameters levelParameters) {
		board=levelParameters.copyBoard();
		spawn=levelParameters.getSpawn().makeCopy();
		bonus=levelParameters.getBonus().makeCopy();
		thresholds=levelParameters.getThresholds().makeCopy();
		ref=levelParameters.getRef().makeCopy();
		moves=levelParameters.getMoves();
	}

	/**
	 * Set the size of the board.
	 * @param size
	 */
	public void setSize(int size){
		board.setSize(size);
		ref.setSize(size);
	}
	
	/**
	 * Get the frequency list.
	 * @return FrequencyList
	 */
	public FrequencyList getSpawn() {
		return spawn;
	}

	/**
	 * Set the frequency list.
	 * @param spawn
	 */
	public void setSpawn(FrequencyList spawn) {
		this.spawn = spawn;
	}

	/**
	 * get the bonus frequencies.
	 * @return FrequencyList
	 */
	public FrequencyList getBonus() {
		return bonus;
	}
	
	/**
	 * Set the bonus frequencies.
	 * @param bonus
	 */
	public void setBonus(FrequencyList bonus) {
		this.bonus = bonus;
	}

	/**
	 * Get the star thresholds.
	 * @return ScoreThresholds
	 */
	public ScoreThresholds getThresholds() {
		return thresholds;
	}

	/**
	 * Set the star thresholds.
	 * @param thresholds
	 */
	public void setThresholds(ScoreThresholds thresholds) {
		this.thresholds = thresholds;
	}

	/**
	 * Get the board.
	 * @return Board
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * Set the board.
	 * @param board
	 */
	public void setBoard(Board board) {
		this.board = board;
		ref.setSize(board.getSize());
	}

	/**
	 * Get the sources for fillfrom.
	 * @return ReferenceMap
	 */
	public ReferenceMap getRef() {
		return ref;
	}

	
	/**
	 * Set the referenceMap.
	 * @param ref
	 */
	public void setRef(ReferenceMap ref) {
		this.ref = ref;
		updateRef(board);
	}

	/**
	 * Set the moves available in the level.
	 * @param moves
	 */
	public void setMoves(ArrayList<Class<? extends AbstractMove>> moves) {
		this.moves = moves;
	}

	/**
	 * Get the moves available in the level.
	 * @return moves
	 */
	public ArrayList<Class<? extends AbstractMove>> getMoves() {
		return moves;
	}

	/**
	 * Copy level parameters without references.
	 */
	public LevelParameters makeCopy() {
		return new LevelParameters(this);
	}
	
	/**
	 * Copy the board without references.
	 * @return board
	 */
	public Board copyBoard() {
		Board b=board.makeCopy();
		updateRef(b);
		return b;
	}
	
	/**
	 * Updates the references of this Objects board according to the ReferenceMap object in these parameters.
	 */
	public void updateRef(){
		updateRef(board);
	}

	/**
	 * Updates the references of the specified board according to the ReferenceMap object in these parameters.
	 * @param board
	 */
	public void updateRef(Board board) {
		for(Point p:ref){
			AbstractSquare fill=board.getSquare(p);
			AbstractSquare from=board.getSquare(ref.getRef(p));
			if(fill instanceof AbstractContainer){
				loop:
				while(true){
					if(from instanceof ISource){
						((AbstractContainer) fill).setFillFrom(from);
						break loop;
					}
					else if((from==null)||(ref.bridge==false)){
						((AbstractContainer) fill).setFillFrom(new RandomSource(spawn,bonus));
						break loop;
					}
					else{
						from=board.getSquare(ref.getRef(new Point(from.getRow(),from.getColumn())));
					}
				}
			}
		}
	}
	
	/**
	 * Copy the Level parameters.
	 */
	public void copy(ICopy object)
	{
		LevelParameters levelParameters=(LevelParameters)object;
		board.copy(levelParameters.copyBoard());
		spawn.copy(levelParameters.getSpawn());
		bonus.copy(levelParameters.getBonus());
		thresholds=levelParameters.getThresholds().makeCopy();
		ref.copy(levelParameters.getRef().makeCopy());
		moves=levelParameters.getMoves();
	}
}
