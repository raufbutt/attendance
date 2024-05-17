package com.classroom.attendance.repositories;

import com.classroom.attendance.models.Checkin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckinRepository extends JpaRepository<Checkin, Long> {

}
