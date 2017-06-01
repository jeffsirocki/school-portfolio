package player.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import player.controllers.PlayerAppReference;
import player.controllers.SelectLevelController;

import common.model.AbstractLevel;


/** View for displaying levels that the user can select and play.
 * 
 * @author Jmmckinney
 */
public class LevelSelectView extends JPanel{
	private static final long serialVersionUID = 8786264958228991167L;
	ArrayList<JButton> levelButtons;
	ArrayList<JPanel> springPanels;
	int pages;
	
	/**
	 * Create the panel.
	 */
	public LevelSelectView() {
		levelButtons = new ArrayList<JButton>();
	}
	
	/**
	 * Initialize the contents and parameters of the panel.
	 */
	public void initialize(ArrayList<AbstractLevel> levels) {
		Collections.sort(levels);
		//levels.get(0).setUnlocked(true);
		
		springPanels = new ArrayList<JPanel>();
		springPanels.add(new JPanel(new SpringLayout()));
		springPanels.get(0).setBackground(this.getBackground());
		this.setLayout(new CardLayout());
		
		for(AbstractLevel level:levels)
		{
			addField(level);
		}
	}

	/** Add a button for a new level to the view, along with an indication of the amount of stars earned on an unlocked level.
	 * 
	 */
	public void addField(AbstractLevel level) {
		JButton button;
		if(level.isUnlocked()) {
			button = new JButton("" + level.getLevelID());
			button.addMouseListener(new SelectLevelController(level));
			//button.setBackground(new Color(255, 255, 255));
			button.setForeground(Color.BLACK);
			button.setIcon(null);
		}
		else {
			button = new JButton("");
			button.setIcon(new ImageIcon(LevelSelectView.class.getResource("/resource/lock.png")));
			button.setHorizontalAlignment(SwingConstants.CENTER);
		}
		
		if(levelButtons.isEmpty())
		{
			levelButtons.add(button);
			
			levelButtons.get(0).setPreferredSize(new Dimension(50,50));
			springPanels.get(0).add(levelButtons.get(0));
			((SpringLayout) springPanels.get(0).getLayout()).putConstraint(SpringLayout.WEST, levelButtons.get(0), 50, SpringLayout.WEST, PlayerAppReference.get().getFrame().getContentPane());
			((SpringLayout) springPanels.get(0).getLayout()).putConstraint(SpringLayout.NORTH, levelButtons.get(0), 20, SpringLayout.NORTH, PlayerAppReference.get().getFrame().getContentPane());
			add(springPanels.get(0), "0");
			
			if(level.isUnlocked())
			{
				JLabel starLabel = new JLabel("Stars: " + level.getParams().getThresholds().getNumStars(level.getHighscore()));
				starLabel.setForeground(Color.WHITE);
				springPanels.get(0).add(starLabel);
				((SpringLayout) springPanels.get(0).getLayout()).putConstraint(SpringLayout.HORIZONTAL_CENTER, starLabel, 0, SpringLayout.HORIZONTAL_CENTER, button);
				((SpringLayout) springPanels.get(0).getLayout()).putConstraint(SpringLayout.NORTH, starLabel, 0, SpringLayout.SOUTH, button);
			}
			
			pages = 1;
		}
		else
		{
			levelButtons.add(button);
			int i = levelButtons.size() - 1;
			levelButtons.get(i).setPreferredSize(new Dimension(50,50));
			
			if(levelButtons.get(i-1).getLocation().getX() + 190 > PlayerAppReference.get().getFrame().getWidth())
			{
				if(levelButtons.get(i-1).getLocation().getY() + 210 > PlayerAppReference.get().getFrame().getHeight())
				{
					final LevelSelectView levelView = this;
					JButton nextButton = new JButton();
					nextButton.addMouseListener(new MouseAdapter()
					{
						@Override
						public void mousePressed(MouseEvent e)
						{
							((CardLayout)levelView.getLayout()).next(levelView);
						}
					});
					nextButton.setIcon(new ImageIcon(LevelSelectView.class.getResource("/resource/arrowRight.png")));
					nextButton.setPreferredSize(new Dimension(20,36));
					springPanels.get(pages - 1).add(nextButton);
					((SpringLayout) springPanels.get(pages - 1).getLayout()).putConstraint(SpringLayout.EAST, nextButton, PlayerAppReference.get().getFrame().getWidth() - nextButton.getWidth() - 35, SpringLayout.WEST, PlayerAppReference.get().getFrame().getContentPane());
					((SpringLayout) springPanels.get(pages - 1).getLayout()).putConstraint(SpringLayout.NORTH, nextButton, PlayerAppReference.get().getFrame().getHeight()/2-72, SpringLayout.NORTH, PlayerAppReference.get().getFrame().getContentPane());
					
					pages++;
					springPanels.add(new JPanel(new SpringLayout()));
					
					JButton backButton = new JButton();
					backButton.addMouseListener(new MouseAdapter()
					{
						@Override
						public void mousePressed(MouseEvent e)
						{
							((CardLayout)levelView.getLayout()).previous(levelView);
						}
					});
					backButton.setIcon(new ImageIcon(LevelSelectView.class.getResource("/resource/arrowLeft.png")));
					backButton.setPreferredSize(new Dimension(20,36));
					springPanels.get(pages - 1).add(backButton);
					((SpringLayout) springPanels.get(pages - 1).getLayout()).putConstraint(SpringLayout.WEST, backButton, 10, SpringLayout.WEST, PlayerAppReference.get().getFrame().getContentPane());
					((SpringLayout) springPanels.get(pages - 1).getLayout()).putConstraint(SpringLayout.NORTH, backButton, PlayerAppReference.get().getFrame().getHeight()/2-72, SpringLayout.NORTH, PlayerAppReference.get().getFrame().getContentPane());
					
					springPanels.get(pages - 1).setBackground(this.getBackground());
					add(springPanels.get(pages - 1), Integer.toString(pages - 1));
					springPanels.get(pages - 1).add(levelButtons.get(i));
					((SpringLayout) springPanels.get(pages - 1).getLayout()).putConstraint(SpringLayout.WEST, levelButtons.get(i), 50, SpringLayout.WEST, PlayerAppReference.get().getFrame().getContentPane());
					((SpringLayout) springPanels.get(pages - 1).getLayout()).putConstraint(SpringLayout.NORTH, levelButtons.get(i), 20, SpringLayout.NORTH, PlayerAppReference.get().getFrame().getContentPane());
				}
				else
				{	
					springPanels.get(pages - 1).add(levelButtons.get(i));
					((SpringLayout) springPanels.get(pages - 1).getLayout()).putConstraint(SpringLayout.WEST, levelButtons.get(i), 50, SpringLayout.WEST, PlayerAppReference.get().getFrame().getContentPane());
					((SpringLayout) springPanels.get(pages - 1).getLayout()).putConstraint(SpringLayout.NORTH, levelButtons.get(i), 20, SpringLayout.SOUTH, levelButtons.get((int) (i - Math.floor(((PlayerAppReference.get().getFrame().getWidth() - 50)/70)-1))));
				}
			}
			else
			{
				springPanels.get(pages - 1).add(levelButtons.get(i));
				((SpringLayout) springPanels.get(pages - 1).getLayout()).putConstraint(SpringLayout.WEST, levelButtons.get(i), 20, SpringLayout.EAST, levelButtons.get(i-1));
				((SpringLayout) springPanels.get(pages - 1).getLayout()).putConstraint(SpringLayout.NORTH, levelButtons.get(i), 0, SpringLayout.NORTH, levelButtons.get(i-1));
			}
			if(level.isUnlocked())
			{
				JLabel starLabel = new JLabel("Stars: " + level.getParams().getThresholds().getNumStars(level.getHighscore()));
				starLabel.setForeground(Color.WHITE);
				springPanels.get(pages - 1).add(starLabel);
				((SpringLayout) springPanels.get(pages - 1).getLayout()).putConstraint(SpringLayout.HORIZONTAL_CENTER, starLabel, 0, SpringLayout.HORIZONTAL_CENTER, button);
				((SpringLayout) springPanels.get(pages - 1).getLayout()).putConstraint(SpringLayout.NORTH, starLabel, 0, SpringLayout.SOUTH, button);
			}
			
			PlayerAppReference.get().getFrame().setPreferredSize(PlayerAppReference.get().getFrame().getSize());
			PlayerAppReference.get().getFrame().pack();
		}
	}
}
