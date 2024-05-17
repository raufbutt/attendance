package com.classroom.attendance.services;

import com.classroom.attendance.dto.ActivityResponse;
import org.springframework.stereotype.Service;

@Service
public class ClassroomService {

  public ActivityResponse getActivity(String reference){
    return new ActivityResponse();
  }

  public ActivityResponse registerCheckin(String reference, String Student){
    return new ActivityResponse();
  }
}
