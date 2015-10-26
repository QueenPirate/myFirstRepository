package bpm.training.Setup.client.radiobuttonandtextbox;

import gwt.material.design.client.ui.MaterialRadioButton;
import gwt.material.design.client.ui.MaterialTextBox;

import java.util.ArrayList;
import java.util.List;

import bpm.training.Setup.shared.Choice;
import bpm.training.Setup.shared.Question;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class AddChoices extends Composite {

	private static AddChoicesUiBinder uiBinder = GWT
			.create(AddChoicesUiBinder.class);

	interface AddChoicesUiBinder extends UiBinder<Widget, AddChoices> {
	}
	
	@UiField MaterialTextBox txtboxChoice;
	@UiField MaterialRadioButton rbCorrectAnswer;
	
	List<Choice> c = new ArrayList<>();
	private Choice choice;

	public AddChoices() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public AddChoices(Choice choice, Question question) {
		initWidget(uiBinder.createAndBindUi(this));
		this.choice = choice;
		txtboxChoice.setText(choice.getChoice());
		
		for(Choice cc : question.getChoices()){
			c.add(cc);
			if(c.indexOf(choice) ==  question.getAnswer()){
				rbCorrectAnswer.setValue(true);
			}
		}
		
	}
	
	@UiHandler("btnRemoveChoice")
	void onClickRemoveChoice(ClickEvent e){
		this.removeFromParent();
	}

	public Choice getChoice() {
		if(choice != null){
			choice.setChoice(txtboxChoice.getText());
			return choice;
		}else{
			return  new Choice(txtboxChoice.getText());
		}
	}

	public MaterialRadioButton getRbCorrectAnswer() {
		return rbCorrectAnswer;
	}

	
	
	
		
}
