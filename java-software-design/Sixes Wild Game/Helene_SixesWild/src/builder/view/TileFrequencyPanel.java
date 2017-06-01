package builder.view;

import builder.controllers.BuilderViewReference;

import common.model.FrequencyList;

/**
 * Panel to set tile frequencies for a level
 * @author npanzarino
 * @author windowbuilder
 * @author jasirocki - jdoc
 *
 */
public class TileFrequencyPanel extends FrequencyPanel {

	/**
	 * No frequencies flag.
	 */
	boolean noFreq=false;
	
	/**
	 * Constructor to initialize frequency list and panel.
	 */
	public TileFrequencyPanel() {
		super();
		initialize(new FrequencyList(new int[] {1,2,3,4,5,6}));
//		addField("1");
//		addField("2");
//		addField("3");
//		addField("4");
//		addField("5");
//		addField("6");
	}

	/**
	 * Get panel name.
	 */
	@Override
	public String getPanelName() {
		return "Tile Spawn Frequencies";
	}

	/**
	 * Get label prefixes.
	 */
	@Override
	public String getLabelPrefix() {
		return "";
	}

	/**
	 * Update fields.
	 */
	@Override
	public void updateFields() {
		try{
			FrequencyList f=BuilderViewReference.get().getModel().getCurrentLevel().getParams().getSpawn();
			if(freqs==f){
				super.updateFields();
			}
			else
				initialize(f);
			noFreq=false;
		}catch(Exception e){
			if(noFreq==false){
				//System.err.println("Could not find spawn frequencies");
				freqs=null;
				noFreq=true;
				clear();
			}
			super.updateFields();
		}
	}

}
