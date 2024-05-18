package com.classroom.attendance.repositories;

import com.classroom.attendance.models.Timeslot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeslotRepository extends JpaRepository<Timeslot, Long> {
}
