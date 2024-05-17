package com.classroom.attendance.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
  @Id
  @Schema(description = "Student Id")
  @Column(name = "student_id")
  private String studentId;

  @Column(name = "given_name")
  private String givenName;

  @Column(name = "surname")
  private String surname;

  @Column(name = "email")
  private String email;

  @Column(name = "address")
  private String address;

  @Column(name = "registration")
  private String registration;

  @Column(name = "active")
  private Boolean active;
}
