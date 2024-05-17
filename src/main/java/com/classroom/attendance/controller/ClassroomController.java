package com.classroom.attendance.controller;

import com.classroom.attendance.model.Activity;
import com.classroom.attendance.service.ClassroomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api")
@Tag(
    name = "ClassroomController",
    description = "Endpoints to fetch current activities and check-in")
public class ClassroomController {

  private ClassroomService classroomService;

  @GetMapping("/classroom/{reference}")
  @Operation(
      summary = "Get classroom current activity",
      description = "Endpoint to retrieve current activity for a given classroom")
  @ApiResponse(responseCode = "200", description = "Activity found")
  @ApiResponse(responseCode = "404", description = "Activity Not Found")
  @ApiResponse(responseCode = "403", description = "Action is forbidden")
  @ApiResponse(responseCode = "500", description = "Internal Server Error")
  public Activity getActivity(
  @Parameter(description = "Classroom unique reference") @PathVariable String reference)
  {
    return new Activity();
  }

  @GetMapping("/classroom/{reference}/checkin")
  @Operation(
      summary = "Get classroom current activity",
      description = "Endpoint to execute checkin for a student")
  @ApiResponse(responseCode = "200", description = "Activity found")
  @ApiResponse(responseCode = "404", description = "Activity Not Found")
  @ApiResponse(responseCode = "403", description = "Action is forbidden")
  @ApiResponse(responseCode = "500", description = "Internal Server Error")
  public Activity checkinAttendance(
      @Parameter(description = "Classroom unique reference") @PathVariable String reference)
  {
    return new Activity();
  }

}
