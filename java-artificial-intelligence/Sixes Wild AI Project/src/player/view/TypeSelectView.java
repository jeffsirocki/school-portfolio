package player.view;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import common.model.AbstractLevel;
import common.model.LevelList;
import common.view.IUpdateFields;

/** View of tab container for a level type.
 * @author Jmmckinney
 */
public class TypeSelectView extends JPanel implements IUpdateFields{
	JTabbedPane tabbedPane;
	LevelList allLevels;
	
	public TypeSelectView() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Righteous", Font.PLAIN, 13));
		add(tabbedPane);
		
		addComponentListener(new ComponentAdapter()
		{
			public void componentResized(ComponentEvent arg0) {
				int selected = getSelectedTab();
				initialize(allLevels);
				setSelectedTab(selected);
			}
		});
	}

	/**
	 * Initialize the contents and parameters of the Level Tabs
	 * @param allLevels
	 */
	public void initialize(LevelList allLevels)
	{
		this.removeAll();
		
		this.allLevels = allLevels;
		
		if(allLevels == null || allLevels.countTypes() == 0){
			JPanel jpane = new JPanel();
			add(jpane);
			jpane.setLayout(new GridBagLayout());
			JLabel picLabel = new JLabel(new ImageIcon(TypeSelectView.class.getResource("/resource/tile7.png")));
			picLabel.setHorizontalAlignment(SwingConstants.CENTER);
			picLabel.setVerticalAlignment(SwingConstants.CENTER);
			jpane.add(picLabel);
			return;
		}
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Righteous", Font.PLAIN, 13));
		add(tabbedPane);
		
		for(Class<? extends AbstractLevel> levelType:allLevels.getTypes())
		{
			addField(allLevels.getType(levelType));
		}
	}
	
	/** Add a new tab for a level type given a list of its levels.
	 * @param levels
	 */
	public void addField(ArrayList<AbstractLevel> levels)
	{	
		LevelSelectView levelTab = new LevelSelectView();
		levelTab.setBackground(levels.get(0).getColor());
		tabbedPane.addTab(levels.get(0).getType(), null, levelTab, null);
		tabbedPane.setForegroundAt(tabbedPane.getComponentCount() - 1, levels.get(0).getColor());
		
		levelTab.initialize(levels);
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2259527770083580649L;

	/**
	 * Re-initialize the view to match the list of allLevels
	 */
	@Override
	public void updateFields() {
		initialize(this.allLevels);
	}
	
	/** 
	 * Return the tab currently selected and shown.
	 */
	public int getSelectedTab()
	{
		return tabbedPane.getSelectedIndex();
	}
	
	/** 
	 * Set the tab that should be selected and shown.
	 */
	public void setSelectedTab(int selected)
	{
		tabbedPane.setSelectedIndex(selected);
	}
}
