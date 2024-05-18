package com.classroom.attendance.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Classroom {
  @Id
  @Column(name = "reference")
  private String reference;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "location")
  private String location;

  @Schema(description = "Timeslot Id")
  private Long timeslotId;

  @Schema(description = "Activity")
  private String activityCode;
}
