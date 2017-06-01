import java.util.LinkedList;

/**
 * 
 * @author Jeff Sirocki, Marco Duran
 * The Network class contains a list of city names 
 * and methods on these collections of strings.
 *
 */
class Network {
	/**
	* LinkedList of city names
	*/
	private LinkedList<String> namesOfCities;
	
	/**
	* Default constructor for Network
	* Sets Network to an empty list of strings.
	*/
	public Network(LinkedList<String> namesOfCities){
		this.namesOfCities = new LinkedList<String>();
	}
	
	/**
	    * Add a city name to the list of cities.
	    *
	    * @param updates namesOfCities field to include the given city.
	    */
	public void addCity(String cityname){
		this.namesOfCities.add(cityname);
	}
	/**
	    * Does Network contain the city?
	    *
	    * @return boolean
	    */
	public boolean containsName(String aname){
		for(String s: namesOfCities){
			return (s == aname);
		}
		return false;
	}
	/**
	    * returns the first city in the LinkedList of cities.
	    *
	    * @return String
	    */
	public String getFirstCity(){
		return this.namesOfCities.getFirst();
	}
}
