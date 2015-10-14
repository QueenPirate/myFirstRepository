package bpm.training.Setup.client.card;

import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialRadioButton;
import gwt.material.design.client.ui.MaterialRow;
import bpm.training.Setup.shared.Category;
import bpm.training.Setup.shared.Choice;
import bpm.training.Setup.shared.ChoiceItem;
import bpm.training.Setup.shared.Question;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class QuestionCard extends Composite {

	private static QuestionCardUiBinder uiBinder = GWT
			.create(QuestionCardUiBinder.class);

	interface QuestionCardUiBinder extends UiBinder<Widget, QuestionCard> {
	}
	
	@UiField MaterialLink lblTitle;
	@UiField MaterialColumn colChoices;
	
	private Category cat;
	private int i = 0;
	private double total = 0;
	private Question question;
	private CategoryCard catCard;

	public QuestionCard(final Question question, Category cat, final CategoryCard catCard) {
		initWidget(uiBinder.createAndBindUi(this));
		this.cat = cat;
		this.setCatCard(catCard);
		this.setQuestion(question);
		lblTitle.setText(question.getQuestion());
		lblTitle.addStyleName(cat.getColor());
		lblTitle.setTextColor(cat.gettxtColor());
		
		
		for(Choice s : question.getChoices()){
			
			String choice;
			choice  = s.getChoice();
			MaterialRow row = new MaterialRow();
			final MaterialRadioButton rb = new MaterialRadioButton("Group");
			row.add(rb);
			rb.setText(choice);
			
			final ChoiceItem item = new ChoiceItem(i);
			item.setIndex(i);
			colChoices.add(row);
			

			rb.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					catCard.setCurrentQuestion(QuestionCard.this);
					catCard.setSelectedChoice(item.getIndex());
				}
			});
			
			i++;
		}
		
	}

	public void check( int indexSelectedAnswer, Question question) {		
		
		total = 0;
		total = getCat().getTotalScore();
		if(indexSelectedAnswer == question.getAnswer()){
			total = total + question.getPoint();
		}
		
		getCat().setTotalScore(total);
		
	}

	public Category getCat() {
		return cat;
	}

	public void setCat(Category cat) {
		this.cat = cat;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public CategoryCard getCatCard() {
		return catCard;
	}

	public void setCatCard(CategoryCard catCard) {
		this.catCard = catCard;
	}
	
	
}
