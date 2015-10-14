package bpm.training.Setup.client;

import java.util.List;

import bpm.training.Setup.shared.Category;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("category")
public interface ICategoryService extends RemoteService {

	public class Connect {
	    private static ICategoryServiceAsync service;

	    public static ICategoryServiceAsync getService() {
	    if (service == null) {
	      service = (ICategoryServiceAsync) GWT.create(ICategoryService.class);
	    }

	     return service;
	   }

	 }
	
	List<Category> getCategory() throws Exception;
	
}
