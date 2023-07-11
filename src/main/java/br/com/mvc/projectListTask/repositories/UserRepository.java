package br.com.mvc.projectListTask.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mvc.projectListTask.entities.UserDtls;

public interface UserRepository extends JpaRepository<UserDtls, Long> {
	
	public UserDtls findByEmail(String email);
	public boolean existsByEmail(String email);
	
}
