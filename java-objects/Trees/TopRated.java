
class TopRated implements ISelect {
	
	
//selects an attraction if it is highly rated
	

	
	public boolean chooseRated(IRated rated) {
		
		return (rated.highlyRated() == true);
	}

	
	public boolean chooseAdmissable(IAttraction attraction) {
		
		return false;
	}




}
