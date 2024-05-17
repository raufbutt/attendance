package com.classroom.attendance.dto;

import lombok.Data;

@Data
public class ActivityResponse {

  private String activityCode;
  private String status;
  private String className;
  private String timeSlot;
  private String student;
}
