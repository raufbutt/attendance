package com.classroom.attendance.repository;

import com.classroom.attendance.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, String> {

}
