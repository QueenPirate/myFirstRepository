package bpm.training.Setup.client.panel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class LeaderboardPanel extends Composite {

	private static LeaderboardPanelUiBinder uiBinder = GWT
			.create(LeaderboardPanelUiBinder.class);

	interface LeaderboardPanelUiBinder extends UiBinder<Widget, LeaderboardPanel> {
	}

	public LeaderboardPanel() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
