// CS 2102 - Homework 3
// Jeff Sirocki (jasirocki)
// Marco Duran 	(mdduran)

import tester.*;

class Examples{
	Examples(){}
	
	// Examples of heaps
	IHeap empty = new MtHeap();
	
	IHeap level21LL = new DataHeap(6, empty, empty);
	IHeap level21LR = new DataHeap(9, empty, empty);
	IHeap level21RL = new DataHeap(4, empty, empty);
	IHeap level21RR = new DataHeap(8, empty, empty);
	IHeap level11L = new DataHeap(3, level21LL, level21LR);
	IHeap level11R = new DataHeap(2, level21RL, level21RR);
	IHeap level01 = new DataHeap(1, level11L, level11R);
	
	IHeap level22LL = new DataHeap(90, empty, empty);
	IHeap level12L = new DataHeap(30, level22LL, empty);
	IHeap level12R = new DataHeap(20, empty, empty);
	IHeap level02 = new DataHeap(10, level12L, level12R);
	
	IHeap level43RRRR = new DataHeap(64, empty, empty);
	IHeap level33RRR = new DataHeap(16, empty, level43RRRR);
	IHeap level23RL = new DataHeap(8, empty, empty);
	IHeap level23RR = new DataHeap(4, empty, level33RRR);
	IHeap level13L = new DataHeap(3, empty, empty);
	IHeap level13R = new DataHeap(2, level23RL, level23RR);
	IHeap level03 = new DataHeap(1, level13L, level13R);
	
	IHeap level24LL = new DataHeap(8, empty, empty);
	IHeap level24RL = new DataHeap(6, empty, empty);
	IHeap level24RR = new DataHeap(9, empty, empty);
	IHeap level14L = new DataHeap(4, level24LL, empty);
	IHeap level14R = new DataHeap(3, level24RL, level24RR);
	IHeap level04 = new DataHeap(2, level14L, level14R);
	
	IHeap level21XLL = new DataHeap(5, level21LL, level21LR);
	IHeap level11XL = new DataHeap(3, level21XLL, empty);
	
	IHeap level32XLLL = new DataHeap(10, empty, empty);
	IHeap level22XLR = new DataHeap(6, empty, empty);
	IHeap level22XLL = new DataHeap(9, level32XLLL, empty);
	IHeap level12XL = new DataHeap(3, level22XLL, level22XLR);
	
	// Examples of Menu heap
	
	IHeap Lobster = new DataHeap(18, empty, empty);
	IHeap Steak = new DataHeap(20, empty, empty);
	IHeap Ribs = new DataHeap(15, empty, empty);
	IHeap Hamburger = new DataHeap(10, empty, empty);
	IHeap Wings = new DataHeap(6, Steak, Lobster);
	IHeap Nachos = new DataHeap(8, Hamburger, Ribs);
	IHeap Soda = new DataHeap(2, Nachos, Wings);
	
	IHeap ASteak = new DataHeap(20, empty, empty);
	IHeap ALobster = new DataHeap(18, ASteak, empty);
	IHeap ARibs = new DataHeap(15, empty, empty);
	IHeap AHamburger = new DataHeap(10, empty, empty);
	IHeap ANachos = new DataHeap(8, AHamburger, ARibs);
	IHeap AWings = new DataHeap(6, ALobster, ANachos);
	
	
	// Examples of Tourney
	IHeap QuarterFinal4 = new DataHeap(8, empty, empty);
	IHeap QuarterFinal3 = new DataHeap(7, empty, empty);
	IHeap QuarterFinal2 = new DataHeap(6, empty, empty);
	IHeap QuarterFinal1 = new DataHeap(5, empty, empty);
	IHeap SemiFinal2 = new DataHeap(3, QuarterFinal3, QuarterFinal4);
	IHeap SemiFinal1 = new DataHeap(2, QuarterFinal1, QuarterFinal2);
	IHeap Final1 = new DataHeap(1, SemiFinal1, SemiFinal2);
	
	IHeap AQuarterFinal4 = new DataHeap(8, empty, empty);
	IHeap AQuarterFinal3 = new DataHeap(7, empty, empty);
	IHeap AQuarterFinal2 = new DataHeap(6, empty, empty);
	IHeap AQuarterFinal1 = new DataHeap(5, AQuarterFinal2, empty);
	IHeap ASemiFinal2 = new DataHeap(3, QuarterFinal3, QuarterFinal4);
	IHeap ASemiFinal1 = new DataHeap(2, QuarterFinal1, ASemiFinal2);
	
	
	// checkExpects for method addElt(int data);
	boolean test1 (Tester t){
		return t.checkExpect(level01.addElt(0), new DataHeap(0, level01, new MtHeap()));
	}
	
	boolean test2 (Tester t){
		return t.checkExpect(level01.addElt(1), level01);
	}
	
	boolean test3 (Tester t){
		return t.checkExpect(level01.addElt(5), new DataHeap(1, level11XL, level11R));
	}
	
	boolean test4 (Tester t){
		return t.checkExpect(level01.addElt(10), new DataHeap(1, level12XL, level11R));
	}
	
	// checkExpects for method findMinElt();
	boolean test5 (Tester t){
		return t.checkExpect(level01.findMinElt(), 1);
	}
	
	boolean test6 (Tester t){
		return t.checkExpect(empty.findMinElt(), -1);
	}
	
	// checkExpects for method getLeft();
	boolean test7 (Tester t){
		return t.checkExpect(level01.getLeft(), level11L);
	}
	
	boolean test8 (Tester t){
		return t.checkExpect(empty.getLeft(), empty);
	}

	// checkExpects for method getRight();
	boolean test9 (Tester t){
		return t.checkExpect(level01.getRight(), level11R);
	}
	
	boolean test10 (Tester t){
		return t.checkExpect(empty.getRight(), empty);
	}
	
	// checkExpects for method merge(IHeap H1, IHeap H2);
	
	boolean test11 (Tester t){
		return t.checkExpect(empty.merge(level11L, level11R), level04);
	}
	
	boolean test12 (Tester t){
		return t.checkExpect(empty.merge(level01, empty), level01);
	}
	
	boolean test13 (Tester t){
		return t.checkExpect(empty.merge(empty, level01), level01);
	}
	
	boolean test14 (Tester t){
		return t.checkExpect(empty.merge(empty, empty), empty);
	}
	
	// checkExpects for method removeMinElt();
	
	boolean test15 (Tester t){
		return t.checkExpect(level01.removeMinElt(), level04);
	}
	
	boolean test16 (Tester t){
		return t.checkExpect(level23RR.removeMinElt(), level33RRR);
	}
	
	boolean test17 (Tester t){
		return t.checkExpect(level12L.removeMinElt(), level22LL);
	}
	
	boolean test18 (Tester t){
		return t.checkExpect(empty.removeMinElt(), empty);
	}
	
	boolean test19 (Tester t){
		return t.checkExpect(Soda.removeMinElt(), AWings);
	}
	
	boolean test20 (Tester t){
		return t.checkExpect(Final1.removeMinElt(), ASemiFinal1);
	}
	
	// checkExpects for method height();
	boolean test21 (Tester t){
		return t.checkExpect(level01.height(), 3);
	}
	
	boolean test22 (Tester t){
		return t.checkExpect(level02.height(), 3);
	}
	
	boolean test23 (Tester t){
		return t.checkExpect(level03.height(), 5);
	}
	
	boolean test24 (Tester t){
		return t.checkExpect(empty.height(), 0);
	}
		
	
}