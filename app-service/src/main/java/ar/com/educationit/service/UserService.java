package ar.com.educationit.service;

import java.util.List;

import ar.com.educationit.domain.Role;
import ar.com.educationit.domain.User;
import ar.com.educationit.exceptions.ServiceException;

public interface UserService {

	public User getUserByUsername(String username) throws ServiceException;
	
	public List<Role> findRoles() throws ServiceException;
}
