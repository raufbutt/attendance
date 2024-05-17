package com.classroom.attendance.repositories;

import com.classroom.attendance.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {

}
