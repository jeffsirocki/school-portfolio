class DataHeap implements IHeap{
	int data;
	IHeap left;
	IHeap right;
	
	DataHeap(int data, IHeap left, IHeap right){
		this.data = data;
		this.left = left;
		this.right = right;
	}
	
	// returns heap containing all existing elements and the given element
	public IHeap addElt(int data){
		if (data < this.data)
			return new DataHeap(data, this, new MtHeap());
		else{
			return new DataHeap(this.data, this.left.addElt(data), this.right);
		}
	}
	
	// returns the smallest element in the heap
	public int findMinElt(){
		return this.data;
	}
	
	// returns a heaps left subtree
		public IHeap getLeft(){
			return this.left;
		}
	
	// returns a heaps right subtree
	public IHeap getRight(){
		return this.right;
	}
	
	
	// takes two heaps and merges them
	public IHeap merge(IHeap H1, IHeap H2) {
		IHeap ST1;//subtree 1
		IHeap ST2;//subtree 2
		IHeap ST3;//subtree 3
		int newRoot;
			
		if(H1.findMinElt() <= H2.findMinElt()){
			  newRoot = H1.findMinElt();
		}
		else
			 newRoot = H2.findMinElt();
			
		if(newRoot == H1.findMinElt()){
			ST1 = H1.getLeft();
			ST2 = H1.getRight();
			ST3 = H2;
				
		}
			
		else{
			ST1 = H2.getLeft();
			ST2 = H2.getRight();
			ST3 = H1;
		}
			//make a case for both MtHeaps
		//make a case for MtHeap in left
		// make a case for MtHeap in right
		
			
		//comparing heights
		if(ST1.height() >= ST2.height() && ST1.height() >= ST3.height()){
			return new DataHeap(newRoot, ST1, merge(ST2,ST3));
		}
		else if(ST2.height() >= ST1.height() && ST2.height() >= ST3.height()){
			return new DataHeap(newRoot, ST2, merge(ST1, ST3));
		}
		else{//ST3 is the largest
			return new DataHeap(newRoot, ST3, merge(ST1,ST2));
		}
			
	}
	
	// returns heap containing all existing elements except the given element
	public IHeap removeMinElt(){
		if (this.left.findMinElt() < this.right.findMinElt())
			//newroot = this.left.findMinElt();
			return this.merge(this.left.getRight(), this.left.getLeft());
		else
			//newroot = this.right.findMinElt()
			return this.merge(this.right.getRight(), this.right.getLeft());
	}
	
	// returns the height of the heap
	public int height(){
		if (this.left.height() >= this.right.height())
			return this.left.height() + 1;
		else
			return this.right.height() + 1;
		
	}
	
	
}