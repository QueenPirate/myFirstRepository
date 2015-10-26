package bpm.training.Setup.client.dialog;

import gwt.material.design.client.ui.MaterialCollapsible;
import gwt.material.design.client.ui.MaterialLoader;
import gwt.material.design.client.ui.MaterialModal;
import gwt.material.design.client.ui.MaterialToast;

import java.util.ArrayList;
import java.util.List;

import bpm.training.Setup.client.ICategoryService;
import bpm.training.Setup.client.collapsibleitem.QuestionCollapseItem;
import bpm.training.Setup.shared.Category;
import bpm.training.Setup.shared.Question;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * This will show a dialog for definition interface of questions
 * 
 *
 */
public class AddQuestionDialog extends Composite {

	private static AddQuestionDialogUiBinder uiBinder = GWT
			.create(AddQuestionDialogUiBinder.class);

	interface AddQuestionDialogUiBinder extends
			UiBinder<Widget, AddQuestionDialog> {
	}
	
	@UiField MaterialCollapsible collapsibleMain;
	
	Category cat;
	
	public AddQuestionDialog(Category cat){
		initWidget(uiBinder.createAndBindUi(this));
		this.cat = cat;
		for(Question q : cat.getQuestions()){
			collapsibleMain.add(new QuestionCollapseItem(q));
		}
	}
	

	@UiHandler("btnAddAnotherQuestion")
	void onClickAddAnotherQuestion(ClickEvent e){
		QuestionCollapseItem item = new QuestionCollapseItem();
		collapsibleMain.add(item);
		
	}
	
	@UiHandler("btnAdd")
	void onClickAddQuestion(ClickEvent e){
		
		List<Question> questions = new ArrayList<>();
		
		for(Widget w : collapsibleMain){
			QuestionCollapseItem item = (QuestionCollapseItem) w;
			if(item.getLbQuestionID() == "0"){
				Question question = item.getQuestion();
				question.setChoices(item.getAllChoices());
				question.setCategoryId(cat.getId());
				questions.add(question);
			}else{
				questions.add(item.getQuestion());
			}
		}
		MaterialLoader.showLoading(true);
		ICategoryService.Connect.getService().addQuestions(questions, new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				MaterialLoader.showLoading(false);
				MaterialToast.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(Void result) {
				MaterialLoader.showLoading(false);
				MaterialToast.alert("Added Question/s");
				MaterialModal.closeModal();
				
			}
		});
		
		
	}

	@UiHandler("btnClose")
	void onClickCloseQuestion(ClickEvent e){
		MaterialModal.closeModal();
	}
	
}
