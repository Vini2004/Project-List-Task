package br.com.mvc.projectListTask.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mvc.projectListTask.entities.Task;
import br.com.mvc.projectListTask.entities.UserDtls;
import br.com.mvc.projectListTask.entities.enums.TaskStatus;
import br.com.mvc.projectListTask.repositories.TaskRepository;
import br.com.mvc.projectListTask.service.exceptions.TaskDeleteException;
import br.com.mvc.projectListTask.service.exceptions.TaskNotFoundException;
import br.com.mvc.projectListTask.service.exceptions.TaskQueryException;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public void saveTaskAndUser(Task task, UserDtls user) {
        task.setStatus(TaskStatus.PENDENTE);
        task.setUser(user);
        taskRepository.save(task);
    }

    public void updateTask(Task updatedTask) {
        Task existingTask = getTaskById(updatedTask.getId());
        existingTask.setTitulo(updatedTask.getTitulo());
        existingTask.setDescricao(updatedTask.getDescricao());
        existingTask.setDataInicial(updatedTask.getDataInicial());
        existingTask.setDataFinal(updatedTask.getDataFinal());
        updatedTask.setStatus(existingTask.getStatus());
        taskRepository.save(existingTask);
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Tarefa inválida ID: " + id));
    }

    public void deleteTask(Long id) {
        Task task = getTaskById(id);
        try {
            taskRepository.delete(task);
        } catch (Exception e) {
            throw new TaskDeleteException("Erro ao excluir a tarefa com ID: " + id);
        }
    }
    
    public List<Task> getTasksByStatus(TaskStatus status) {
        try {
        	
            return taskRepository.findByStatus(status);
        } catch (Exception e) {
            throw new TaskQueryException("Erro ao consultar as tarefas por status: " + status);
        }
    }
    
    public List<Task> getTasksByUser(UserDtls user) {
        return taskRepository.findByUser(user);
    }
    
    public void saveTaskStatus(Long id, TaskStatus status) {
        Task task = getTaskById(id);
        task.setStatus(status);
        taskRepository.save(task);
    }
    
    public List<Task> getTasksByStatusAndUser(TaskStatus status, UserDtls user) {
        try {
            return taskRepository.findByStatusAndUser(status, user);
        } catch (Exception e) {
            throw new TaskQueryException("Erro ao consultar as tarefas por status e usuário: " + status);
        }
    }
    
    
    
}
