package com.classroom.attendance.exceptions;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class ResourceConflictException extends ResponseStatusException {
  public ResourceConflictException(HttpStatusCode status, String errorMessage) {
    super(status, errorMessage);
  }

}
