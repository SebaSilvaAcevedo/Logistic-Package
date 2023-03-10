package com.techacademy.logisticpackage.commons.exception;

/**
 * @author Beatriz Lara <ext_blarao@falabella.cl>
 */

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
@Log4j2
public class ApiError {

  private HttpStatus status;
  private String message;
  private List<String> errors;

  public ApiError(HttpStatus status, String message, List<String> errors) {
    super();
    this.status = status;
    this.message = message;
    this.errors = errors;
  }

  public ApiError(HttpStatus status, String message, String error) {
    super();
    this.status = status;
    this.message = message;
    errors = Arrays.asList(error);

  }

  public ApiError(HttpStatus status, String message) {
    super();
    this.status = status;
    this.message = message;

  }

  public HttpStatus getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }

  public List<String> getErrors() {
    return errors;
  }
}
