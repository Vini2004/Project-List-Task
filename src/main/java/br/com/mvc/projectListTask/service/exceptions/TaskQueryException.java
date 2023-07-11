package br.com.mvc.projectListTask.service.exceptions;

public class TaskQueryException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public TaskQueryException(String message) {
        super(message);
    }
}
