package com.classroom.attendance.services;

import com.classroom.attendance.dto.ActivityResponse;
import com.classroom.attendance.models.Activity;

public interface ClassroomService {

  Activity getActivity(String reference);

  ActivityResponse registerCheckin(String reference);

  }
