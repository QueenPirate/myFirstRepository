package bpm.training.Setup.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String picture;
	private String name;
	private String color;
	private String txtColor;
	private List<Question> questions = new ArrayList<>();
	private double totalScore = 0;
	
	public Category() {
		// TODO Auto-generated constructor stub
	}
	
	public Category(long id, String picture, String name, String color, String txtColor) {
		super();
		this.id = id;
		this.picture = picture;
		this.name = name;
		this.color = color;
		this.txtColor = txtColor;
	}
	
	public Category(String picture, String name, String color, String txtColor) {
		super();
		this.picture = picture;
		this.name = name;
		this.color = color;
		this.txtColor = txtColor;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public String gettxtColor() {
		return txtColor;
	}

	public void settxtColor(String txtColor) {
		this.txtColor = txtColor;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public double getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(double totalScore) {
		this.totalScore = totalScore;
	}

	

}
