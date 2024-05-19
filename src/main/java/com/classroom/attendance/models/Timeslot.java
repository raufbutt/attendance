package com.classroom.attendance.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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

  //@ManyToOne
  @JoinColumn(name = "classroom_ref")
  private String classroom;

  @Schema(description = "Activity")
  private String activityCode;
}
