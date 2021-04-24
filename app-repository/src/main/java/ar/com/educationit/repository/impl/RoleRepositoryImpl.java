package ar.com.educationit.repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import ar.com.educationit.domain.Role;
import ar.com.educationit.repository.GenericException;
import ar.com.educationit.repository.RoleRepository;

public class RoleRepositoryImpl extends HibernateBaseRepository implements RoleRepository {

	public RoleRepositoryImpl() {
		super();
	}
	
	@Override
	public List<Role> findRoles() throws GenericException{
		
		List<Role> roles = null;
		
		Session session = super.factory.getCurrentSession();
		
		try {
			session.getTransaction().begin();
			
			String sql = "Select r from " + Role.class.getName() + " r";
			
			Query<Role> query = session.createQuery(sql);
					
			roles = query.getResultList();
			
			session.getTransaction().commit();
		}catch (Exception e) {
			session.getTransaction().rollback();
			throw new GenericException(e.getMessage(), e);
		}
		
		return roles;
	}

}
