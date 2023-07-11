package br.com.mvc.projectListTask.controllers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class TaskControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleResourceNotFoundException(ResourceNotFoundException ex) {
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("status", "404");
        mv.addObject("error", "Resource Not Found");
        mv.addObject("message", ex.getMessage());
        return mv;
    }

}
