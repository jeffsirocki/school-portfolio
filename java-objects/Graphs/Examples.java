// Jeff Sirocki (jasirocki)
// Marco Duran (mdduran)
// Homework 6

import java.util.*; 
import tester.* ;

/**
 * 
 * @author Jeff Sirocki, Marco Duran
 * The Examples class contains examples of data in the graph 
 * and tests the methods we used on graphs.
 *
 */
class Examples {
	Examples (){
		initGraph();
	}
	
	Graph G = new Graph();
	Node bost, worc, hart, chic, denv, pheo, hous, tulsa;
	
	ListOfNetwork lon1 = new ListOfNetwork(new LinkedList<Network>());
	
	LinkedList<Network> linklon = new LinkedList<Network>();
	
	LinkedList<Node> linknode = new LinkedList<Node>();
	
	ListOfPair lop1 = new ListOfPair(new LinkedList<Pair>());
	
	Network net1 = new Network(new LinkedList<String>());
	Network net2 = new Network(new LinkedList<String>());
	Network net3 = new Network(new LinkedList<String>());
	Network emptynet1 = new Network(new LinkedList<String>());
	Network emptynet2 = new Network(new LinkedList<String>());
	Network emptynet3 = new Network(new LinkedList<String>());
	
	Pair pair1 = new Pair("Boston", "Pheonix");
	Pair pair2 = new Pair("Chicago", "Pheonix");
	
	LinkedList<Node> visitedNE = new LinkedList<Node>();//visited cities in New England Area
	LinkedList<Node> visitedMW = new LinkedList<Node>();//visited cities in MidWest Area
	LinkedList<Node> visitedSW = new LinkedList<Node>();//visited cities in the SouthWest Area
	LinkedList<Node> emptyvisitedNE = new LinkedList<Node>();
	LinkedList<Node> emptyvisitedMW = new LinkedList<Node>();
	LinkedList<Node> emptyvisitedSW = new LinkedList<Node>();
	
	void initGraph(){
		// add nodes to graph
		bost = this.G.newNode("Boston");
		worc = this.G.newNode("Worcester");
		hart = this.G.newNode("Hartford");
		chic = this.G.newNode("Chicago");
		denv = this.G.newNode("Denver");
		pheo = this.G.newNode("Pheonix");
		hous = this.G.newNode("Houston");
		tulsa = this.G.newNode("Tulsa");
	
		//add nodes to linkedLists in NE area
		visitedNE.add(bost);
		visitedNE.add(worc);
		visitedNE.add(hart);
		
		//add nodes to linkedLists in MW area
		visitedMW.add(chic);
		visitedMW.add(denv);
		
		//add nodes to linkedLists in SW area
		visitedSW.add(tulsa);
		visitedSW.add(hous);
		visitedSW.add(pheo);
		
		//Network 1 
		G.addDirectedEdge(bost, worc);
		G.addDirectedEdge(worc, bost);
		G.addDirectedEdge(bost, hart);
		G.addDirectedEdge(hart, bost);
		net1.addCity("Boston");
		net1.addCity("Worcester");
		net1.addCity("Hartford");
		
		//Network 2 
		G.addDirectedEdge(denv, chic);
		G.addDirectedEdge(chic, denv);
		net2.addCity("Chicago");
		net2.addCity("Denver");
		
		//Network 3
		G.addDirectedEdge(pheo, hous);
		G.addDirectedEdge(hous, pheo);
		G.addDirectedEdge(hous, tulsa);
		G.addDirectedEdge(tulsa, hous);
		net3.addCity("Pheonix");
		net3.addCity("Houston");
		net3.addCity("Tulsa");
		
		//combined networks
		linklon.add(net1);
		linklon.add(net2);
		linklon.add(net3);
		
		//bost getsTo list
		linknode.add(worc);
		linknode.add(hart);
		
		//LON1
		lon1.addNetwork(net1);
		lon1.addNetwork(net2);
		lon1.addNetwork(net3);
		
		//LOP1
		lop1.addPair(pair1);
		lop1.addPair(pair2);
	}
	
	// TEST CASES
	
	public boolean testgetLofNetworks(Tester t){
		return t.checkExpect(lon1.getLofNetworks(), linklon);
	}
	
	public boolean testgetFirstCity(Tester t){
		return t.checkExpect(net1.getFirstCity(), "Boston");
	}
	
	public boolean testgetCityname(Tester t){
		return t.checkExpect(bost.getCityname(), "Boston");
	}
	
	public boolean testgetGetsTo(Tester t){
		return t.checkExpect(bost.getGetsTo(), linknode);
	}
	
	public boolean testmakeNetwork(Tester t){
	    return t.checkExpect (bost.makeNetwork( emptynet1, emptyvisitedNE), net1) &&
	    		t.checkExpect(chic.makeNetwork(emptynet2, emptyvisitedMW), net2)&&
	    		t.checkExpect(pheo.makeNetwork(emptynet3, emptyvisitedSW), net3);
	}
	
	public boolean testcontainsNode(Tester t){
		return t.checkExpect(lon1.containsNode(bost), true) &&
				t.checkExpect(lon1.containsNode(new Node("New York")), false);
	}
	
	public boolean testcontainsName(Tester t){
		return t.checkExpect(net1.containsName("Boston"), true) &&
				t.checkExpect(net1.containsName("Tulsa"), false);
	}
	
	public boolean testgetNetworks(Tester t){
	    return t.checkExpect (G.getNetworks(), lon1);
	}
	
	public boolean testnewFlights(Tester t){
	    return t.checkExpect (G.newFlights(), lop1);
	}
}