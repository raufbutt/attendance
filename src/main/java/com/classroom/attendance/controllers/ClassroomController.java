package com.classroom.attendance.controllers;

import com.classroom.attendance.dto.ActivityResponse;
import com.classroom.attendance.models.Activity;
import com.classroom.attendance.services.ClassroomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(
    name = "ClassroomController",
    description = "Endpoints to fetch current activities and check-in")
public class ClassroomController {

  private final ClassroomService classroomService;

  @GetMapping("/classroom/{reference}")
  @Operation(
      summary = "Get classroom current activity",
      description = "Endpoint to retrieve current activity for a given classroom")
  @ApiResponse(responseCode = "200", description = "Activity found")
  @ApiResponse(responseCode = "404", description = "Activity Not Found")
  @ApiResponse(responseCode = "403", description = "Action is forbidden")
  @ApiResponse(responseCode = "500", description = "Internal Server Error")
  public ActivityResponse getActivity(
  @Parameter(description = "Classroom unique reference") @PathVariable String reference) {
      var response = classroomService.getActivity(reference);
      if (Objects.isNull(response)){
          throw new ResponseStatusException(HttpStatus.NOT_FOUND, "\"No Activity Found. Try later. (CODE 404)\n");
      }
      return response;
  }

  @GetMapping("/classroom/{reference}/checkin")
  @Operation(
      summary = "Checkin for the classroom",
      description = "Endpoint to execute checkin for a student")
  @ApiResponse(responseCode = "200", description = "Activity found")
  @ApiResponse(responseCode = "404", description = "Activity Not Found")
  @ApiResponse(responseCode = "403", description = "Action is forbidden")
  @ApiResponse(responseCode = "500", description = "Internal Server Error")
  public Activity registerCheckin(
      @Parameter(description = "Classroom unique reference") @PathVariable String reference)
  {
    return new Activity();
  }

}
