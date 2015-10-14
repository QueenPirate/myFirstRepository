package bpm.training.Setup.client.panel;

import gwt.material.design.client.ui.MaterialTextBox;
import bpm.training.Setup.shared.User;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ProfilePanel extends Composite {

	private static ProfilePanelUiBinder uiBinder = GWT
			.create(ProfilePanelUiBinder.class);

	interface ProfilePanelUiBinder extends UiBinder<Widget, ProfilePanel> {
	}
	
	@UiField MaterialTextBox lblFirstName, lblLastI;
	User user;

	public ProfilePanel(User user) {
		initWidget(uiBinder.createAndBindUi(this));
		
		this.user = user;
		lblFirstName.setText(user.getFirstName());
		lblLastI.setText(user.getLastName().charAt(0) + ".");
		
		
	}

}
