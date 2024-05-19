package com.classroom.attendance.services;

import com.classroom.attendance.models.Checkin;
import java.util.Date;

public interface CheckinService {
  boolean isAlreadyCheckedin(String activityCode, String userId, Date logTime);

  Checkin create(Checkin attendance);

}
