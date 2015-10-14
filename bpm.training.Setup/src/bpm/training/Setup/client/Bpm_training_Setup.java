package bpm.training.Setup.client;

import bpm.training.Setup.client.page.LoginPage;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Bpm_training_Setup implements EntryPoint {

	@Override
	public void onModuleLoad() {
		// TODO Auto-generated method stub
		
		RootPanel.get().add(new  LoginPage());
	}
	
}
