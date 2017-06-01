

abstract class AbsAttraction implements IAttraction {
String name;
Location loc;

AbsAttraction(String name, Location loc ){
	this.name = name;
	this.loc = loc;
	
	}
	
	public boolean isStreetAbove(int street2){
		if (this.loc.street > street2)
			return true;
		else
			return false;
	}
	
	
}
