import java.util.LinkedList;

/**
 * 
 * @author Jeff Sirocki, Marco Duran
 * The ListOfPair class contains a list of pairs 
 * and methods on these collections of pairs.
 *
 */
class ListOfPair{
	/**
	* LinkedList of pairs
	*/
	private LinkedList<Pair> pairs;
	
	/**
	* Default constructor for ListOfPair
	* Sets ListOfPair to an empty list of pairs.
	*/
	public ListOfPair(LinkedList<Pair> pairs){
		this.pairs = new LinkedList<Pair>();
	}
	
	/**
	    * Add a pair to the list Of pairs
	    *
	    * @param updates list's pairs field to include the given pair
	    */
	public void addPair(Pair pair){
		this.pairs.add(pair);
	}
}