package com.classroom.attendance.repositories;

import com.classroom.attendance.models.Checkin;
import java.util.Date;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckinRepository extends JpaRepository<Checkin, Long> {
  Optional<Checkin> findCheckinByActivityCodeAndCheckedinByAndLogTimeIs(String code, String StudentId, Date today);
}
