package bpm.training.Setup.server.dao;

import java.util.List;

import org.hibernate.Session;

import bpm.training.Setup.shared.Category;
import bpm.training.Setup.shared.Choice;
import bpm.training.Setup.shared.Question;

public class CategoryDao {

	public List<Category> getCategory() throws Exception{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Category> categories =  session.createQuery("from Category").list();
		session.getTransaction().commit();
		
		for(Category cat : categories){
			cat.setQuestions(getQuestions(cat.getId()));
			
		}
		
		if(categories.isEmpty()){
			 throw new Exception("No Categories.");
		}else{
			 return categories;
		}
	}
	
	public List<Question> getQuestions(long selectedCatItem) throws Exception{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Question> questions = session.createQuery("from Question WHERE cat_id=" + selectedCatItem).list();
		session.getTransaction().commit();
		for(Question question : questions){
			question.setChoices(getChoices(question.getId()));
		}
			return questions;
	}

	public List<Choice> getChoices(long selectedQuesItem) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Choice> choices = session.createQuery("from Choice WHERE question_id=" + selectedQuesItem).list();
		session.getTransaction().commit();
		
		if(choices.isEmpty()){
			throw new Exception("No choices");
		}else{
			return choices;
		}
	}

	public void addCategory(Category category) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Category> categories = session.createQuery("from Category WHERE cat_name='" + category.getName() + "'").list();
		
		if(categories.isEmpty()){
			session.save(category);
			session.getTransaction().commit();
		}else{
			throw new Exception("Category already exist!");
		}
	}

	public void addQuestions(List<Question> question) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		for(Question q : question){
			if(q.getId() == 0){
				session.save(q);
			}else{
				session.update(q);
			}
			
			for(Choice c : q.getChoices()){
				if(c.getId() == 0){
					c.setQuestion_id(q.getId());
					session.save(c);
				}else{
					session.update(c);
				}
				
			}
			
		}
		session.getTransaction().commit();
	}

	public void deleteQuestion(Question question) {
		Session  session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		for(Choice c : question.getChoices()){
			session.delete(c);
		}
		session.delete(question);
		session.getTransaction().commit();
	}

}