package common.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;

/**
 * Stores and manages a number of frequency objects.
 * Ensures that frequencies always add to MAX_FREQ (i.e 100).
 * Automatically adjusts Frequencies and priorities.
 * @author Nicholas Panzarino
 * @author jasirocki - jdoc
 *
 */
public class FrequencyList implements Iterable<Frequency>, ICopy, Serializable{
	
	/**
	 * Serializable ID.
	 */
	private static final long serialVersionUID = -8303954416976988023L;
	
	/**
	 * Hashtable that contains the six frequencies.
	 */
	Hashtable<Integer,Frequency> table=new Hashtable<Integer,Frequency>();
	
	/**
	 * Constructor for frequency list given an array of values.
	 * @param values
	 */
	public FrequencyList(int[] values){
		for(int i=0;i<values.length;i++){
			Frequency f=new Frequency(values[i]);
			f.setFrequency((Frequency.MAX_FREQ-Frequency.MIN_FREQ)/values.length+Frequency.MIN_FREQ);
			table.put(values[i],f);
		}
		update();
	}
	
	/**
	 * Constructor for frequency list given a list.
	 * @param list
	 */
	public FrequencyList(FrequencyList list){
		for(Frequency f:list){
			table.put(f.getValue(),f.makeCopy());
		}
	}
	
	/**
	 * Constructor for frequency list given an array list of ints.
	 * @param list
	 */
	public FrequencyList(ArrayList<Integer> list) {
		for(int i=0;i<list.size();i++){
			Frequency f=new Frequency(list.get(i));
			f.setFrequency((Frequency.MAX_FREQ-Frequency.MIN_FREQ)/list.size()+Frequency.MIN_FREQ);
			table.put(list.get(i),f);
		}
		update();
	}

	/**
	 * Copy frequency list without references.
	 */
	public FrequencyList makeCopy(){
		return new FrequencyList(this);
	}
	
	/**
	 * Copy the Frequency list.
	 */
	public void copy(ICopy object){
		for(Frequency f:((FrequencyList)object).getList()){
			table.put(f.getValue(),f.makeCopy());
		}
	}
	
	/**
	 * Set frequency given tile value and frequency.
	 * @param value
	 * @param freq
	 * @return boolean
	 */
	public boolean setFreq(int value,float freq){
		return setFreq(table,value,freq);
	}
	
	/**
	 * Get frequency of a given tile value.
	 * @param value
	 * @return float
	 */
	public float getFreq(int value){
		return table.get(value).getFrequency();
	}
	
	/**
	 * Set frequency of a tile with a value and frequency.
	 * @param table
	 * @param value
	 * @param freq
	 * @return boolean
	 */
	private boolean setFreq(Hashtable<Integer,Frequency> table,int value,float freq){
		if(table.containsKey(value)==false)
			return false;
		
		Frequency t=table.get(value);
		t.setFrequency(freq);
		
		if(t.priority>0){
			for(Frequency f:this){
				f.incrementPriority();
			}
		}
		t.setPriority(0);
		
		update();
		
		return true;
	}
	
	/**
	 * Update the frequency list.
	 */
	public void update(){
		
		float sum=sumFreq(table);
		while(sum>Frequency.MAX_FREQ){
			lowestPriorityNonZero().changeFrequency(Frequency.MAX_FREQ-sum);
			sum=sumFreq(table);
		}
		while(sum<Frequency.MAX_FREQ){
			lowestPriorityNonMax().changeFrequency(Frequency.MAX_FREQ-sum);
			sum=sumFreq(table);
		}
	}

	/**
	 * Sum of the frequencies in the list.
	 * @param table
	 * @return sum
	 */
	private float sumFreq(Hashtable<Integer,Frequency> table){
		float sum=0;
		for(Frequency f:this){
			sum+=f.getFrequency();
		}
		return sum;
	}
	
	/**
	 * Return lowest priority that is non zero.
	 * @return Frequency
	 */
	private Frequency lowestPriorityNonZero(){
		Frequency out=table.elements().nextElement();
		for(Frequency f:this){
			if(out.getFrequency()<=Frequency.MIN_FREQ)
				out=f;
			if((f.priority>=out.priority)&&(f.getFrequency()>Frequency.MIN_FREQ))
				out=f;
		}
		return out;
	}
	
	/**
	 * Return lowest priority that is non max.
	 * @return Frequency
	 */
	private Frequency lowestPriorityNonMax(){
		Frequency out=table.elements().nextElement();
		for(Frequency f:this){
			if(out.getFrequency()>=Frequency.MAX_FREQ)
				out=f;
			if((f.priority>=out.priority)&&(f.getFrequency()<Frequency.MAX_FREQ))
				out=f;
		}
		return out;
	}
	
	/**
	 * Get hash table list of frequencies.
	 * @return Hashtable<Integer, Frequency>
	 */
	public Hashtable<Integer, Frequency> getTable() {
		return table;
	}
	
	/**
	 * Get array list of frequencies.
	 * @return ArrayList<Frequency>
	 */
	public ArrayList<Frequency> getList() {
		ArrayList<Frequency> out=new ArrayList<Frequency>();
		for(Frequency f:this){
			out.add(f);
		}
		Collections.sort(out);
		return out;
	}
	
	/**
	 * Iterate over hash table and return collection of frequencies.
	 */
	@Override
	public Iterator<Frequency> iterator() {
		return Collections.list(table.elements()).iterator();
	}

	/**
	 * Return a string representation of all frequencies.
	 */
	@Override
	public String toString(){
		String p="";
		p+="Freqs:\n";
		for(Frequency f:this){
			p+="\t"+f+"\n";
		}
		return p;
	}
}
