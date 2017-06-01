package jasirocki;

import java.awt.event.MouseEvent;

import ks.common.model.Column;
import ks.common.model.Card;
import ks.common.model.Move;
import ks.common.model.Pile;
import ks.common.view.ColumnView;
import ks.common.view.CardView;
import ks.common.view.Container;
import ks.common.view.Widget;
import jasirocki.FortyThieves;

/**
 * TableauView Controller
 * - handles(press, release)
 * @author jasirocki
 *
 */
public class FortyThievesTableauController extends java.awt.event.MouseAdapter {

	/** The game that we are partly controlling. */
	protected FortyThieves theGame;

	/** The src ColumnView that initiated the event. */
	protected ColumnView src;
	
	/**
	 * ColumnController constructor comment.
	 */
	public FortyThievesTableauController(FortyThieves theGame, ColumnView cv) {
		super();

		this.theGame = theGame;
		this.src = cv;
	}
	
	
	
	/**
	 * Coordinate reaction to the beginning of a Drag Event.
	 *
	 * @param pv ks.common.view.PileView
	 * @param me java.awt.event.MouseEvent
	 */
	public void mousePressed(MouseEvent me) {

		// The container manages several critical pieces of information; namely, it
		// is responsible for the draggingObject; in our case, this would be a CardView
		// Widget managing the card we are trying to drag between two piles.
		Container c = theGame.getContainer();

		/** Return if there is no card to be chosen. */
		Column theColumn = (Column) src.getModelElement();
		if (theColumn.count() == 0) {
			c.releaseDraggingObject();
			return;
		}
		
		// Get a card to move from the CardView
		// Note that this method will alter the model for CardView if the condition is met.
		CardView cardView = src.getCardViewForTopCard(me);

		// an invalid selection (either all facedown, or not in faceup region)
		if (cardView == null) {
			c.releaseDraggingObject();
			return;
		}

		// If we get here, then the user has indeed clicked on the top card in the PileView and
		// we are able to now move it on the screen at will. For smooth action, the bounds for the
		// cardView widget reflect the original card location on the screen.
		Widget w = c.getActiveDraggingObject();
		if (w != Container.getNothingBeingDragged()) {
			System.err.println ("ColumnController::mousePressed(): Unexpectedly encountered a Dragging Object during a Mouse press.");
			return;
		}

		// Tell container which object is being dragged, and where in that widget the user clicked.
		c.setActiveDraggingObject (cardView, me);

		// Tell container which ColumnView is the source for this drag event.
		c.setDragSource (src);

		// we simply redraw our source pile to avoid flicker,
		// rather than refreshing all widgets...
		src.redraw();
	}
	/**
	 * Coordinate reaction to the completion of a Drag Event.
	 * <p>
	 * A bit of a challenge to construct the appropriate move, because cards
	 * can be dragged both from the WastePile (as a CardView widget) and the 
	 * CardView (as a CardView widget).
	 * <p>
	 * @param me java.awt.event.MouseEvent
	 */
	public void mouseReleased(MouseEvent me) {
		Container c = theGame.getContainer();

		/** Return if there is no card being dragged chosen. */
		Widget draggingWidget = c.getActiveDraggingObject();
		if (draggingWidget == Container.getNothingBeingDragged()) {
			System.err.println ("TableauController::mouseReleased() unexpectedly found nothing being dragged.");
			c.releaseDraggingObject();		
			return;
		}

		/** Recover the from Column OR waste Pile */
		Widget fromWidget = c.getDragSource();
		if (fromWidget == null) {
			System.err.println ("TableauController::mouseReleased(): somehow no dragSource in container.");
			c.releaseDraggingObject();
			return;
		}

		// Determine the To Column
		Column tableau = (Column) src.getModelElement();

		if (fromWidget instanceof ColumnView) {
			// coming from a column
			Column fromColumn = (Column) fromWidget.getModelElement();

			/** Must be the CardView widget being dragged. */
			CardView cardView = (CardView) draggingWidget;
			Card card = (Card) cardView.getModelElement();
			if (card == null) {
				System.err.println ("FoundationController::mouseReleased(): somehow ColumnView model element is null.");
				c.releaseDraggingObject();			
				return;
			}

			// Make new move
			Move m = new TableauTableauMove (fromColumn, card, tableau);

			if (m.doMove (theGame)) {
				// Success
				theGame.pushMove (m);
			} else {
				fromWidget.returnWidget (draggingWidget);
			}
				
			
		} else {
			// Coming from the waste [number of cards being dragged must be one]
			Pile wastePile = (Pile) fromWidget.getModelElement();

			/** Must be the CardView widget being dragged. */
			CardView cardView = (CardView) draggingWidget;
			Card theCard = (Card) cardView.getModelElement();
			if (theCard == null) {
				System.err.println ("FoundationController::mouseReleased(): somehow CardView model element is null.");
				c.releaseDraggingObject();
				return;
			}

			// must use peek() so we don't modify col prematurely
			Move m = new WasteTableauMove (wastePile, theCard, tableau);
			if (m.doMove (theGame)) {
				// Success
				theGame.pushMove (m);
			} else {
				fromWidget.returnWidget (draggingWidget);
			}
		}

		// Ahhhh. Instead of dealing with multiple 'instanceof' difficulty, why don't we allow
		// for multiple controllers to be set on the same widget? Each will be invoked, one
		// at a time, until someone returns TRUE (stating that they are processing the event).
		// Then we have controllers for each MOVE TYPE, not just for each entity. In this way,
		// I wouldn't have to convert the CardView from wastePile into a ColumnView. I would
		// still have to do some sort of instanceOf check, however, to validate: But if the
		// instanceof failed, the controller could safely return and say NOT ME! See! There
		// always is a way to avoid layered if statements in OO.

		// release the dragging object, (this will reset dragSource)
		c.releaseDraggingObject();
		
		// finally repaint
		c.repaint();
	}
	
}
