package com.classroom.attendance.repositories;

import com.classroom.attendance.models.Classroom;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomRepository extends JpaRepository<Classroom, String> {
  Optional<Classroom> findClassroomByReference(String reference);

  //Classroom findClassroomByReference(String reference);

}
