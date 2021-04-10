package ar.com.educationit.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import ar.com.educationit.domain.Producto;
import ar.com.educationit.hibernate.HibernateUtils;
import ar.com.educationit.repository.DuplicatedException;
import ar.com.educationit.repository.GenericException;
import ar.com.educationit.repository.ProductoRepository;

public class ProductoRepositoryHibImpl implements ProductoRepository{

	//slf4
	//log4j
	//logback
	
	private SessionFactory factory;
	
	public ProductoRepositoryHibImpl() {
		this.factory = HibernateUtils.getSessionFactory();
	}
	
	@Override
	public Producto save(Producto producto) throws DuplicatedException, GenericException {
		/*
		SessionFactory sf = null;
		try {
			sf = HibernateUtils.getSessionFactory();
		}catch (Exception e) {
			throw new GenericException("No se ha podido crear session factory", e);
		}*/
		
		Session session = null;
		try {
			session = factory.getCurrentSession();
		}catch (Exception e) {
			throw new GenericException("No se ha podido crear session", e);
		}
			
		
		session.beginTransaction();
			
		//logica
		
		try {
			session.saveOrUpdate(producto);
			session.getTransaction().commit();
		}catch (org.hibernate.exception.ConstraintViolationException e) {
			session.getTransaction().rollback();
			throw new DuplicatedException("Prducto duplicado: " +e.getMessage(), e);
		}finally {
			session.close();			
		}
		
		return producto;
	}

	@Override
	public Producto delete(String codigo) throws GenericException {
		
		Producto producto = getByCodigo(codigo);
		
		/*
		if(producto == null) {
			throw new NonExistsException("");
		}
		*/
		
		Session session = this.factory.getCurrentSession();
		
		try {
			
			session.getTransaction().begin();
			
			session.remove(producto);
			
			session.getTransaction().commit();
		}catch (Exception e) {
			session.getTransaction().rollback();
			throw new GenericException(e.getMessage(), e);
		} finally {
			session.close();
		}
		
		return producto;
	}

	@Override
	public List<Producto> findAll() throws GenericException {
		
		Session session = this.factory.getCurrentSession();
		
		List<Producto> productos = new ArrayList<Producto>();
		
		try {
			
			session.getTransaction().begin();
			
			//HQL
			String hql = "Select p from " + Producto.class.getName() + " p" ;
			
			Query<Producto> query = session.createQuery(hql);
				
			productos = query.getResultList();
			
			session.getTransaction().commit();			
		}catch (Exception e) {
			session.getTransaction().rollback();
			throw new GenericException(e.getMessage(), e);			
		} finally {
			session.close();
		}
		
		return productos;
	}
	
	@Override
	public Producto getByCodigo(String codigoProducto) throws GenericException {
		
		Session session = factory.getCurrentSession();
		
		Producto producto = null;
		
		try {
			session.getTransaction().begin();
			
			String hql = "Select producto from " + Producto.class.getName()+ " producto where producto.codigo=:codigo ";
			
			Query<Producto> query = session.createQuery(hql);
			
			query.setParameter("codigo", codigoProducto);
			
			Optional<Producto> queryProducto = query.uniqueResultOptional();
			
			if(queryProducto.isPresent()) {
				producto = queryProducto.get();
			}
			
			session.getTransaction().commit();
		}catch (Exception e) {
			session.getTransaction().rollback();
			throw new GenericException(e.getMessage(), e);
		}finally {
			session.close();
		}
		
		return producto;
	}
	
	@Override
	public Producto getById(Long id) throws GenericException {
		
		Session session = factory.getCurrentSession();
		
		Producto producto = null;
		
		try {
			session.getTransaction().begin();
			
			String hql = "Select producto from " + Producto.class.getName()+ " producto where producto.id=:id ";
			
			Query<Producto> query = session.createQuery(hql);
			
			query.setParameter("id", id);
			
			Optional<Producto> queryProducto = query.uniqueResultOptional();
			
			if(queryProducto.isPresent()) {
				producto = queryProducto.get();
			}
			
			session.getTransaction().commit();
			//commit!
		}catch (Exception e) {
			session.getTransaction().rollback();
			throw new GenericException(e.getMessage(), e);
		}finally {
			session.close();
		}
		
		return producto;
	}
	
	@Override
	public List<Producto> search(String titulo) throws GenericException {
		
		Session session = factory.getCurrentSession();
		
		List<Producto> productos = new ArrayList<Producto>();
		
		try {
			
			session.getTransaction().begin();
			
			String hql = "Select producto from " + Producto.class.getName()+ " producto where UPPER(producto.titulo) like :titulo";
			
			Query<Producto> query = session.createQuery(hql);
			
			query.setParameter("titulo", "%"+titulo.toUpperCase()+"%");
			
			productos = query.getResultList();
			
			session.getTransaction().commit();
		}catch (Exception e) {			
			session.getTransaction().rollback();
			throw new GenericException(e.getMessage(), e);
		}finally {
			session.close();
		}
		
		return productos;
	}
	
	@Override
	public Producto update(Producto producto) throws GenericException {

		Producto productoToUpdate = getByCodigo(producto.getCodigo());
		
		Session session = factory.getCurrentSession();
		
		try {
			
			session.getTransaction().begin();
			
			if(productoToUpdate != null) {
				productoToUpdate.setPrecio(producto.getPrecio());
				productoToUpdate.setTipoProducto(producto.getTipoProducto());
				productoToUpdate.setTitulo(producto.getTitulo());
				
				session.saveOrUpdate(productoToUpdate);
				producto = productoToUpdate;
			}
			
			session.getTransaction().commit();
		}catch (Exception e) {
			session.getTransaction().rollback();
			throw new GenericException(e.getMessage(), e);
		}finally {
			session.close();
		}
		
		return producto;
	}
	
}
