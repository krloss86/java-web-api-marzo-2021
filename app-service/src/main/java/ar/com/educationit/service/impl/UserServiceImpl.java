package ar.com.educationit.service.impl;

import ar.com.educationit.domain.User;
import ar.com.educationit.exceptions.ServiceException;
import ar.com.educationit.repository.UserRepository;
import ar.com.educationit.repository.impl.UserRepositoryImpl;
import ar.com.educationit.service.UserService;

public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	
	public UserServiceImpl() {
		this.userRepository = new UserRepositoryImpl();
	}
	
	@Override
	public User getUserByUsername(String username) throws ServiceException {
		
		try {
			return this.userRepository.getUser(username);
		}catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

}
