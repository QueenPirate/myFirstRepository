package bpm.training.Setup.server.dao;

import java.util.List;

import org.hibernate.Session;

import bpm.training.Setup.shared.Category;

public class CategoryDao {

	public List<Category> getCategory() throws Exception{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		  session.beginTransaction();
		  @SuppressWarnings("unchecked")
		  List<Category> categories =  session.createQuery("from Category").list();
		  session.getTransaction().commit();
		  
		  if(categories.isEmpty()){
			  throw new Exception("No Categories.");
		  }else{
			  return categories;
		  }
	}
}

