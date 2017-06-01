package common.model;

import java.io.Serializable;

/**
 * Frequency object that contains a value, frequency, and priority.
 * @author njpanzarino
 * @author jasirocki - jdoc
 *
 */
public class Frequency implements Comparable<Frequency>, ICopy, Serializable{
	
	/**
	 * Serializable ID.
	 */
	private static final long serialVersionUID = -8303954416976988023L;
	
	/**
	 * Max Freq.
	 */
	public static int MAX_FREQ=100;
	
	/**
	 * Minimum Freq.
	 */
	public static int MIN_FREQ=0;

	/**
	 * Tile value.
	 */
	protected int value;
	
	/**
	 * Tile frequency.
	 */
	protected float frequency;
	
	/**
	 * Tile frequency priority.
	 */
	protected int priority;

	/**
	 * Constructor for frequency given tile value.
	 * @param value
	 */
	Frequency(int value){
		this.value=value;
		this.frequency=0;
		this.priority=1;
	}
	
	Frequency(Frequency freq)
	{
		this.value = freq.value;
		this.frequency =freq.frequency;
		this.priority = freq.priority;
	}
	
	/**
	 * Get tile value.
	 * @return value
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Copy frequency without references.
	 */	
	public void copy(ICopy object){
		
	}

	/**
	 * Get frequency.
	 * @return frequency
	 */
	public float getFrequency() {
		return frequency;
	}
	
	/**
	 * Change frequency to a different float.
	 * @param delta
	 */
	public void changeFrequency(float delta){
		setFrequency(this.frequency+delta);
	}

	/**
	 * Get priority of frequency.
	 * @return priority
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * Set frequency given a frequency.
	 * @param frequency
	 */
	public void setFrequency(float frequency) {
		if(frequency>MAX_FREQ)
			frequency=MAX_FREQ;
		else if(frequency<MIN_FREQ)
			frequency=MIN_FREQ;
		
		this.frequency = frequency;
	}

	/**
	 * Set priority.
	 * @param priority
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}

	/**
	 * Increment priority.
	 */
	public void incrementPriority(){
		setPriority(priority+1);
	}
	
	/**
	 * Return string representation of frequency.
	 */
	public String toString(){
		return value+": "+frequency+" | "+priority;
	}

	/**
	 * Compare to method.
	 */
	@Override
	public int compareTo(Frequency arg0) {
		return Integer.compare(this.getValue(),arg0.getValue());
	}

	/**
	 * Copy Frequencies.
	 */
	public Frequency makeCopy() {
		return new Frequency(this);
	}
}
