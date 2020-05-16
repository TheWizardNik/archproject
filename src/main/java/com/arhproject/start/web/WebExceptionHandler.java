package com.arhproject.start.web;

import com.arhproject.start.web.mapper.FailureBodyMapper;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@AllArgsConstructor
public class WebExceptionHandler extends ResponseEntityExceptionHandler {

    private final FailureBodyMapper failureBodyMapper;

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex, HttpServletRequest request) {
        return new ResponseEntity<>(failureBodyMapper.toModel(
                "Entity not found", HttpStatus.NOT_FOUND, request), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handleEntityNotFound(EmptyResultDataAccessException ex, HttpServletRequest request) {
        return new ResponseEntity<>(failureBodyMapper.toModel(
                "Entity not found", HttpStatus.NOT_FOUND, request), HttpStatus.NOT_FOUND);
    }

/*    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Object> handleUserAlreadyExist(UserAlreadyExistsException ex, HttpServletRequest request) {
        return new ResponseEntity<>(failureBodyMapper.toModel
                ("User already exists", HttpStatus.CONFLICT, request), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InterviewAlreadyFinished.class)
    public ResponseEntity<Object> handleInterviewAlreadyFinished(InterviewAlreadyFinished ex, HttpServletRequest request) {
        return new ResponseEntity<>(failureBodyMapper.toModel
                ("Interview already finished", HttpStatus.CONFLICT, request), HttpStatus.CONFLICT);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (!(request instanceof ServletWebRequest)) {
            return super.handleMethodArgumentNotValid(ex, headers, status, request);
        }

        HttpServletRequest servletRequest = ((ServletWebRequest) request).getRequest();
        Map<String, List<String>> validationErrors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.groupingBy(FieldError::getField,
                        Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())));

        FailureBodyModel body = failureBodyMapper.toModel(
                "Validation errors", HttpStatus.UNPROCESSABLE_ENTITY, servletRequest, validationErrors);

        return new ResponseEntity<>(body, HttpStatus.UNPROCESSABLE_ENTITY);
    }
 */
}
