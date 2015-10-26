package bpm.training.Setup.client;

import java.util.List;

import bpm.training.Setup.shared.Category;
import bpm.training.Setup.shared.Question;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ICategoryServiceAsync {

	void getCategory( AsyncCallback<List<Category>> callback);

	void addCategory(Category category, AsyncCallback<Void> callback);

	void addQuestions(List<Question> questions, AsyncCallback<Void> callback);

	void deleteQuestion(Question question, AsyncCallback<Void> callback);


}
