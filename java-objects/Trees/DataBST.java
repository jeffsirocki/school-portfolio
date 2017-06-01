//import java.util.*;


class DataBST implements IBST {
	
	AbsAttraction elt;
	IBST left;
	IBST right;
	
	DataBST(AbsAttraction elt, IBST left, IBST right){
		this.elt = elt;
		this.left = left;
		this.right = right;
		
	}
	
	public IBST addElt(AbsAttraction otherelt) {
		if (elt.name.compareTo(otherelt.name) > 0)
			return new DataBST(this.elt, this.left.addElt(otherelt), this.right);
		else
			return new DataBST(this.elt, this.left, this.right.addElt(otherelt));
	}

	
	public boolean containsAny(ISelect s){
		if (elt.accept(s) || left.containsAny(s) || right.containsAny(s))
			return true;
		else 
			return false;
	}

	
	public boolean allAre(ISelect s) {
		
		if(elt.accept(s) && left.allAre(s) && right.allAre(s))
			return true;
		else
			return false;
	}


	/*
	public ILof filter(ISelect s) {
		LinkedList<AbsAtraction> list = new LinkedList<AbsAttraction>();
		
		list.addAll(this.left.filter(s));
		if(elt.accept(s))
			list.add(this.elt);
		list.addAll(this.right.filter(s));
	}
	*/

}
