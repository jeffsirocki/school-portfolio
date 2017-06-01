/**
 * 
 * @author Jeff Sirocki, Marco Duran
 * The Graph interface contains methods on lists of nodes.
 *
 */

interface IGraph {
	/**
	    * Adds a new node to the graph with the given string as a city name.
	    *
	    * @return Node
	    */
	Node newNode(String cityname);
	
	/**
	    * Add a directed edge from the "from" node to the "to" node.
	    *
	    * @param updates nodes getsTo field
	    * @return Node
	    */
	void addDirectedEdge(Node from, Node to);
	
	/**
	 * This method, getNetworks, uses accumulator style programming to get a list of networks.
	 * 
	 * @return ListOfNetwork
	 */
	ListOfNetwork getNetworks();
	
	/**
	    * Uses accumulator style programming to return a list of pairs that connect all flights in the network.
	    *
	    * @return ListOfPair
	    */
	public ListOfPair newFlights();
}
