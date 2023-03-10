package com.techacademy.logisticpackage.commons.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.*;
import javax.validation.ConstraintDefinitionException;
import javax.validation.ConstraintViolationException;
import java.sql.SQLTransientConnectionException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Log4j2
@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

  private static final String DB_CONNECTION = "Unable to connect to database";

  @ExceptionHandler({EnumRequestHeaderException.class})
  public ResponseEntity<Object> handleEnumRequestHeaderException(EnumRequestHeaderException ex,
                                                                 WebRequest request) {
    ApiError apiError = new ApiError(
            HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), ex.getCause().getMessage());
    return ResponseEntityBuilder.build(apiError);
  }

  @Override
  protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex,
                                                                        HttpHeaders headers,
                                                                        HttpStatus status,
                                                                        WebRequest request) {
    ApiError apiError = new ApiError(
            HttpStatus.BAD_REQUEST, ex.getCause().getMessage(), ex.getLocalizedMessage());
    return ResponseEntityBuilder.build(apiError);
  }

  @Override
  protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    ApiError apiError =
            new ApiError(HttpStatus.BAD_REQUEST, ex.getParameterName(), ex.getParameterType());
    return ResponseEntityBuilder.build(apiError);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
          MethodArgumentNotValidException ex,
          HttpHeaders headers,
          HttpStatus status,
          WebRequest request) {
    List<String> errors = new ArrayList<>();
    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
      errors.add(error.getField() + ": " + error.getDefaultMessage());
    }
    for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
      errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
    }

    ApiError apiError =
            new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
    log.error("Status: {}, Errors: {}",HttpStatus.BAD_REQUEST,errors);
    return handleExceptionInternal(
            ex, apiError, headers, apiError.getStatus(), request);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                HttpHeaders headers,
                                                                HttpStatus status,
                                                                WebRequest request) {

    ApiError apiError =
            new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), ex.getMessage());
    return ResponseEntityBuilder.build(apiError);
  }

  @ExceptionHandler({NoSuchElementException.class})
  public ResponseEntity<Object> handleNoSuchElementException(Exception ex, WebRequest request) {
    ApiError apiError = new ApiError(
            HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), "error occurred in get id");
    return ResponseEntityBuilder.build(apiError);
  }
  @ExceptionHandler({Exception.class})
  public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
    ApiError apiError = new ApiError(
            HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), "error occurred");
    return ResponseEntityBuilder.build(apiError);
  }

  /*
  ResponseStatusException handler.
  */
  @ExceptionHandler({ResponseStatusException.class})
  public ResponseEntity<Object> handleResponseStatusException(Exception ex,
                                                                WebRequest request) {
    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,
            ex.getLocalizedMessage(), ex.getMessage());
    return ResponseEntityBuilder.build(apiError);
  }

  /*
  ConversionFailedException handler.
  */
  @ExceptionHandler({ConversionFailedException.class})
  public ResponseEntity<Object> handleConversionFailedException(Exception ex,
                                                                WebRequest request) {
    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,
            ex.getLocalizedMessage(), ex.getMessage());
    return ResponseEntityBuilder.build(apiError);
  }

  /*
  ConstraintViolationException handler.
  */
  @ExceptionHandler({ConstraintViolationException.class})
  public ResponseEntity<Object> handleConstraintViolationException(Exception ex,
                                                                   WebRequest request) {
    ApiError apiError = new ApiError(HttpStatus.CONFLICT,
            ex.getLocalizedMessage(), "ConstraintViolationException occurred");
    return ResponseEntityBuilder.build(apiError);
  }

  /*
  ConstraintDefinitionException handler.
  */
  @ExceptionHandler({ConstraintDefinitionException.class})
  public ResponseEntity<Object> handleConstraintDefinitionException(Exception ex,
                                                                   WebRequest request) {
    ApiError apiError = new ApiError(HttpStatus.CONFLICT,
            ex.getLocalizedMessage(), "ConstraintDefinitionException occurred");
    return ResponseEntityBuilder.build(apiError);
  }

  /*
  Exception handler for existing entities.
  */
  @ExceptionHandler({EntityExistsException.class})
  public ResponseEntity<Object> handleEntityExistsException(Exception ex, WebRequest request) {
    ApiError apiError = new ApiError(HttpStatus.CONFLICT,
            ex.getLocalizedMessage(), "EntityExistsException occurred");
    return ResponseEntityBuilder.build(apiError);
  }

  /*
  Exception handler for not found entities.
  */
  @ExceptionHandler({EntityNotFoundException.class})
  public ResponseEntity<Object> handleEntityNotFoundException(Exception ex, WebRequest request) {
    ApiError apiError = new ApiError(HttpStatus.CONFLICT,
            ex.getLocalizedMessage(), "EntityNotFoundException occurred");
    return ResponseEntityBuilder.build(apiError);
  }

  /*
  LockTimeout Exception handler
  */
  @ExceptionHandler({LockTimeoutException.class})
  public ResponseEntity<Object> handleLockTimeoutException(Exception ex, WebRequest request) {
    ApiError apiError = new ApiError(HttpStatus.CONFLICT,
            ex.getLocalizedMessage(), "LockTimeoutException occurred");
    return ResponseEntityBuilder.build(apiError);
  }

  /*
  NonUniqueResult Exception handler
  */
  @ExceptionHandler({NonUniqueResultException.class})
  public ResponseEntity<Object> handleNonUniqueResultException(Exception ex, WebRequest request) {
    ApiError apiError = new ApiError(HttpStatus.CONFLICT,
            ex.getLocalizedMessage(), "NonUniqueResultException occurred");
    return ResponseEntityBuilder.build(apiError);
  }

  /*
  NoResult Exception handler
  */
  @ExceptionHandler({NoResultException.class})
  public ResponseEntity<Object> handleNoResultException(Exception ex, WebRequest request) {
    ApiError apiError = new ApiError(HttpStatus.NOT_FOUND,
            ex.getLocalizedMessage(), "NoResultException occurred");
    return ResponseEntityBuilder.build(apiError);
  }

  /*
  OptimisticLock Exception handler
  */
  @ExceptionHandler({OptimisticLockException.class})
  public ResponseEntity<Object> handleOptimisticLockException(Exception ex, WebRequest request) {
    ApiError apiError = new ApiError(HttpStatus.CONFLICT,
            ex.getLocalizedMessage(), "OptimisticLockException occurred");
    return ResponseEntityBuilder.build(apiError);
  }

  /*
   PessimisticLock Exception handler
  */
  @ExceptionHandler({PessimisticLockException.class})
  public ResponseEntity<Object> handlePessimisticLockException(Exception ex, WebRequest request) {
    ApiError apiError = new ApiError(HttpStatus.CONFLICT,
            ex.getLocalizedMessage(), "PessimisticLockException occurred");
    return ResponseEntityBuilder.build(apiError);
  }

  /*
  QueryTimeout Exception handler
  */
  @ExceptionHandler({QueryTimeoutException.class})
  public ResponseEntity<Object> handleQueryTimeoutException(Exception ex, WebRequest request) {
    ApiError apiError = new ApiError(HttpStatus.CONFLICT,
            ex.getLocalizedMessage(), "QueryTimeoutException occurred");
    return ResponseEntityBuilder.build(apiError);
  }

  /*
  Rollback Exception handler
  */
  @ExceptionHandler({RollbackException.class})
  public ResponseEntity<Object> handleRollbackException(Exception ex, WebRequest request) {
    ApiError apiError = new ApiError(HttpStatus.CONFLICT,
            ex.getLocalizedMessage(), "RollbackException occurred");
    return ResponseEntityBuilder.build(apiError);
  }

  /*
  TransactionRequired Exception handler
  */
  @ExceptionHandler({TransactionRequiredException.class})
  public ResponseEntity<Object> handleTransactionRequiredException(Exception ex,
                                                                   WebRequest request) {
    ApiError apiError = new ApiError(HttpStatus.CONFLICT,
            ex.getLocalizedMessage(), "TransactionRequiredException occurred");
    return ResponseEntityBuilder.build(apiError);
  }

  /*
  CannotCreateTransaction Exception handler
  */
  @ExceptionHandler({CannotCreateTransactionException.class})
  public ResponseEntity<Object> handleCannotCreateTransactionException(Exception ex,
                                                                       WebRequest request) {
    ApiError apiError = new ApiError(HttpStatus.SERVICE_UNAVAILABLE,
            ex.getLocalizedMessage(), DB_CONNECTION);
    return ResponseEntityBuilder.build(apiError);
  }

  /*
  SQLTransientConnectionException handler
  */
  @ExceptionHandler({SQLTransientConnectionException.class})
  public ResponseEntity<Object> handleSQLTransientConnectionException(Exception ex,
                                                                      WebRequest request) {
    ApiError apiError = new ApiError(HttpStatus.SERVICE_UNAVAILABLE,
            ex.getLocalizedMessage(), DB_CONNECTION);
    return ResponseEntityBuilder.build(apiError);
  }

  /*
  PersistenceException handler
  */
  @ExceptionHandler({PersistenceException.class})
  public ResponseEntity<Object> handlePersistenceException(Exception ex,
                                                           WebRequest request) {
    ApiError apiError = new ApiError(HttpStatus.SERVICE_UNAVAILABLE,
            ex.getLocalizedMessage(), DB_CONNECTION);
    return ResponseEntityBuilder.build(apiError);
  }

}
