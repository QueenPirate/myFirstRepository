package bpm.training.Setup.client.dialog;

import gwt.material.design.client.ui.MaterialDatePicker;
import gwt.material.design.client.ui.MaterialListBox;
import gwt.material.design.client.ui.MaterialLoader;
import gwt.material.design.client.ui.MaterialModal;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialToast;

import java.util.Date;

import bpm.training.Setup.client.IUserService;
import bpm.training.Setup.shared.User;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;


public class RegistrationDialog extends Composite {

	private static RegistrationDialogUiBinder uiBinder = GWT
			.create(RegistrationDialogUiBinder.class);

	interface RegistrationDialogUiBinder extends
			UiBinder<Widget, RegistrationDialog> {
	}

	@UiField MaterialTextBox lblFirstName, lblLastName, lblEmailAdd, lblFirstPassword, lblSecondPassword;
	@UiField MaterialDatePicker dpBirthDate;
	@UiField MaterialListBox lbCountry;
	
	private Date tempBDay;
	private int userAge;
	
	public RegistrationDialog() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	
	@SuppressWarnings("deprecation")
	@UiHandler("btnAgree")
	void onActionButton(ClickEvent e){
		
		
		String firstTempLength = lblFirstPassword.getText();
		String secondTempLength = lblSecondPassword.getText();
		int firstPassLength = firstTempLength.length();
		int secondPassLength = secondTempLength.length();
		tempBDay = dpBirthDate.getDate();
		
	
		if(lblFirstName.getText() == "") {
			lblFirstName.setError("Your first name is required.");
		}else{
			lblFirstName.setSuccess(" ");
		}
		
		if(lblLastName.getText().isEmpty()){
			lblLastName.setError("Your last name is required.");
		}else{
			lblLastName.setSuccess(" ");
		}
		
		if(lblEmailAdd.getText().isEmpty()){
			lblEmailAdd.setError("Your email address is required.");
		}else{
		
			if(lblEmailAdd.getText().contains("@")){
				lblEmailAdd.setSuccess(" ");
			}else{
				lblEmailAdd.setError("Invalid Email Address");
			}
		}

		if(lblFirstPassword.getText() == ""){
			lblFirstPassword.setError("Please create a password");
			lblSecondPassword.setError("Please create a password");
		}else{
			if(firstPassLength < 6 && secondPassLength < 6 ){
				lblFirstPassword.setError("Password should contain more than six characters");
				lblSecondPassword.setError("Password should contain more than six characters");
			}else{
				if(lblFirstPassword.getText() != lblSecondPassword.getText()){
					lblFirstPassword.setError("Password not match");
					lblSecondPassword.setError("Password not match");
				}else{
					lblFirstPassword.setSuccess(" ");
					lblSecondPassword.setSuccess(" ");
				}
			}
		}
		
		if(dpBirthDate.getDate() == null){
			dpBirthDate.setError("Provide your birthday");
		}else{
			userAge = getAge(tempBDay.getYear() + 1900, tempBDay.getMonth() + 1, tempBDay.getDate());
			if(userAge < 18){
				dpBirthDate.setError("Must be at least 18");
			}else{
				dpBirthDate.setSuccess(" ");
			}
		}
		User user = new User(lblFirstName.getText(), lblLastName.getText(), lblEmailAdd.getText(), dpBirthDate.getDate(), lbCountry.getItemText(lbCountry.getSelectedIndex()), lblFirstPassword.getText());
		
		MaterialLoader.showLoading(true);
		IUserService.Connect.getService().createUser(user, new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) {
				MaterialToast.alert("Success");
				MaterialModal.closeModal();
				MaterialLoader.showLoading(false);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				MaterialLoader.showLoading(false);
				MaterialToast.alert(caught.getMessage() );
			}
			
			
		});
		
		
	}
	
	@UiHandler("btnDisagree")
	void onClick(ClickEvent e){
		
		MaterialModal.closeModal();
		
	}
	
	@SuppressWarnings("deprecation")
	public static int getAge(int year, int month, int day) {
	    Date now = new Date();
	    int nowMonth = now.getMonth()+1;
	    int nowYear = now.getYear()+1900;
	    int result = nowYear - year;

	    if (month > nowMonth) {
	        result--;
	    }
	    else if (month == nowMonth) {
	        int nowDay = now.getDate();

	        if (day > nowDay) {
	            result--;
	        }
	    }
	    return result;
	}
	
}
