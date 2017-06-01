package common.model;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;

/**
 * HashTable that holds all levels in the game.
 * @author njpanzarino
 * @author jasirocki - jdoc
 *
 */
public class LevelList implements Iterable<AbstractLevel> , Serializable {
	
	/**
	 * Serializable ID.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Stores Levels in a hashtable. The key is equal to getType()+": "+levelID.
	 */
	protected Hashtable<String,AbstractLevel> allLevels=new Hashtable<String,AbstractLevel>();
	
	/**
	 * Adds the input level to this list. 
	 * If level of the same type/number exists, it is overridden with the new one.
	 * @param level
	 */
	public void addLevel(AbstractLevel level){
		if(level==null)
			return;
		if(allLevels.containsKey(level.toString())){
			allLevels.remove(level.toString());
		}
		allLevels.put(level.toString(), level);
	}
	
	/**
	 * Adds a new level in the first open position starting at 'start'.
	 * @param c: the class of the level to add
	 * @param start: the starting index of the level
	 * @return
	 */
	public AbstractLevel addLevel(Class<? extends AbstractLevel> c, Integer start){
		
		if(start == null||start<1){
			start = 1;
		}
		
		ArrayList<AbstractLevel> list = getType(c);
		Collections.sort(list);
		
		int[] id = new int[list.size()];
		for(int i = 0; i < list.size(); i++){
			id[i] = list.get(i).getLevelID();
		}
		
		AbstractLevel level = null;
		int n = 1;
		
		loop:
		for(int i = 0; i < id.length; i++){
			if(id[i] == start){
				for(int j = i; j < id.length-1; j++){
					if((id[j]+1)!=id[j+1]){
						n=id[j]+1;
						break loop;
					}
				}
				n=id[id.length-1]+1;
				break loop;
			}
		}
		
		try {
			level = c.getDeclaredConstructor(Integer.class).newInstance(n);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			System.err.println("In LevelList, addLevel(c,n): could not create instance of class: "+c.getSimpleName());
			//e.printStackTrace();
		}
		
		addLevel(level);
		return level;
	}
	
	/**
	 * Removes the input level from the level list, if the level exists.
	 * @param level
	 */
	public void removeLevel(AbstractLevel level){
		if(level==null)
			return;
		
		if(this.contains(level.toString())){
			allLevels.remove(level.toString());
		}
	}
 
	/**
	 * adds a new level of the specified type to the list of all Levels.
	 * @param c
	 */
	public AbstractLevel addLevel(Class<? extends AbstractLevel> c){
		return addLevel(c,1);
	}
	
	/**
	 * Contains this key.
	 * @param key
	 * @return boolean
	 */
	public boolean contains(String key){
		return allLevels.containsKey(key);
	}
	
	/**
	 * Get this level with this key.
	 * @param key
	 * @return AbstractLevel
	 */
	public AbstractLevel getLevel(String key){
		return allLevels.get(key);
	}
	
	/**
	 * returns the level corresponding to the input type and ID number, or null if none exists.
	 * @param c
	 * @param id
	 * @return
	 */
	public AbstractLevel getLevel(Class<? extends AbstractLevel> c,Integer id){
		if(id==null)
			return null;
		for(AbstractLevel level:this){
			if((level.getLevelID()==id)&&(level.getClass().equals(c))){
				return level;
			}
		}
		return null;
	}
	
	/**
	 * Returns a count of the number of unique level class types in this list.
	 * @param c a class which extends Abstract level
	 * @return a count of the number of levels in this list which are of that type
	 */
	public int countType(Class<? extends AbstractLevel> c){
		if(allLevels.isEmpty())
			return 0;
		
		int n=0;
		for(AbstractLevel l:this){
			if(l.getClass().equals(c)){
				n++;
			}
		}
		return n;
	}
	
	/**
	 * returns an unsorted array of levels. To sort in order of level number use Collections.sort().
	 * @param c a class which extends Abstract level
	 * @return all levels in this list which are of that class type
	 */
	public ArrayList<AbstractLevel> getType(Class<? extends AbstractLevel> c){
		ArrayList<AbstractLevel> list=new ArrayList<AbstractLevel>();
		for(AbstractLevel l:this){
			if(l.getClass().equals(c)){
				list.add(l);
			}
		}
		return list;
	}
	
	/**
	 * Counts the number of level types.
	 * @param c a class which extends Abstract level
	 * @return a count of the number of distinct level types in this list
	 */
	public int countTypes(){
		return getTypes().size();
	}
	
	/**
	 * Returns a list of the classes of all the distinct level types in this list.
	 * @return a list of the classes of all the distinct level types in this list
	 */
	public ArrayList<Class<? extends AbstractLevel>> getTypes(){
		ArrayList<Class<? extends AbstractLevel>> list=new ArrayList<Class<? extends AbstractLevel>>();
		for(AbstractLevel l:this){
			if(!list.contains(l.getClass())){
				list.add(l.getClass());
			}
		}
		return list;
	}

	/**
	 * Iterate over all levels in HashTable.
	 */
	@Override
	public Iterator<AbstractLevel> iterator() {
		return Collections.list(allLevels.elements()).iterator();
	}

	/**
	 * Return a string representation of the LevelList.
	 */
	@Override
	public String toString() {
		String p="LevelList:\n";
		ArrayList<Class<? extends AbstractLevel>> types=getTypes();
		for(Class<? extends AbstractLevel> c:types){
			ArrayList<AbstractLevel> list=getType(c);
			for(AbstractLevel l:list){
				p+="  "+l.toString()+"\n";
			}
		}
		return p;
	}
	
	/**
	 * Update whether levels are unlocked.
	 */
	public void updateUnlocked(){
		for(Class<? extends AbstractLevel> t:getTypes()){
			ArrayList<AbstractLevel> list=getType(t);
			Collections.sort(list);
			list.get(0).setUnlocked(true);
			for(int i=1;i<list.size();i++){
				boolean b=(list.get(i-1).getParams().getThresholds().getNumStars(list.get(i-1).getHighscore()))>0;
				list.get(i).setUnlocked(b);
			}
		}
	}
	
}
