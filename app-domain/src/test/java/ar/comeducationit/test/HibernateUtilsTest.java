package ar.comeducationit.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ar.com.educationit.domain.Producto;
import ar.com.educationit.domain.TipoProducto;
import ar.com.educationit.hibernate.HibernateUtils;

public class HibernateUtilsTest {

	//@test
	public static void main(String[] args) {
		
		TipoProducto tp = new TipoProducto();
		tp.setDescripcion("cocina");
		
		Producto nuevoProducto = new Producto("mate", 1000f, "000001", tp);
		
		SessionFactory sf = HibernateUtils.getSessionFactory();
		
		Session session = sf.getCurrentSession();
		
		session.beginTransaction();
		
		session.save(tp);
		session.save(nuevoProducto);
		
		session.getTransaction().commit();
		
		System.out.println(nuevoProducto.getId());
	}
}
