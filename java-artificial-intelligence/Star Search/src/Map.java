import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Map implements IMap{
	
	private Point start;
	private Point goal;
	public int xMax;
	public int yMax;
	private ArrayList<String[]> map = new ArrayList<String[]>();
	
	/*
	 * Constructor for initial map creation from file
	 */
	public Map()
	{
		
	}
	
	public Map(String filename){
		try {
			this.initialize(filename);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Map(Map toCopy)
	{
		setStart(toCopy.getStart());
		setGoal(toCopy.getGoal());
		setMax(toCopy.xMax, toCopy.yMax);
		this.map = toCopy.getMatrix();
	}
	

	public Point getGoal() {
		
		return this.goal;
	}

	/*
	 * Usage: Initialize the Map
	 */
	public void initialize(String filename) throws FileNotFoundException {
		//Create File Handle
		Scanner input = new Scanner(new File(filename));
		//Scan the File
		this.xMax = 0;
		while (input.hasNextLine()){
            String line = input.nextLine();
            String[] split = line.split("\t");
            if (this.xMax  < split.length) this.xMax = split.length;
            map.add(split);
            this.yMax++;
        }

		//Close Scanner
		input.close();
		
		//Set the Start and Goal Point
		for(int i = 0; i < yMax; i++){
			for(int j = 0; j < xMax; j++){
				if(map.get(i)[j].equals("S")){
					this.start = new Point(j,i);
				}
				else if(map.get(i)[j].equals("G")){ 
					this.goal = new Point(j,i);
				}
			}
		}
	}

	@Override
	public Integer getComplexity(Point point) {
		try{
			return Integer.parseInt(map.get(point.y)[point.x]);
		}
		catch(NumberFormatException ex){
			if(map.get(point.y)[point.x]=="S"||
					map.get(point.y)[point.x]=="G"){
				return 1;
			}
			else if (map.get(point.y)[point.x] == " "){
				return null;
			}
		}
		return null;
	}

	@Override
	public Point getStart() {
		return this.start;
	}

	@Override
	public boolean isGoal(Point point) {
		return (point.x == goal.x && point.y == goal.y);
	}
	
	/*
	 * Usage: Prints out the Map
	 */
	public void printMap(){
		System.out.println("X-Max: " + Integer.toString(xMax));
		System.out.println("Y-Max: " + Integer.toString(yMax));
		System.out.println("Start: " + this.start);
		System.out.println("Goal: " + this.goal);
		for(int i = 0; i < this.yMax; i++){
			for(int j = 0; j < this.xMax; j++){
				System.out.print(map.get(i)[j] + "\t");
			}
			System.out.print("\n");
		}
	}

	@Override
	public IMap copy() {
		Map newMap = new Map();
		
		for(int i = 0; i < this.yMax; i++)
		{
			newMap.addLine(map.get(i));
		}
		
		newMap.setGoal(this.goal);
		newMap.setStart(this.start);
		newMap.setMax(this.xMax, this.yMax);
		
		return newMap;
	}

	private void setStart(Point s) {
		this.start = s;
		
	}

	private void setMax(int x, int y) {
		this.xMax = x;
		this.yMax = y;
	}

	private void setGoal(Point g) {
		this.goal = g;
		
	}

	@Override
	public void setComplexity(Point point, Integer complexity) {
		this.map.get(point.y)[point.x] = complexity.toString();
	}
	
	@Override
	public ArrayList<String[]> getMatrix()
	{
		return this.map;
	}
	
	
	public void addLine(String[] line) {
		this.map.add(line);
	}

	@Override
	public boolean isCliff(Point point) {
		return (point.x<0||point.y<0||
				point.x>=xMax||point.y>=yMax);
	
	}
}

