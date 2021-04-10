package ar.com.educationit.service;

import ar.com.educationit.domain.User;
import ar.com.educationit.exceptions.ServiceException;

public interface UserService {

	public User getUserByUsername(String username) throws ServiceException;
}
