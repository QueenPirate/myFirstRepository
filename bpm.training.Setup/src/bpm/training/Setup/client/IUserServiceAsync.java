package bpm.training.Setup.client;

import bpm.training.Setup.shared.User;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface IUserServiceAsync {

	void createUser(User user, AsyncCallback<Void> callback);

	void getUser(User user, AsyncCallback<User> callback);

}
