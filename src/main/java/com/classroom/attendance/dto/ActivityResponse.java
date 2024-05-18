package com.classroom.attendance.dto;
import lombok.Data;

@Data
public class ActivityResponse {

  private String activityCode;
  private String description;
  private String status;
  private String classroomReference;
  private String adminId;
  private String student;
}
