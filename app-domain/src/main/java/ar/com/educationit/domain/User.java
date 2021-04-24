package ar.com.educationit.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name="user")
public class User implements Serializable {

	private static final long serialVersionUID = 3331393383588340029L;

	@Id
	private Long id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	//fetch eager, lazy
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name="user_role",
			joinColumns = {
				@JoinColumn(name="user_id")	
			},
			inverseJoinColumns = {
				@JoinColumn(name="role_id")
			}
	)
	private List<Role> roles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
