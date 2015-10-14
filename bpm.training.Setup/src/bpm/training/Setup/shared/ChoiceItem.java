package bpm.training.Setup.shared;

import gwt.material.design.client.ui.MaterialRadioButton;

public class ChoiceItem extends MaterialRadioButton {
	
	private int index;

	public ChoiceItem(int index) {
		super();
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	

}
