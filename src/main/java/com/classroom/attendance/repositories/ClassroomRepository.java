package com.classroom.attendance.repositories;

import com.classroom.attendance.models.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomRepository extends JpaRepository<Classroom, String> {

}
