package com.classroom.attendance.repositories;

import com.classroom.attendance.models.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, String> {

}
