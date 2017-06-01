import java.util.LinkedList;

/**
 * 
 * @author Jeff Sirocki, Marco Duran
 * The Graph class contains a list of nodes and methods on these collections of nodes.
 *
 */
class Graph implements IGraph {
	/**
	* LinkedList of nodes
	*/
	private LinkedList<Node> nodes; //these are all of the nodes in the graph
	
	
	/**
	* Default constructor for Graph
	* Sets Graph to an empty list of nodes.
	*/
	public Graph(){
		this.nodes = new LinkedList<Node>();
	}
	
	/**
	    * Adds a new node to the graph with the given string as a city name.
	    *
	    * @return Node
	    */
	public Node newNode(String cityname){
		Node newN = new Node(cityname);
		this.nodes.add(newN);
		return newN;
	}
	
	/**
	    * Add a directed edge from the "from" node to the "to" node.
	    *
	    * @param updates nodes getsTo field
	    */
	public void addDirectedEdge(Node from, Node to){
		from.addEdge(to);
	}
	/**
	 * This method, getNetworks, uses accumulator style programming to get a list of networks.
	 * 
	 * @return ListOfNetwork
	 */
	public ListOfNetwork getNetworks(){
		ListOfNetwork result = new ListOfNetwork(new LinkedList<Network>());
		LinkedList<Node> visited = new LinkedList<Node>();
		
		for(Node node: nodes){  // tests if the result contains the node being tested
			if(visited.contains(node)){
				// do nothing
			}
			else 
			{
				
				result.addNetwork(node.makeNetwork(new Network(new LinkedList<String>()), visited));
			}
		}		
		return result;
	}
	
	/**
	    * Uses accumulator style programming to return a list of pairs that connect all flights in the network.
	    *
	    * @return ListOfPair
	    */
	public ListOfPair newFlights(){
		LinkedList<Network> Flights = this.getNetworks().getLofNetworks();
		ListOfPair result = new ListOfPair(new LinkedList<Pair>());
		
		for (Network network: Flights){
			if (network.getFirstCity() != Flights.getLast().getFirstCity())
			{
				result.addPair(new Pair(network.getFirstCity(), Flights.getLast().getFirstCity()));
			}
			else
			{
				// do nothing
			}
		}
		return result;
	}
}