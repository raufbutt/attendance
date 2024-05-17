package com.classroom.attendance.repository;

import com.classroom.attendance.model.Timeslot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeslotRepository extends JpaRepository<Timeslot, Long> {

}
