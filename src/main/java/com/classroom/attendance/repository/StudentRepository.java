package com.classroom.attendance.repository;

import com.classroom.attendance.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {

}
