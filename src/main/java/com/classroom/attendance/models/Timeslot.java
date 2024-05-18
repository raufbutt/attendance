package com.classroom.attendance.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Timeslot {

  @Id
  @Schema(description = "Timeslot Id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long timeslotId;

  @Column(name = "from_time")
  private Date fromTime;

  @Column(name = "to_time")
  private Date toTime;

  @Column(name = "day_of_week")
  private String dayOfWeek;
}
