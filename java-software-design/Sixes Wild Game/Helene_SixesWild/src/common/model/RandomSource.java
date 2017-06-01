package common.model;

import java.util.Stack;

/**
 * Random source for a specific tile to fill from.
 * @author njpanzarino
 * @author jasirocki - jdoc
 *
 */
public class RandomSource implements ISource {

	/**
	 * Frequency list for regular tiles.
	 */
	FrequencyList spawn;
	/**
	 * Frequency list for bonuses.
	 */
	FrequencyList bonus;
	
	/**
	 * Stack of all tiles.
	 */
	Stack<Tile> stack;
	
	/**
	 * Constructor for RandomSource given both frequencies.
	 * @param spawn
	 * @param bonus
	 */
	public RandomSource(FrequencyList spawn,FrequencyList bonus){
		this.spawn=spawn;
		this.bonus=bonus;
		stack=new Stack<Tile>();
	}
	
	/**
	 * Take tile from the stack if it is empty.
	 */
	@Override
	public Tile takeTile() {
		if(stack.isEmpty()){
			return makeTile();
		}
		else
			return stack.pop();
	}

	/**
	 * Pust tile onto the stack.
	 */
	@Override
	public void push(Tile tile) {
		stack.push(tile);
	}

	/**
	 * Peek at the top tile on the stack.
	 */
	@Override
	public Tile peek() {
		if(stack.empty()){
			stack.push(makeTile());
		}
		return stack.peek();
	}
	
	/**
	 * Make a new tile.
	 * @return Tile
	 */
	public Tile makeTile(){
		int val=1, multiplier=1;
		
		double r=Math.random()*Frequency.MAX_FREQ-Frequency.MIN_FREQ;
		double sum=0;
		
		loop:
		for(Frequency f:spawn){
			sum+=f.getFrequency();
			if(sum>r){
				val=f.getValue();
				break loop;
			}
		}
		
		r=Math.random()*Frequency.MAX_FREQ-Frequency.MIN_FREQ;
		sum=0;
		
		loop:
			for(Frequency f:bonus){
				sum+=f.getFrequency();
				if(sum>r){
					multiplier=f.getValue();
					break loop;
				}
			}
		
		return new Tile(val,multiplier);
	}

}
