interface IHeap{
// returns heap containing all existing elements and the given element
public IHeap addElt(int data);
// returns the smallest element in the heap
public int findMinElt();
//returns a heaps left subtree
public IHeap getLeft();
//returns a heaps right subtree
public IHeap getRight();
// takes two heaps and merges them
public IHeap merge(IHeap H1, IHeap H2);
// returns heap containing all existing elements except the given element
public IHeap removeMinElt();
// returns the height of the heap
public int height();


}