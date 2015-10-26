package bpm.training.Setup.server;

import java.util.List;

import javax.servlet.http.HttpSession;

import bpm.training.Setup.client.ICategoryService;
import bpm.training.Setup.client.resources.IConstants;
import bpm.training.Setup.server.dao.CategoryDao;
import bpm.training.Setup.shared.Category;
import bpm.training.Setup.shared.Question;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class CategoryServiceImpl extends RemoteServiceServlet implements ICategoryService {

	private static final long serialVersionUID = 1L;
	
	CategoryDao catDao = new CategoryDao();
	Category cat;
	
	public void setCategoryInSession() {
		HttpSession session = getThreadLocalRequest().getSession();
	    session.setAttribute(IConstants.CATEGORY_SESSION, cat);
	  }
	
	public List<Category> getCategory() throws Exception{
		setCategoryInSession();
		return catDao.getCategory();
	}

	@Override
	public void addCategory(Category category) throws Exception {
		catDao.addCategory(category);
		
	}

	public void addQuestions(List<Question> question) {
		catDao.addQuestions(question);
	}

	@Override
	public void deleteQuestion(Question question) {
		catDao.deleteQuestion(question);
	}

}
