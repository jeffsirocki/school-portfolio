package jasirocki;

import java.awt.Dimension;

import heineman.klondike.BuildablePileController;
import heineman.klondike.FoundationController;
import ks.common.controller.SolitaireMouseMotionAdapter;
import ks.common.games.Solitaire;
import ks.common.games.SolitaireUndoAdapter;
import ks.common.model.Card;
import ks.common.model.Column;
import ks.common.model.Deck;
import ks.common.model.MultiDeck;
import ks.common.model.Pile;
import ks.common.view.CardImages;
import ks.common.view.ColumnView;
import ks.common.view.DeckView;
import ks.common.view.IntegerView;
import ks.common.view.PileView;
import ks.launcher.Main;

/**
 * Forty Thieves variation of Solitaire
 * - Uses SolitairePluginTutorial
 * 
 * @author jasirocki
 */
public class FortyThieves extends Solitaire {

	MultiDeck deck;
	Pile waste;
	Pile foundation[] = new Pile[8];
	Column tableau[] = new Column[10];
	
	DeckView deckView;
	PileView wasteView;
	PileView foundationViews[] = new PileView[8];
	ColumnView tableauViews[] = new ColumnView[10];
	
	IntegerView scoreView;
	IntegerView numLeftView;
	
	/**
	 * FortyThieves constructor comment
	 */
	public FortyThieves() {
		super();
	}
	
	
	@Override
	public String getName() {
		return "jasirocki-FortyThieves";
	}

	@Override
	public boolean hasWon() {
		// TODO Auto-generated method stub
		return getScore().getValue() == 104;
	}

	@Override
	public void initialize() {
		// initialize model
			initializeModel(getSeed());
			initializeView();
			initializeControllers();
			
	// prepare game by dealing facedown cards to all, and 4 face up for each column
			for (int tableauNum=0; tableauNum < 10; tableauNum++) {
				for (int num = 0; num < 4; num++) {
					Card c = deck.get();
					
					c.setFaceUp(true);
					tableau[tableauNum].add(c);
				}
			}
			
			updateNumberCardsLeft(-40);
	}

	/**
	 * Initialize Controllers
	 */
	private void initializeControllers() {
		deckView.setMouseAdapter(new FortyThievesDeckController(this, deck, waste));
		deckView.setMouseMotionAdapter (new SolitaireMouseMotionAdapter(this));
		deckView.setUndoAdapter (new SolitaireUndoAdapter(this));
		
		wasteView.setMouseAdapter(new FortyThievesWastePileController (this, wasteView));
		wasteView.setMouseMotionAdapter (new SolitaireMouseMotionAdapter(this));
		wasteView.setUndoAdapter (new SolitaireUndoAdapter(this));
		
		// Now for each Foundation.
		for (int i = 0; i < 8; i++) {
			foundationViews[i].setMouseAdapter (new FortyThievesFoundationController (this, foundationViews[i]));
			foundationViews[i].setMouseMotionAdapter (new SolitaireMouseMotionAdapter (this));
			foundationViews[i].setUndoAdapter (new SolitaireUndoAdapter(this));
		}
		
		
		// Now for each Tableau.
		for (int i = 0; i < 10; i++) {
			tableauViews[i].setMouseAdapter (new FortyThievesTableauController (this, tableauViews[i]));
			tableauViews[i].setMouseMotionAdapter (new SolitaireMouseMotionAdapter (this));
			tableauViews[i].setUndoAdapter (new SolitaireUndoAdapter(this));
		}
		
		
		
	}

	/**
	 * Initialize views
	 */
	private void initializeView() {
		CardImages ci = getCardImages();

		// MultiDeck Deck
		deckView = new DeckView (deck);
		deckView.setBounds (20, 60+ci.getHeight(), ci.getWidth(), ci.getHeight());
		container.addWidget (deckView);
		
		// Waste Pile
		wasteView = new PileView (waste);
		wasteView.setBounds (40 + ci.getWidth(), 60+ci.getHeight(), ci.getWidth(), ci.getHeight());
		container.addWidget (wasteView);
		
		
		// Foundation Piles
		for( int i = 0; i < 8; i++ ) {
			foundationViews[i] = new PileView(foundation[i]);
			foundationViews[i].setBounds((20+20*i) + i*ci.getWidth(), 20, ci.getWidth(), ci.getHeight());
			container.addWidget (foundationViews[i]);
		}
		
		//Tableau Columns
		for( int i = 0; i < 10; i++ ) {
			tableauViews[i] = new ColumnView(tableau[i]);
			tableauViews[i].setBounds((20+20*i) + i*ci.getWidth(), 80+2*ci.getHeight(), ci.getWidth(), 13*ci.getHeight());
			container.addWidget (tableauViews[i]);
		}
		
		
		// Score View
		scoreView = new IntegerView (getScore());
		scoreView.setBounds (20*9+9*ci.getWidth(), 20, 160, 60);
		container.addWidget (scoreView);

		// Number Left
		numLeftView = new IntegerView (getNumLeft());
		numLeftView.setFontSize (14);
		numLeftView.setBounds (20 + ci.getWidth()/4, 40+ci.getHeight(), ci.getWidth(), 20);
		container.addWidget (numLeftView);
		
	}

	/**
	 * Initialize Model entities
	 * Uses two decks
	 */
	private void initializeModel(int seed) {
		
		// Two Decks
		deck = new MultiDeck("d", 2);
		deck.create(seed);
		model.addElement(deck);   // add to our model (as defined within our superclass).
		
		// Waste Pile
		waste = new Pile();
		model.addElement(waste);
		
		// 8 Foundation Piles
		for(int i = 0; i < 8; i++) {
			foundation[i] = new Pile ("foundation" + i);
			model.addElement(foundation[i]);
		}
		
		// 10 Tableau Piles
		for(int i = 0; i < 10; i++) {
			tableau[i] = new Column("tableau" + i);
			model.addElement(tableau[i]);
		}
		
		// Prepare game
		updateScore(0);
		updateNumberCardsLeft(104);
	}
	
	

	// Override size to fit game
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(1000, 635);
	}


	/** Code to launch solitaire variation. */
	public static void main (String []args) {
		// Seed is to ensure we get the same initial cards every time.
		// Here the seed is to "order by suit."
		Main.generateWindow(new FortyThieves(), Deck.OrderBySuit);
	}
}

