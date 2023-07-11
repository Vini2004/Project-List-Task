package br.com.mvc.projectListTask.service.exceptions;

public class TaskDeleteException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public TaskDeleteException(String message) {
        super(message);
    }
}

