package com.classroom.attendance.repository;

import com.classroom.attendance.model.Checkin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckinRepository extends JpaRepository<Checkin, Long> {

}
