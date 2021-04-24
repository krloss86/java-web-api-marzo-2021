package ar.com.educationit.repository;

import java.util.List;

import ar.com.educationit.domain.Role;

public interface RoleRepository {

	List<Role> findRoles() throws GenericException;

}
