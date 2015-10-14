package bpm.training.Setup.client;

import bpm.training.Setup.shared.User;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("user")
public interface IUserService extends RemoteService{
	
	 public class Connect {
		    private static IUserServiceAsync service;

		    public static IUserServiceAsync getService() {
		    if (service == null) {
		      service = (IUserServiceAsync) GWT.create(IUserService.class);
		    }

		     return service;
		   }

		 }
	 
	 void createUser(User user) throws Exception;
	 User getUser(User user) throws Exception;
}
