package com.classroom.attendance.exceptions;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class BadRequestException extends ResponseStatusException {
  public BadRequestException(HttpStatusCode status, String errorMessage) {
    super(status, errorMessage);
  }

}
