package bpm.training.Setup.client.page;

import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialLoader;
import gwt.material.design.client.ui.MaterialModal;
import gwt.material.design.client.ui.MaterialToast;
import gwt.material.design.client.ui.MaterialModal.TYPE;
import gwt.material.design.client.ui.MaterialTextBox;
import bpm.training.Setup.client.IUserService;
import bpm.training.Setup.client.dialog.RegistrationDialog;
import bpm.training.Setup.shared.User;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class LoginPage extends Composite {

	private static LoginPageUiBinder uiBinder = GWT
			.create(LoginPageUiBinder.class);

	interface LoginPageUiBinder extends UiBinder<Widget, LoginPage> {
	}

	public LoginPage() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField MaterialButton btnSignUp;
	@UiField MaterialTextBox txtEmailAdd, txtPassword;
	
	
	@UiHandler("btnSignUp")
	void onClickSignUp(ClickEvent e){
		MaterialModal.showModal(new RegistrationDialog(), TYPE.FIXED_FOOTER, false);
	}
	
	
	@UiHandler("btnLogin")
	void onLogin(ClickEvent e){
		boolean isValid = true;
		
		if(txtEmailAdd.getText().isEmpty() && txtPassword.getText().isEmpty()){
			txtEmailAdd.setError("Your email address is required.");
			txtPassword.setError("Please provide your password");
			isValid = false;
		}else if(txtEmailAdd.getText() != "" && txtPassword.getText().isEmpty()){
			if(txtEmailAdd.getText().contains("@")){
				txtEmailAdd.setSuccess(" ");
				txtPassword.setError("Please provide your password");
				isValid = false;
			}else{
				txtEmailAdd.setError("Invalid Email Address");
				txtPassword.setError("Please provide your password");
				isValid = false;
			}
			
		}else if(txtEmailAdd.getText().isEmpty() && txtPassword.getText() != ""){
			txtEmailAdd.setError("Your email address is required.");
			txtPassword.setSuccess(" ");
			isValid = false;
		}else{
		
			if(txtEmailAdd.getText().contains("@")){
				
				if (isValid) {
					MaterialLoader.showLoading(true);
					IUserService.Connect.getService().getUser(new User(txtEmailAdd.getText(), txtPassword.getText()), new AsyncCallback<User>() {
						
						@Override
						public void onSuccess(User result) {
							// TODO Auto-generated method stub
								MaterialLoader.showLoading(false);
								MaterialToast.alert("Success");
								RootPanel.get().clear();
								RootPanel.get().add(new GamePage(result));
						}
						
						@Override
						public void onFailure(Throwable caught) {
							MaterialLoader.showLoading(false);
							MaterialToast.alert(caught.getMessage());
							
						}
					});
					
				}
			}else{
				txtEmailAdd.setError("Invalid Email Address");
				txtPassword.setSuccess(" ");
				isValid = false;
			}
		}
		
	}
	
	

}
