package bpm.training.Setup.client;

import java.util.List;

import bpm.training.Setup.shared.User;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface IUserServiceAsync {

	void createUser(User user, AsyncCallback<Void> callback);

	void getUser(User user, AsyncCallback<User> callback);

	void getAllUsers(AsyncCallback<List<User>> callback);


}
