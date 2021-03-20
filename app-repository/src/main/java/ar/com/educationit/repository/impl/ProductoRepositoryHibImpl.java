package ar.com.educationit.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ar.com.educationit.domain.Producto;
import ar.com.educationit.hibernate.HibernateUtils;
import ar.com.educationit.repository.DuplicatedException;
import ar.com.educationit.repository.GenericException;
import ar.com.educationit.repository.ProductoRepository;

public class ProductoRepositoryHibImpl implements ProductoRepository{

	@Override
	public Producto save(Producto producto) throws DuplicatedException, GenericException {
		
		SessionFactory sf = null;
		try {
			sf = HibernateUtils.getSessionFactory();
		}catch (Exception e) {
			throw new GenericException("No se ha podido crear session factory", e);
		}
		
		Session session = null;
		try {
			session = sf.getCurrentSession();
		}catch (Exception e) {
			throw new GenericException("No se ha podido crear session", e);
		}
			
		
		session.beginTransaction();
			
		//logica
		
		try {
			session.saveOrUpdate(producto);
		}catch (org.hibernate.exception.ConstraintViolationException e) {			
			throw new DuplicatedException("Prducto duplicado: " +e.getMessage(), e);
		}
		//
		
		session.getTransaction().commit();
				
		session.close();
		
		return producto;
	}

}
