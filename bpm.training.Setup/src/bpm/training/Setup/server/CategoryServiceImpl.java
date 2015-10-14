package bpm.training.Setup.server;

import java.util.List;

import javax.servlet.http.HttpSession;

import bpm.training.Setup.client.ICategoryService;
import bpm.training.Setup.client.resources.IConstants;
import bpm.training.Setup.server.dao.CategoryDao;
import bpm.training.Setup.shared.Category;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class CategoryServiceImpl extends RemoteServiceServlet implements ICategoryService {

	private static final long serialVersionUID = 1L;
	
	CategoryDao catDao = new CategoryDao();
	Category cat;
	
	public void setUserInSession() {
		HttpSession session = getThreadLocalRequest().getSession();
	    session.setAttribute(IConstants.CATEGORY_SESSION, cat);
	  }

	public List<Category> getCategory() throws Exception{
		setUserInSession();
		return catDao.getCategory();
	}
}
