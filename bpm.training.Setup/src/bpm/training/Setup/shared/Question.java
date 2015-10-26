package bpm.training.Setup.shared;

import java.io.Serializable;
import java.util.List;

public class Question implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long id;
	private String question;
	private List<Choice> choices;
	private int answer;
	private long categoryId;
	private double point;
	
	public Question() {
		// TODO Auto-generated constructor stub
	}

	
	
	public Question(String question,int answer, double point) {
		super();
		this.question = question;
		this.answer = answer;
		this.point = point;
	}



	public Question(String question, int answer,
			long categoryId, double point) {
		super();
		this.question = question;
		this.answer = answer;
		this.categoryId = categoryId;
		this.point = point;
	}



	public Question(String question, List<Choice> choices, int answer, long categoryId, double point) {
		super();
		this.question = question;
		this.choices = choices;
		this.answer = answer;
		this.categoryId = categoryId;
		this.point = point;
	}

	public Question(long id, String question, int answer, long categoryId, double point) {
		super();
		this.id = id;
		this.question = question;
		this.answer = answer;
		this.categoryId = categoryId;
		this.point = point;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<Choice> getChoices() {
		return choices;
	}

	public void setChoices(List<Choice> choices) {
		this.choices = choices;
	}

	public int getAnswer() {
		return answer;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getPoint() {
		return point;
	}

	public void setPoint(double point) {
		this.point = point;
	}

	
	
}
