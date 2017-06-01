
interface ISelect {
	//returns true if a given item meets the criteria for selection
	boolean chooseRated(IRated rated);
	
	boolean chooseAdmissable(IAttraction attraction);
}
