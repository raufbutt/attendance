package com.classroom.attendance.repository;

import com.classroom.attendance.model.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomRepository extends JpaRepository<Classroom, String> {

}
