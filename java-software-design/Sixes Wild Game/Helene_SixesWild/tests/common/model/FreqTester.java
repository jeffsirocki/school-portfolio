package common.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Enumeration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import common.model.Frequency;
import common.model.FrequencyList;
import common.model.Tile;

public class FreqTester {

	FrequencyList spawn;
	FrequencyList bonus;
	
	@Before
	public void setUp() throws Exception {
		spawn=new FrequencyList(new FrequencyList(new int[] {1,2,3,4,5,6}));
		bonus=new FrequencyList(new FrequencyList(new int[] {1,2,3}));
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testArrayLists(){
		ArrayList<Integer> vals=new ArrayList<Integer>();
		vals.add(1);
		vals.add(3);
		vals.add(5);
		
		FrequencyList list=new FrequencyList(vals);
		
		ArrayList<Frequency> out=list.getList();
		
		for(Frequency f:out){
			assertTrue(vals.contains(f.getValue()));
		}
	}

	@Test
	public void test() {
		
		spawn.setFreq(2, 50);
		assertEquals(50,spawn.getFreq(2),.001);
		
		spawn.setFreq(6, 25);
		assertEquals(25,spawn.getFreq(6),.001);
		
		spawn.setFreq(3, 120);
		assertEquals(100,spawn.getFreq(3),.001);
		
		spawn.setFreq(3, 50);
		assertEquals(50,spawn.getFreq(3),.001);
		
		spawn.setFreq(2, 20);
		assertEquals(50,spawn.getFreq(3),.001);	
		
		spawn.setFreq(4, 10);
		assertEquals(50,spawn.getFreq(3),.001);	
		
		spawn.setFreq(5, 18);
		assertEquals(50,spawn.getFreq(3),.001);
	
		bonus.setFreq(3, 1);
		assertEquals(1,bonus.getFreq(3),.001);
		
		bonus.setFreq(2, 5);
		assertEquals(5,bonus.getFreq(2),.001);
		
		bonus.setFreq(2, 4);
		assertEquals(4,bonus.getFreq(2),.001);
		
		System.out.println(spawn);
		System.out.println(bonus);
		
		RandomSource s=new RandomSource(spawn,bonus);
		
		float vsum=0,bsum=0;
		int n=1000;
		for(int i=0;i<n;i++){
			Tile t=s.makeTile();
			vsum+=t.val;
			bsum+=t.bonus;
		}
		vsum/=n;
		bsum/=n;

		float[] exp=getExpected(spawn,bonus);

		//System.out.println(vsum+","+bsum);
		//System.out.println(exp[0]+","+exp[1]);
		
		assertEquals(exp[0],vsum,.15);
		assertEquals(exp[1],bsum,.15);
		
		FrequencyList copyS=spawn.makeCopy();
		FrequencyList copyB=bonus.makeCopy();
		
		for(int i=1;i<7;i++){
			assertTrue(copyS.getTable().get(i).getFrequency()==spawn.getTable().get(i).getFrequency());
			assertTrue(copyS.getTable().get(i).getPriority()==spawn.getTable().get(i).getPriority());
			assertTrue(copyS.getTable().get(i).getValue()==spawn.getTable().get(i).getValue());
		}
		for(int i=1;i<4;i++){
			assertTrue(copyB.getTable().get(i).getFrequency()==bonus.getTable().get(i).getFrequency());
			assertTrue(copyB.getTable().get(i).getPriority()==bonus.getTable().get(i).getPriority());
			assertTrue(copyB.getTable().get(i).getValue()==bonus.getTable().get(i).getValue());
		}
		
		spawn.setFreq(2, 77);
		
		assertFalse(spawn.getFreq(2)==copyS.getFreq(2));

		assertFalse(spawn.setFreq(0,30));
	}
	
	private float[] getExpected(FrequencyList spawn,FrequencyList bonus){
		float val=0,b=0;
		for(Frequency f:spawn){
			val+=f.getValue()*f.getFrequency()/Frequency.MAX_FREQ;
		}
		for(Frequency f:bonus){
			b+=f.getValue()*f.getFrequency()/Frequency.MAX_FREQ;
		}
		float[] out={val,b};
		return out;
	}
	
}
