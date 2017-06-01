import java.util.LinkedList;

/**
 * 
 * @author Jeff Sirocki, Marco Duran
 * 
 * The ListOfNetwork class contains a list of networks 
 * and methods on these collections of networks.
 *
 */
class ListOfNetwork{
	/**
	* LinkedList of networks
	*/
	private LinkedList<Network> networks;
	
	/**
	* Default constructor for ListOfNetwork
	* Sets ListOfNetwork to an empty list of networks.
	*/
	public ListOfNetwork(LinkedList<Network> networks){ 
		this.networks = new LinkedList<Network>();
	}
	/**
	    * returns the LinkedList of networks in the list.
	    *
	    * @return LinkedList<Network>
	    */
	public LinkedList<Network> getLofNetworks(){
		return this.networks;
	}
	/**
	    * Does ListOfNetwork contain the given node?
	    *
	    * @return boolean
	    */
	public boolean containsNode(Node node){
		for (Network net: networks){
			if (net.containsName(node.getCityname()))
				return true;
		}
		return false;
	}
	
	/**
	    * Add a network to the list of networks.
	    *
	    * @param updates list's networks field to include the given network
	    */
	public void addNetwork(Network network){
		this.networks.add(network);
	}
}