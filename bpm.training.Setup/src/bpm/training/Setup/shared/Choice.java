package bpm.training.Setup.shared;

import java.io.Serializable;

public class Choice implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String choice;
	private long question_id;
	
	public Choice(long id, String choice, long question_id) {
		super();
		this.id = id;
		this.choice = choice;
		this.question_id = question_id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public long getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(long question_id) {
		this.question_id = question_id;
	}
	
	
}

