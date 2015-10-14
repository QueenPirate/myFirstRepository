
package bpm.training.Setup.server;

import javax.servlet.http.HttpSession;

import bpm.training.Setup.client.IUserService;
import bpm.training.Setup.client.resources.IConstants;
import bpm.training.Setup.server.dao.UserDao;
import bpm.training.Setup.shared.User;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class UserServiceImpl extends RemoteServiceServlet implements IUserService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	UserDao userdao = new UserDao();

	public void setUserInSession(User user) {
		HttpSession session = getThreadLocalRequest().getSession();
	    session.setAttribute(IConstants.USER_SESSION, user);
	  }
	@Override
	public void createUser(User user) throws Exception {
		userdao.saveUser(user);
	}

	@Override
	public User getUser(User user) throws Exception {
		user = userdao.getUser(user);
		setUserInSession(user);
		return user;
	}

}
