package bpm.training.Setup.client.collapsibleitem;

import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialLoader;
import gwt.material.design.client.ui.MaterialRadioButton;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialToast;

import java.util.ArrayList;
import java.util.List;

import bpm.training.Setup.client.ICategoryService;
import bpm.training.Setup.client.radiobuttonandtextbox.AddChoices;
import bpm.training.Setup.shared.Choice;
import bpm.training.Setup.shared.Question;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class QuestionCollapseItem extends Composite {

	private static QuestionCollapseItemUiBinder uiBinder = GWT
			.create(QuestionCollapseItemUiBinder.class);

	interface QuestionCollapseItemUiBinder extends
			UiBinder<Widget, QuestionCollapseItem> {
	}

	@UiField MaterialIcon btnRemoveQuestion;
	@UiField MaterialColumn colChoices;
	@UiField MaterialTextBox txtboxQuestion, txtboxPoints;
	@UiField MaterialLink lbTitle, lbQuestionID;
	
	private Question question;
	
	/**
	 * For new Question
	 * @param cat
	 */
	public QuestionCollapseItem() {
		initWidget(uiBinder.createAndBindUi(this));
		lbQuestionID.setText("0");
		//lbQuestionID.setVisible(true);
	}
	
	/**
	 * Existing questions
	 * @param cat
	 */
	public QuestionCollapseItem(Question question) {
		initWidget(uiBinder.createAndBindUi(this));
		this.question = question;
		lbTitle.setText(question.getQuestion());
		txtboxQuestion.setText(question.getQuestion());
		txtboxPoints.setText(String.valueOf(question.getPoint()));
		lbQuestionID.setText(String.valueOf(question.getId()));
		
		for(Choice c : question.getChoices())
		{
			colChoices.add(new AddChoices(c, question));
		}
	}

	@UiHandler("btnRemoveQuestion")
	void onClickRemoveQuestion(ClickEvent e){
		if(lbQuestionID.getText() != "0"){
			
			MaterialLoader.showLoading(true);
			ICategoryService.Connect.getService().deleteQuestion(question, new AsyncCallback<Void>() {
				
				@Override
				public void onSuccess(Void result) {
					MaterialLoader.showLoading(false);
					MaterialToast.alert("Question Deleted.");
				}
				
				@Override
				public void onFailure(Throwable caught) {
					MaterialLoader.showLoading(false);
					MaterialToast.alert(caught.getMessage());
				}
			});
		}else{
			this.removeFromParent();
		}
		
	}
	
	@UiHandler("btnAddChoices")
	void onClickAddChoices(ClickEvent e){
		AddChoices addChoices = new AddChoices();
		colChoices.add(addChoices);
	}
	
	
	public Question getQuestion() {
		
		int correct = 0;
	
		for(Widget w : colChoices){
			if(w instanceof AddChoices){
				MaterialRadioButton rb = ((AddChoices) w).getRbCorrectAnswer();
				if(rb.getValue()){
					correct = colChoices.getWidgetIndex(w);
				}
			}
		}
		
		if(question == null){
			return new Question(txtboxQuestion.getText(), correct, Double.parseDouble(txtboxPoints.getText()));
		}else{
			question.setQuestion(txtboxQuestion.getText());
			question.setPoint(Double.parseDouble(txtboxPoints.getText()));
			question.setAnswer(correct);
			getAllChoices();
			return question;
		}
		
	}

	public List<Choice> getAllChoices(){
		List<Choice> choices = new ArrayList<Choice>();
		for(Widget w : colChoices){
			if(w instanceof AddChoices){
				choices.add(((AddChoices) w).getChoice());
			}
		}
		return choices;
	}
	public MaterialColumn getColChoices() {
		return colChoices;
	}
	public void setColChoices(MaterialColumn colChoices) {
		this.colChoices = colChoices;
	}
	
	public String getLbQuestionID() {
		return lbQuestionID.getText();
	}

}
