package player;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import common.model.AbstractSquare;
import common.model.Board;
import common.model.Selection;

public class Sim_Selection implements Iterable<Point>{

	ArrayList<Point> points = new ArrayList<Point>();

	public Sim_Selection(Point s){
		add(s);
	}
	
	public Sim_Selection(Collection<Point> s){
		points.addAll(s);
	}
	
	public Sim_Selection(Selection s){
		for(AbstractSquare squ:s){
			add(new Point(squ.getRow(),squ.getColumn()));
		}
	}
	
	public Selection applyTo(Board b){
		Selection select = null;
		for(int i=0;i<points.size();i++){
			if(select==null)
				select = new Selection(b.getSquare(points.get(i)));
			else
				select.add(b.getSquare(points.get(i)));
		}
		return select;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sim_Selection other = (Sim_Selection) obj;
		if (points == null) {
			if (other.points != null)
				return false;
		}
		
		return matches(other);
	}

	private boolean matches(Sim_Selection other) {
		ArrayList<Point> temp = new ArrayList<Point>(other.points);
		for(Point p:this.points){
			if(!temp.remove(p))
				return false;
		}
		return temp.size()==0;
	}

	public void add(Point s){
		if(contains(s))
			return;
		
		points.add(s);
	}
	
	public Point get(int i){
		return points.get(i);
	}
	
	public void clear(){
		points.clear();
	}
	

	public boolean contains(Point s){
		return points.contains(s);
	}
	
	@Override
	public Iterator<Point> iterator() {
		return points.iterator();
	}
	
	public int size(){
		return points.size();
	}
}
