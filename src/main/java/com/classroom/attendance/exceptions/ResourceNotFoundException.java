package com.classroom.attendance.exceptions;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class ResourceNotFoundException extends ResponseStatusException {
  public ResourceNotFoundException(HttpStatusCode status, String errorMessage) {
    super(status, errorMessage);
  }
}
