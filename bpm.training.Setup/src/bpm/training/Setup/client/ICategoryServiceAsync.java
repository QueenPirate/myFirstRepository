package bpm.training.Setup.client;

import java.util.List;

import bpm.training.Setup.shared.Category;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ICategoryServiceAsync {

	void getCategory( AsyncCallback<List<Category>> callback);

}
