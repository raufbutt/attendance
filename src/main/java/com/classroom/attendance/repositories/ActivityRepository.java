package com.classroom.attendance.repositories;

import com.classroom.attendance.models.Activity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, String> {
  Optional<Activity> findActivityByActivityCode(String code);


  Optional<Activity> findActivityByClassroomReference(String reference);
}
