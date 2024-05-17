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
public class Activity {
    @Id
    @Schema(description = "Activity Code")
    @Column(name = "code")
    private String code;

    @Schema(description = "Activity Description")
    @Column(name = "description")
    private String description;

    @Schema(description = "Classroom Unique Reference")
    @Column(name = "cr_reference")
    private String classroomReference;

    @Schema(description = "Timeslot Id")
    @Column(name = "timeslot_id")
    private long timeslotId;

    @Schema(description = "Staff Id")
    @Column(name = "admin_id")
    private String adminId;

    //NOTE: A student will not check-in to an activity, it will check-in to a class
    @Schema(description = "Student Id")
    @Column(name = "studentId")
    private String studentId;

    @Column(name = "status")
    private String status;
}
