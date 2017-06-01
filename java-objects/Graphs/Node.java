import java.util.LinkedList;

/**
 * 
 * @author Jeff Sirocki, Marco Duran
 * The Node class a city name and a list of nodes.
 *
 */
class Node {
	/**
	* Name of city
	*/
	private String cityname;
	/**
	* LinkedList of nodes
	*/
	private LinkedList<Node> getsTo;
	
	/**
	* Default constructor for Node
	* Sets city to a string and getsTo to an empty list of strings.
	*/
	public Node(String cityname){
		this.cityname = cityname;
		this.getsTo = new LinkedList<Node>();
	}
	
	/**
	    * returns the city name in node.
	    *
	    * @return String
	    */
	public String getCityname(){
		return this.cityname;
	}
	
	/**
	    * returns the LinkedList of nodes that are connected to the node.
	    *
	    * @return LinkedList<Node>
	    */
	public LinkedList<Node> getGetsTo(){
		return this.getsTo;
	}
	
	/**
	    * Adds a node to the list of nodes the node getsTo.
	    *
	    * @param updates getsTo field to include the given node.
	    */
	public void addEdge(Node toNode){
		this.getsTo.add(toNode);
	}
	/**
	    * makes a network from the given node.
	    *
	    * @return Network
	    */
	public Network makeNetwork(Network result, LinkedList<Node> visited){
		
		if (!visited.contains(this))
		{
			result.addCity(this.getCityname());
			visited.add(this);
			for (Node n: getsTo){
				n.makeNetwork(result, visited);
			}
			return result;
		}
		else
		{
			return result;
		}
	}
}
	
	