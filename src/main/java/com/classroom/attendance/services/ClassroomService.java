package com.classroom.attendance.services;

import com.classroom.attendance.dto.ActivityResponse;

public interface ClassroomService {

  ActivityResponse getActivity(String reference);

  ActivityResponse registerCheckin(String reference);

  }
