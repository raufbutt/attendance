package com.classroom.attendance.models;

import com.classroom.attendance.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Activity {
    @Id
    @Schema(description = "Activity Code")
    @Column(name = "activity_code")
    private String activityCode;

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

    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Column(name = "status")
    private Status status;
}
