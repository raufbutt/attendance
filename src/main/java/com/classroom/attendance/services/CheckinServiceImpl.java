package com.classroom.attendance.services;

import com.classroom.attendance.models.Checkin;
import com.classroom.attendance.repositories.CheckinRepository;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckinServiceImpl implements CheckinService{

  private final CheckinRepository checkinRepository;

    public boolean isAlreadyCheckedin(String activityCode, String userId, Date logTime){
      var existingCheckin = checkinRepository.findCheckinByActivityCodeAndCheckedinByAndLogTimeIs(
          activityCode, userId, logTime);
      return existingCheckin.isPresent();
    }

    public Checkin create(Checkin attendance){
      return checkinRepository.saveAndFlush(attendance);
    }
}
