package gameModes.lightning;

import java.awt.Color;
import java.io.Serializable;

import builder.view.ParameterPanel;

import common.model.AbstractLevel;
import common.model.ICopy;

/**
 * Foundation class for the elements in the lightning level.
 * @author rlamsal-jdoc
 *
 */
public class LightningLevel extends AbstractLevel implements Serializable{
	
	/**
	 * Serializable id.
	 */
	private static final long serialVersionUID = -8303954416976988023L;

	/**
	 * Maximum time allowed.
	 */
	int maxSeconds=180;
	
	/**
	 * Constructor that takes in the level number.
	 * @param num
	 */
	public LightningLevel(Integer num) {
		super(num);
	}

	/**
	 * Constructor that takes in a level.
	 * @param level
	 */
	public <T extends AbstractLevel> LightningLevel(T level) {
		super(level);
	}
	
	/**
	 * makes a copy of the level.
	 */
	public LightningLevel makeCopy() {
		LightningLevel copy = new LightningLevel(this);
		copy.setMaxSeconds(maxSeconds);
		return copy;
	}

	/**
	 * returns maximum time.
	 * @return
	 */
	public int getMaxSeconds() {
		return maxSeconds;
	}

	/**
	 * sets maximum time.
	 * @param maxSeconds
	 */
	public void setMaxSeconds(int maxSeconds) {
		this.maxSeconds = maxSeconds;
	}

	/**
	 * makes a game with a lightning level.
	 */
	@Override
	public LightningGame makeGame() {
		return new LightningGame(this);
	}
	
	/**
	 * return a string with the level type.
	 * @return
	 */
	public static String type() {
		return "Lightning";
	}
	
	/**
	 * returns a new lightning parameter panel.
	 * @return
	 */
	public static ParameterPanel getAdditionalParameterPanel(){
		return new LightningParameterPanel();
	}
	
	/**
	 * creates a copy of a lightning level.
	 */
	@Override
	public void copy(ICopy object)
	{
		super.copy(object);
		setMaxSeconds(((LightningLevel)object).getMaxSeconds());
	}
	
	/**
	 * gets a new color for the level.
	 */
	@Override
	public Color getColor() {
		return new Color(50, 205, 50);
	}
}
