package com.excilys.formation.project.persistence;

import com.excilys.formation.project.models.User;

public interface IUserDAO {
	User findByUserName(String username);

}
