package ar.com.educationit.repository.impl;

import org.hibernate.SessionFactory;

import ar.com.educationit.hibernate.HibernateUtils;

public abstract class HibernateBaseRepository {

	protected SessionFactory factory;
	
	public HibernateBaseRepository() {
		this.factory = HibernateUtils.getSessionFactory();
	}
}
