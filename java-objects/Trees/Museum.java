

class Museum extends AbsAttraction {
	double admission;
	Museum(String name, Location loc, double admission) {
		super(name, loc);
		this.admission = admission;
		
	}
	
	public boolean accept(ISelect s) {
		
		return s.chooseAdmissable(this);
	}

	
	
	

}
