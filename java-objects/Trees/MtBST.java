//import java.util.*;

class MtBST implements IBST {
	MtBST(){}
	
	public IBST addElt(AbsAttraction elt) {
		return new DataBST(elt, new MtBST(), new MtBST());
	}

	
	public int accept(ISelect f) {
	
		throw new RuntimeException("Cannot compute");
	}
	
	public boolean containsAny(ISelect s){
		return false;
	}
	
	public boolean allAre(ISelect s){
		return true;
	}
	/*
	public ILof filter(ISelect s){
		LinkedList<AbsAttraction> list = new LinkedList<AbsAttraction>();
		
		return list;
	}
	*/
}
