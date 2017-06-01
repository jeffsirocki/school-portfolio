//HomeWork4
//Jeff Sirocki jasirocki
//Marco Duran mdduran


import tester.*;

class Examples {
	Examples(){}
	
	//Examples of Attractions
	Museum museum1 = new Museum("Art", new Location(7, 75), 5);
	Museum museum2 = new Museum("Music", new Location(7, 65), 5);
	
	Hotel hotel1 = new Hotel("Mariot", new Location(7, 10) , 5);
	Hotel hotel2 = new Hotel("BestWay", new Location(7, 75) , 3);
	
	Restaurant restaurant1 = new Restaurant("Boynton", new Location(7, 10), 4);
	Restaurant restaurant2 = new Restaurant("Sole", new Location(7, 75), 3);
	
	int street2 = 5;
	int street3 = 15;
	
	IBST empty = new MtBST();
	
	IBST hotel01 = new DataBST(hotel1, empty, empty);
	IBST hotel02 = new DataBST(hotel2, empty, empty);
	IBST hotel03 = new DataBST(hotel1, hotel02, empty);
	IBST restaurant01 = new DataBST(restaurant1, empty, empty);
	IBST restaurant02 = new DataBST(restaurant2, empty, empty);
	IBST hotel04 = new DataBST(hotel2, empty, restaurant01);
	IBST hotel06 = new DataBST(hotel1, hotel04, empty);
	IBST museum01 = new DataBST(museum1, empty, empty);
	IBST hotel07 = new DataBST(hotel2, museum01, restaurant01);
	IBST hotel08 = new DataBST(hotel1, hotel07, empty);
	
	ILof mt = new MtLof();
	ILof list1 = new DataLof(hotel1, mt);
	
	boolean testhighlyRated(Tester t){
		return t.checkExpect(restaurant1.highlyRated(), true) &&
				t.checkExpect(restaurant2.highlyRated(), false);
	}
	
	boolean testisStreetAbove(Tester t){
		return t.checkExpect(hotel1.isStreetAbove(25), false) &&
				t.checkExpect(hotel2.isStreetAbove(25), true);
	}
	
	boolean testTopRated(Tester t){
		return t.checkExpect(hotel1.accept(new TopRated()), true) &&
				t.checkExpect(hotel2.accept(new TopRated()), false) &&
				t.checkExpect(restaurant1.accept(new TopRated()), true) &&
				t.checkExpect(restaurant2.accept(new TopRated()), false) &&
				t.checkExpect(museum1.accept(new TopRated()), false) &&
				t.checkExpect(museum2.accept(new TopRated()), false);
	}
	
	boolean testLocatedAbove(Tester t){
		return t.checkExpect(hotel1.accept(new LocatedAbove(50)), false) &&
				t.checkExpect(hotel2.accept(new LocatedAbove(50)), true) &&
				t.checkExpect(restaurant1.accept(new LocatedAbove(50)), false) &&
				t.checkExpect(restaurant2.accept(new LocatedAbove(50)), true) &&
				t.checkExpect(museum1.accept(new LocatedAbove(50)), true) &&
				t.checkExpect(museum2.accept(new LocatedAbove(50)), true);
	}
	
	
	boolean testaddElt(Tester t){
		return t.checkExpect(empty.addElt(hotel1), hotel01) &&
				 t.checkExpect(hotel01.addElt(hotel2), hotel03) &&
				 t.checkExpect(hotel03.addElt(restaurant1), hotel06) &&
				 t.checkExpect(hotel06.addElt(museum1), hotel08);
	}
	
	boolean testcontainsAny(Tester t){
		return t.checkExpect(hotel01.containsAny(new TopRated()), true) &&
				 t.checkExpect(hotel02.containsAny(new TopRated()), false) &&
				 t.checkExpect(restaurant01.containsAny(new TopRated()), true) &&
				 t.checkExpect(restaurant02.containsAny(new TopRated()), false) &&
				 t.checkExpect(museum01.containsAny(new TopRated()), false) &&
				 t.checkExpect(hotel01.containsAny(new LocatedAbove(50)), false) &&
				 t.checkExpect(restaurant01.containsAny(new LocatedAbove(50)), false) &&
				 t.checkExpect(museum01.containsAny(new LocatedAbove(50)), true);
				
	}
	
	boolean testallAre(Tester t){
		return t.checkExpect(hotel08.containsAny(new TopRated()), true) &&
				 t.checkExpect(restaurant01.containsAny(new TopRated()), true) &&
				 t.checkExpect(restaurant02.containsAny(new TopRated()), false) &&
				 t.checkExpect(museum01.containsAny(new TopRated()), false) &&
				 t.checkExpect(hotel01.containsAny(new LocatedAbove(50)), false) &&
				 t.checkExpect(restaurant01.containsAny(new LocatedAbove(50)), false) &&
				 t.checkExpect(museum01.containsAny(new LocatedAbove(50)), true);
				
	}
	
	/*
	boolean testfilter(Tester t){
		return t.checkExpect(hotel01.filter(new TopRated()), list(hotel1)) &&
				t.checkExpect(hotel03.filter(new TopRated()), list(hotel1)) &&
				t.checkExpect(restaurant01.filter(new LocatedAbove()), list(restaurant1)) &&
				t.checkExpect(museum01.filter(new LocatedAbove()), list(museum1));
	}
	*/
}
