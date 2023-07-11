package br.com.mvc.projectListTask.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mvc.projectListTask.entities.Task;
import br.com.mvc.projectListTask.entities.UserDtls;
import br.com.mvc.projectListTask.entities.enums.TaskStatus;

public interface TaskRepository extends JpaRepository<Task, Long> {
	
	List<Task> findByStatus(TaskStatus pendente);
	List<Task> findByUser(UserDtls user);
	List<Task> findByStatusAndUser(TaskStatus status, UserDtls user);
	
}
