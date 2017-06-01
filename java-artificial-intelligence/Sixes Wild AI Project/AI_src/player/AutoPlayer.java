package player;

import gameModes.elimination.EliminationGame;
import gameModes.elimination.EliminationSquare;
import gameModes.release.ReleaseGame;
import gameModes.release.ReleaseSquare;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;

import player.model.AddToSixMove;
import player.model.ShuffleMove;
import player.view.GameView;
import common.model.AbstractMove;
import common.model.Board;
import common.model.Selection;

public class AutoPlayer {
	
	public static class initPlayerThread extends Thread{
		
		public void run()                       
        {              
			allMoves = getAllSelections(6);
			initComplete = true;
        }
	}
	
	static Collection<Sim_Selection> allMoves = null;
	
	static boolean initStarted=false;
	static boolean initComplete=false;
	
	public GameView gameView;
	public Board realBoard;
	
	public static void init(){
		if(!initStarted){
			initStarted=true;
			initPlayerThread thread = new initPlayerThread();
			thread.start();
		}
	}

	public AutoPlayer(GameView gameView) {
		this.gameView = gameView;
		this.realBoard = gameView.getBoardView().getBoard();
	}
	
	public AbstractMove selectMove(){
		//if best add to six returns zero
		long startTime = System.nanoTime();
		AddToSixMove suggestedMove = bestAddToSix();
		if(suggestedMove == null){
			long endTime = System.nanoTime();
			System.out.println((endTime-startTime));
			return new ShuffleMove(realBoard);
		}
		else{
			long endTime = System.nanoTime();
			System.out.println((endTime-startTime));
			return suggestedMove;
		}
	}
	
	public AddToSixMove bestAddToSix(){
		Sim_Selection best = null;
		double bestEval = -1;
		Board testBoard = null;
		
		if(!initStarted){
			init();
		}
		
		while(initComplete==false){
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for(Sim_Selection s:allMoves){
			boolean valid = true;
			check:
			for(Point p:s){
				if(p.x>=realBoard.getSize()||p.y>=realBoard.getSize()){
					valid=false;
					break check;
				}
			}
			if(valid){
				testBoard = realBoard.makeCopy();
				Selection test = s.applyTo(testBoard);
				AddToSixMove m = new AddToSixMove(test);
				//If the Move is valid, and the score of the move is better than the best, do the swap
				if(gameView.getTheGame() instanceof EliminationGame && m.isValid()){
					double rawscore = gameView.getTheGame().evaluateMove(m);
					boolean includesElimination = false;
					EliminationGame g = (EliminationGame)(gameView.getTheGame());
					int countUnmark = 0;
					for(Point p: s){
						if((realBoard.getSquare(p) instanceof EliminationSquare)){
							EliminationSquare sq = (EliminationSquare) realBoard.getSquare(p);
							includesElimination = includesElimination || !sq.isMarked();
							if(!sq.isMarked()){
								countUnmark++;
							}
						}
						if(g.countUnmarked() == 0){
							includesElimination = true;
						}
					}
					if(!includesElimination){
						rawscore = -1;
					}
					if((rawscore>bestEval)){
						best = s;
						bestEval = gameView.getTheGame().evaluateMove(m)*countUnmark;
					}
				}
				else if(gameView.getTheGame() instanceof ReleaseGame && m.isValid()){
					double rawscore = gameView.getTheGame().evaluateMove(m);
					boolean includesRelease = true; //TODO
					for(Point p: s){
						//TODO: includesRelease = includesRelease || (realBoard.getSquare(p) instanceof ReleaseSquare);
					}
					if(!includesRelease){
						rawscore = -1;
					}
					if((rawscore>bestEval)){
						best = s;
						bestEval = gameView.getTheGame().evaluateMove(m);
					}
				}
				else{
					if(m.isValid()&&(best==null||gameView.getTheGame().evaluateMove(m)>bestEval)){
						best = s;
						bestEval = gameView.getTheGame().evaluateMove(m);
					}
				}
			}
		}
		if(best != null){
			return new AddToSixMove(best.applyTo(realBoard));
		}
		else{
			return null;
		}
	}
	
	public static Collection<Sim_Selection> getAllSelections(int size){
		ArrayList<Sim_Selection> output = new ArrayList<Sim_Selection>();
		
		for(int c=0;c<9;c++){
			for(int r=0;r<9;r++){
				if(size>=1)
					output.add(new Sim_Selection(new Point(r,c)));
			}
		}
		
		for(int i=0;i<output.size();i++){
			if(output.get(i).size()<size){
				Collection<Sim_Selection> c = expand(output.get(i));
				for(Sim_Selection s:c){
					if(!output.contains(s)){
						output.add(s);
					}
				}
			}
		}
		
		return output;
	}
	
	public static Collection<Sim_Selection> expand(Sim_Selection s){
		ArrayList<Sim_Selection> output = new ArrayList<Sim_Selection>();
		ArrayList<Point> frontier = new ArrayList<Point>();
		
		for(Point point:s){
			
			for(int i=0;i<4;i++){
				Point p;
				if(i==0)
					p=new Point((int)point.getX()+1,(int)point.getY());
				else if(i==1)
					p=new Point((int)point.getX()-1,(int)point.getY());
				else if(i==2)
					p=new Point((int)point.getX(),(int)point.getY()+1);
				else
					p=new Point((int)point.getX(),(int)point.getY()-1);
				
				if(p.x>=0&&p.x<9&&p.y>=0&&p.y<9){
					if(!s.contains(p)){
						frontier.add(p);
					}
				}
			}
		}
		for(Point p:frontier){
			Sim_Selection select=new Sim_Selection(s.points);
			select.add(p);
			output.add(select);
		}
		
		return output;
	}
	
	public void pushMove(AbstractMove m){
		gameView.getTheGame().doMove(m);
	}

	public void playAuto(int delayMillis) {
		AbstractMove m=selectMove();
		gameView.getBoardView().paintImmediately(0, 0, gameView.getBoardView().getWidth(), gameView.getBoardView().getHeight());
		try {
			Thread.sleep(delayMillis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pushMove(m);
	}
	
	

}
