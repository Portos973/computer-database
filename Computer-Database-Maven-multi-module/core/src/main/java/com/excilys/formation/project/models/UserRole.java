package com.excilys.formation.project.models;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_roles")
public class UserRole {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "user_role_id", unique = true, nullable = false)
	private Integer roleId;
	
	@Column(name = "ROLE", nullable = false, length = 45)
	private String role;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
 
	public UserRole() {
	}
 
	public UserRole(User user, String role) {
		this.role = role;
	}
 
	public Integer getUserRoleId() {
		return this.roleId;
	}
 
	public void setUserRoleId(Integer userRoleId) {
		this.roleId = userRoleId;
	}
 

	public String getRole() {
		return this.role;
	}
 
	public void setRole(String role) {
		this.role = role;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	

}
