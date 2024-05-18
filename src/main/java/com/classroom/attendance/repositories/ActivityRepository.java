package com.classroom.attendance.repositories;

import com.classroom.attendance.models.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, String> {

  Activity findActivityByClassroomReference(String reference);
}
