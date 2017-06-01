package common.view;

import static org.junit.Assert.*;
import gameModes.elimination.EliminationSquare;
import gameModes.release.ReleaseSquare;
import gameModes.release.StartAsSixSquare;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import common.model.AbstractSquare;
import common.model.Square;

public class TestDrawer {
	
	ArrayList<AbstractSquareDrawer<?>> d=new ArrayList<AbstractSquareDrawer<?>>();

	@Before
	public void setUp() throws Exception {
		Square s=new Square(2,2);
		d.add(s.getDrawer());
		EliminationSquare s2=new EliminationSquare(3,2);
		d.add(s2.getDrawer());
		ReleaseSquare s3=new ReleaseSquare(2,1);
		d.add(s3.getDrawer());
		StartAsSixSquare s4=new StartAsSixSquare(1,1);
		s4.setFillFrom((AbstractSquare)s2);
		d.add(s4.getDrawer());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		
		JFrame f=new JFrame();
		f.setVisible(true);
		Graphics g=f.getGraphics();
		
		int[] p=d.get(0).getPoint(0, 0, -5, 315);
		assertTrue(p[0]<0);
		assertTrue(p[1]<0);
		
		for(AbstractSquareDrawer<?> draw:d){
			draw.setBounds(10,10);
			draw.drawOn(g);
			draw.drawReference(g);
		}
	}

}
