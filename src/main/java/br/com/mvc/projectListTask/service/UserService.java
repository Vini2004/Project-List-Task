package br.com.mvc.projectListTask.service;

import br.com.mvc.projectListTask.entities.UserDtls;

public interface UserService {
	
    public UserDtls createUser(UserDtls user);

    public boolean checkEmail(String email);
}
