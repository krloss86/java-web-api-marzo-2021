package ar.com.educationit.repository.impl;

import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.query.Query;

import ar.com.educationit.domain.User;
import ar.com.educationit.repository.GenericException;
import ar.com.educationit.repository.UserRepository;

public class UserRepositoryImpl extends HibernateBaseRepository implements UserRepository {

	public UserRepositoryImpl() {
		super();
	}
	
	@Override
	public User getUser(String username) throws GenericException{
		
		User user = null;
		
		Session session = super.factory.getCurrentSession();
		
		try {
			session.getTransaction().begin();
			
			String sql = "Select u from " + User.class.getName() + " u where u.username=:username ";
			
			Query<User> query = session.createQuery(sql);
					
			query.setParameter("username", username);
			
			Optional<User> userOptional = query.uniqueResultOptional();
			
			if(userOptional.isPresent()) {
				user = userOptional.get();
			}
			
			session.getTransaction().commit();
		}catch (Exception e) {
			session.getTransaction().rollback();
			throw new GenericException(e.getMessage(), e);
		}
		
		return user;
	}

}
