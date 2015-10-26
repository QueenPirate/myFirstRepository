package bpm.training.Setup.server.dao;

import java.util.List;

import org.hibernate.Session;

import bpm.training.Setup.shared.User;

public class UserDao {

	public void saveUser(User user) throws Exception {
		  Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		  session.beginTransaction();
		  @SuppressWarnings("unchecked")
		  List<User> users = session.createQuery("from User WHERE email_add='" + user.getEmailAdd() + "'").list();
		  
		  
		  if(users.isEmpty()){
			  session.save(user);
			  session.getTransaction().commit();
		  }else{
			  throw new Exception("User already exist!");
		  }
		  
	}
	public User getUser(User user) throws Exception {
		  Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		  session.beginTransaction();
		  @SuppressWarnings("unchecked")
		  List<User> users =  session.createQuery("from User WHERE email_add='" + user.getEmailAdd() + "'").list();
		  session.getTransaction().commit();
		  
		  if(users.isEmpty()){
			  throw new Exception("User does not exist...");
		  }else{
			  User userData = users.get(0);
			  if(userData.getPassword().equals(user.getPassword())){
				  	return userData;
		   }else{
			   		throw new Exception("Wrong password");
		   }
		 }
		  
	}
	public List<User> getAllUsers() throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		@SuppressWarnings("unchecked")
		List<User> users = session.createQuery("from User").list();
		
		if(users.isEmpty()){
			throw new Exception("No Users");
		}else{
			return users;
		}
		
	}
}