package com.classroom.attendance.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Checkin {
  @Id
  @Schema(description = "Checkin Id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Schema(description = "Classroom reference")
  @Column(name = "cr_reference")
  private String reference;

  @Column(name = "activity_code")
  private String activityCode;

  @Column(name = "checkedin_by")
  private String checkedinBy;

  @Column(name = "logging_time")
  private Date loggingTime;
}
