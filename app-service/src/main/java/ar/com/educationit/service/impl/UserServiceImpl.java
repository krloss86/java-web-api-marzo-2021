package ar.com.educationit.service.impl;

import java.util.List;

import ar.com.educationit.domain.Role;
import ar.com.educationit.domain.User;
import ar.com.educationit.exceptions.ServiceException;
import ar.com.educationit.repository.GenericException;
import ar.com.educationit.repository.RoleRepository;
import ar.com.educationit.repository.UserRepository;
import ar.com.educationit.repository.impl.RoleRepositoryImpl;
import ar.com.educationit.repository.impl.UserRepositoryImpl;
import ar.com.educationit.service.UserService;

public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private RoleRepository rr;
	
	public UserServiceImpl() {
		this.userRepository = new UserRepositoryImpl();
		this.rr = new RoleRepositoryImpl();
	}
	
	@Override
	public User getUserByUsername(String username) throws ServiceException {
		
		try {
			return this.userRepository.getUser(username);
		}catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	public List<Role> findRoles() throws ServiceException {
		try {
			return this.rr.findRoles();
		} catch (GenericException e) {
			throw new ServiceException("No se ha podido obtener la lista de tipo de productos", e);
		}
	}
}
