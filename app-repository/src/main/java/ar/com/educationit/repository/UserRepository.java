package ar.com.educationit.repository;

import ar.com.educationit.domain.User;

public interface UserRepository {

	User getUser(String username) throws GenericException;

}
