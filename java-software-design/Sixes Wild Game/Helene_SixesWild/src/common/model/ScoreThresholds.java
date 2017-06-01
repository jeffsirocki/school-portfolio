package common.model;

import java.io.Serializable;

/**
 * Contains the score thresholds to get 1,2,and 3 stars in a game.
 * @author njpanzarino
 * @author tjbennett
 * @author jasirocki - jdoc
 *
 */
public class ScoreThresholds implements ICopy, Serializable{
	
	/**
	 * Serializable ID.
	 */
	private static final long serialVersionUID = -8303954416976988023L;
	
	
	/**
	 * holds scores needed for 0-X stars (Default X=3).
	 */
	int scoreThresh[];
	
	/**
	 * Constructor for score thresholds.
	 */
	ScoreThresholds(){
		scoreThresh=new int[4];
		for(int i=0;i<scoreThresh.length;i++)
			scoreThresh[i]=i;
	}
	
	/**
	 * Constructor for score thresholds given numbers for 1,2,and3 stars.
	 * @param one
	 * @param two
	 * @param three
	 */
	ScoreThresholds(int one, int two, int three){
		scoreThresh=new int[4];
		scoreThresh[0]=0;
		scoreThresh[1]=one;
		scoreThresh[2]=two;
		scoreThresh[3]=three;
	}
	
	/**
	 * Get number of stars rewarded given a score.
	 * @param inScore
	 * @return int
	 */
	public int getNumStars(int inScore){
		for(int i=0;i<scoreThresh.length;i++)
			if(inScore<scoreThresh[i])
				return i-1;
		return scoreThresh.length-1;
	}

	/**
	 * Copy the score thresholds without references.
	 */
	public ScoreThresholds makeCopy() {
		ScoreThresholds copy = new ScoreThresholds(scoreThresh[1], scoreThresh[2], scoreThresh[3]);
		return copy;
	}

	/**
	 * Get score thresh for tile num.
	 * @param i
	 * @return
	 */
	public int getScoreThresh(int i) {
		return scoreThresh[i];
	}
	
	/**
	 * Set score threshold.
	 * @param i
	 * @param val
	 */
	public void setThreshold(int i, int val) {
		scoreThresh[i] = val;
	}

	/**
	 * Copy the object.
	 */
	@Override
	public void copy(ICopy object) {
		if(object.getClass().equals(this.getClass()))
			this.scoreThresh[1] = ((ScoreThresholds) object).getScoreThresh(1);
			this.scoreThresh[2] = ((ScoreThresholds) object).getScoreThresh(2);
			this.scoreThresh[3] = ((ScoreThresholds) object).getScoreThresh(3);
	}
}
