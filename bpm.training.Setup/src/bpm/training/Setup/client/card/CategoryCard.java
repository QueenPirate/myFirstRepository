package bpm.training.Setup.client.card;

import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialImage;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialModal;
import gwt.material.design.client.ui.MaterialModal.TYPE;
import gwt.material.design.client.ui.MaterialPanel;

import java.util.ArrayList;
import java.util.List;

import bpm.training.Setup.client.dialog.AddQuestionDialog;
import bpm.training.Setup.shared.Category;
import bpm.training.Setup.shared.Question;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class CategoryCard extends Composite {

	private static CategoryCardUiBinder uiBinder = GWT
			.create(CategoryCardUiBinder.class);

	interface CategoryCardUiBinder extends UiBinder<Widget, CategoryCard> {
	}
	
	@UiField MaterialImage imgPicture;
	@UiField MaterialLink lblName, lblIcon, lblScore, lblTotalScore, lblAddQuestion;
	@UiField MaterialColumn colCategory;
	@UiField MaterialColumn rowQuestions;
	@UiField MaterialButton btnStart;
	@UiField MaterialPanel pnlScore;

	private Category cat;
	private int counter = 0;
	private int selectedItem = 0;
	private QuestionCard currentQuestion;
	private int selectedChoice;
	private List<Question> question = new ArrayList<>();
	private int questionListLen = 0;
	
	public CategoryCard(Category cat) {
		initWidget(uiBinder.createAndBindUi(this));
		
		this.cat = cat;
		imgPicture.setUrl(cat.getPicture());
		lblName.setText(cat.getName());
		colCategory.setColor(cat.getColor());
		lblName.addStyleName(cat.getColor());
		lblName.setTextColor(cat.gettxtColor());
		
	}
	
	@UiHandler({"imgPicture", "lblName", "lblIcon"})
	void onClick(ClickEvent e){
		
		selectedItem = (int) cat.getId();
		colCategory.addStyleName("category-active");	
		
		
		for(Question q : cat.getQuestions()){
			question.add(q);
		}
		questionListLen = cat.getQuestions().size();
		
	}
	
	@UiHandler("lblAddQuestion")
	void onClickAddQuestion(ClickEvent e){
		MaterialModal.showModal(new AddQuestionDialog(cat), TYPE.FIXED_FOOTER, false);
	}
	
	@UiHandler("btnStart")
	void onClickStart(ClickEvent e){
		
		colCategory.addStyleName("category-start");
		
		showQuestion(0);
	}
	
	public void showQuestion(int index){
		Question current =   question.get(index);
		
		if(selectedItem == question.get(index).getCategoryId()){
			rowQuestions.clear();
			rowQuestions.add(new QuestionCard(current, cat, this));
			
		}
		
	}

	@UiHandler("btnBack")
	void onBack(ClickEvent e){
		colCategory.addStyleName("category-inactive");	
		colCategory.removeStyleName("category-active");
		colCategory.removeStyleName("category-start");
		colCategory.removeStyleName("pop-up-score");
		counter = 0;
		questionListLen = 0;
	}

	@UiHandler("btnNext")
	void onNext(ClickEvent e){
		
		if(questionListLen > counter){
			currentQuestion.check(selectedChoice, currentQuestion.getQuestion());
			//check
			colCategory.removeStyleName("category-start");
			pnlScore.setColor(cat.getColor());
			
			if(selectedChoice == currentQuestion.getQuestion().getAnswer()){
				lblScore.setText("+" + question.get(counter).getPoint());
				lblTotalScore.setText(cat.getTotalScore() + "");
				
			}else{
				lblScore.setText("+0");
				lblTotalScore.setText(cat.getTotalScore() + "");
			}
			
			colCategory.addStyleName("pop-up-score");
			
			
			
			Timer t = new Timer() {
				
				@Override
				public void run() {
					if(questionListLen-1 > counter){
						colCategory.removeStyleName("pop-up-score");
						colCategory.addStyleName("category-start");
						counter ++;
						showQuestion(counter);
					}
				}
			};
			t.schedule(1000);
			
			if(questionListLen-1 == counter){
				
				colCategory.addStyleName("pop-up-score");
				
				Timer tt = new Timer() {
					
					@Override
					public void run() {
						colCategory.removeStyleName("category-start");
						colCategory.addStyleName("category-active");
						btnStart.removeFromParent();
						colCategory.addStyleName("pop-up-score");
						lblScore.setText( cat.getTotalScore() + "");
					}
				};
				tt.schedule(3000);
				
				
				questionListLen = 0;
				counter = 0;
			}
			
			
		}
		
	}

	public QuestionCard getCurrentQuestion() {
		return currentQuestion;
	}

	public void setCurrentQuestion(QuestionCard currentQuestion) {
		this.currentQuestion = currentQuestion;
	}

	public int getSelectedChoice() {
		return selectedChoice;
	}

	public void setSelectedChoice(int selectedChoice) {
		this.selectedChoice = selectedChoice;
	}


}
