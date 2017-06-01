
interface IBST {
//adds element to the BST
	IBST addElt(AbsAttraction elt);
	
	// does this BST contain any attractions that satisfy the given criteria?
	boolean containsAny(ISelect s);

	// do all of the attractions in this BST satisfy the given criteria?
	boolean allAre(ISelect s);

	/*
	// filter this BST of attractions, returning a list of attractions that
	// satisfy the given criteria
	ILof filter(ISelect s);
	*/
	
}
