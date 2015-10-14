package bpm.training.Setup.client.page;

import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialTextBox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class HomePage extends Composite {

	private static HomePageUiBinder uiBinder = GWT
			.create(HomePageUiBinder.class);

	interface HomePageUiBinder extends UiBinder<Widget, HomePage> {
	}

	/**
	 * UI Field
	 * Private Variables
	 * Constructors
	 * Ui Handlers
	 * Methods
	 * Setters and Getters
	 */
	
	@UiField MaterialTextBox result;
	@UiField MaterialButton btnOne, btnTwo, btnThree, btnFour, btnFive ,btnSix ,btnSeven ,btnEight ,btnNine ,btnZero ,btnClear;
	@UiField MaterialLink logIn;
	
	private double firstNum;
	private double secondNum;
	private String sign;
	
	public HomePage() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiHandler("logIn")
	void onClick(ClickEvent e){
		RootPanel.get().clear();
		RootPanel.get().add(new LoginPage()); 
	}
	
	@UiHandler({"btnOne","btnTwo","btnThree","btnFour","btnFive","btnSix","btnSeven","btnEight", "btnNine","btnZero","btnClear"})
	void onClickOne(ClickEvent e){
		if(e.getSource() == btnOne){
			result.setText(result.getText() + "1");
		}else if(e.getSource() == btnTwo){
			result.setText(result.getText() + "2");
		}else if(e.getSource() == btnThree){
			result.setText(result.getText() + "3");
		}else if(e.getSource() == btnFour){
			result.setText(result.getText() + "4");
		}else if(e.getSource() == btnFive){
			result.setText(result.getText() + "5");
		}else if(e.getSource() == btnSix){
			result.setText(result.getText() + "6");
		}else if(e.getSource() == btnSeven){
			result.setText(result.getText() + "7");
		}else if(e.getSource() == btnEight){
			result.setText(result.getText() + "8");
		}else if(e.getSource() == btnNine){
			result.setText(result.getText() + "9");
		}else if(e.getSource() == btnZero){
			result.setText(result.getText() + "0");
		}else if(e.getSource() == btnClear){
			result.setText("");
		}
		
	}
	
	@UiHandler("btnAdd")
	void onClickAdd(ClickEvent e){
		firstNum = Double.parseDouble(result.getText());
		
		if(result.getText() == ""){
			
		}else{
			sign = "+";
			result.setText("");
		}	
	}
	
	@UiHandler("btnMinus")
	void onClickMinus(ClickEvent e){
		firstNum = Double.parseDouble(result.getText());
		
		if(result.getText() == ""){
			
		}else{
			sign = "-";
			result.setText("");
		}	
	}
	
	@UiHandler("btnMultiply")
	void onClickMultiple(ClickEvent e){
		firstNum = Double.parseDouble(result.getText());
		
		if(result.getText() == ""){
			
		}else{
			sign = "x";
			result.setText("");
		}	
	}
	
	@UiHandler("btnDivide")
	void onClickDivide(ClickEvent e){
		firstNum = Double.parseDouble(result.getText());
		
		if(result.getText() == ""){
			
		}else{
			sign = "/";
			result.setText("");
		}	
	}
	
	@UiHandler("btnEquals")
	void onClickEquals(ClickEvent e){
		secondNum = Double.parseDouble(result.getText());
		double iResult;
		
		switch(sign){
			case "+":
				iResult = firstNum + secondNum;
				result.setText(String.valueOf(iResult));
				break;
			case "-":
				iResult = firstNum - secondNum;
				result.setText(String.valueOf(iResult));
				break;
			case "x":
				iResult = firstNum * secondNum;
				result.setText(String.valueOf(iResult));
				break;
			case "/":
				
				if(secondNum != 0){
					iResult = firstNum / secondNum;
					result.setText(String.valueOf(iResult));
				}else{
					result.setText("Math Error");
				}
				
				break;
		}
	}
	
	@UiHandler("btnTest")
	void onClickTest(ClickEvent e){
		Window.alert("fsdhjflhsd");
		/*RootPanel.get().clear();
		RootPanel.get().add(new TestPage()); */
	}
	
	
}
