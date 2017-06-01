package ebc.controller;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ebc.model.Model;
import ebc.model.Value;
import ebc.view.IModelUpdated;
import ebc.view.DrawingCanvas;

/**
 * Controller to manage access to a Model (width x height) using mouse controls.
 */
public class BoxController extends MouseAdapter {
	/** Where mouse was first pressed. */
	Point start = null;
	
	/** Where mouse was dragged or released. */
	Point end = null;
	
	/** Model that we are responsible for controlling. */
	Model model;
	
	/** Entity to contact when changes in model. */
	IModelUpdated view;

	/** React to changes in model by updating given entity. */
	public BoxController (Model m, IModelUpdated view) {
		this.model = m;
		this.view = view;
	}
	
	/**
	 * Deal with user events by remembering first start point and subtracting
	 * differences (along y-coordinate for height and x-coordinate for width).
	 * 
	 * Note how this method can be tested because it doesn't depend on user
	 * interaction.
	 */
	void react() {
		int newH = end.x - DrawingCanvas.OffsetX;
		int newV = end.y - DrawingCanvas.OffsetY;
		
		// update the model
		int deltaH = newH - model.getWidth().getValue();
		
		if (deltaH < 0) {
			decrementCount (model.getWidth(), -deltaH);
		} else if (deltaH > 0){
			incrementCount (model.getWidth(), deltaH);
		}
		
		int deltaV = newV - model.getHeight().getValue();
		if (deltaV < 0) {
			decrementCount (model.getHeight(), -deltaV);
		} else if (deltaV > 0 ){
			incrementCount (model.getHeight(), deltaV);
		}
		
	}
	
	/** Increment value by a fixed count. */
	void incrementCount (Value value, int ct) {
		for (int i = 0; i < ct; i++) {
			if (value.getValue() != value.getMaximum()) {
				value.increment();
			}

			view.modelChanged();
		}
	}
	
	/** Decrement value by a fixed count. */
	void decrementCount (Value value, int ct) {
		for (int i = 0; i < ct; i++) {
			if (value.getValue() != value.getMinimum()) {
				value.decrement();
			}

			view.modelChanged();
		}
	}

	public void mouseClicked(MouseEvent me) {
		System.out.println("Clicked");
	}
	
	/** 
	 * As mouse is dragged, act upon it. You want to have such graphical event
	 * handlers be as small as possible to make the logic testable.S 
	 */
	public void mouseDragged(MouseEvent me) {
		System.out.println("Drag");
		end = me.getPoint();
		react();
	}

	/**
	 * Initiate behavior with first mouse event. You want to have such graphical event
	 * handlers be as small as possible to make the logic testable.S 
	 */
	public void mousePressed(MouseEvent me) {
		System.out.println("Press");
		start = end = me.getPoint();
		react();
	}

	/** 
	 * Act on final mouse event and reset for next time. You want to have such graphical event
	 * handlers be as small as possible to make the logic testable.S 
	 */
	public void mouseReleased(MouseEvent me) {
		System.out.println("Release");
		end = me.getPoint();
		react();
		start = end = null;
	}
}
