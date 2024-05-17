package com.classroom.attendance.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Checkin {
  @Id
  @Schema(description = "Checkin Id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "activity_code")
  private String activityCode;

  @Column(name = "checkedin_by")
  private String checkedinBy;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "logging_time")
  private Date loggingTime;
}
