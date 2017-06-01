
class LocatedAbove implements ISelect {
	
	AbsAttraction a1;
	int street;
	
	public LocatedAbove(int street){
		this.street = street;
	}
	
	public boolean chooseRated(IRated rated) {
		return rated.isStreetAbove(street);
	}

	
	public boolean chooseAdmissable(IAttraction attraction) {
		return attraction.isStreetAbove(street);
	}


}
