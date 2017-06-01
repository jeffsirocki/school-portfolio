

class Hotel extends AbsAttraction implements IRated {
	int rating;
	Hotel(String name, Location loc, int rating) {
		super(name, loc);
		this.rating = rating;
		
	}

	
	public boolean highlyRated() {
		if(rating >= 4)
			return true;
		else
			return false;
	}
	
	public boolean accept(ISelect s){
		return s.chooseRated(this);
	}

}
