package builder.view;

import builder.controllers.BuilderViewReference;

import common.model.FrequencyList;

/**
 * Object to hold bonus frequencies.
 * @author njpanzarino
 * @author tjbennett - windowbuilder
 * @author jasirocki - jdoc
 *
 */
public class BonusFrequencyPanel extends FrequencyPanel {
	
	/**
	 * Each has frequencies.
	 */
	boolean noFreq=false;
	
	/**
	 * Constructor that inherits from FrequencyPanel.
	 */
	public BonusFrequencyPanel() {
		super();
		initialize(new FrequencyList(new int[] {1,2,3}));
//		addField("1");
//		addField("2");
//		addField("3");
	}

	/**
	 * Get panel Name.
	 * @return String
	 */
	@Override
	public String getPanelName() {
		return "Bonus Frequencies";
	}

	/**
	 * Get Label Prefix.
	 * @return String
	 */
	@Override
	public String getLabelPrefix() {
		return "x";
	}

	/**
	 * Update bonus frequency fields.
	 */
	@Override
	public void updateFields() {
		try{
			FrequencyList f=BuilderViewReference.get().getModel().getCurrentLevel().getParams().getBonus();
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
