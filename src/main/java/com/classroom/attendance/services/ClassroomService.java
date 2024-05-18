package com.classroom.attendance.services;

import com.classroom.attendance.dto.ActivityResponse;
import com.classroom.attendance.repositories.ActivityRepository;
import com.classroom.attendance.repositories.ClassroomRepository;
import com.classroom.attendance.repositories.TimeslotRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClassroomService {

  private final ActivityRepository activityRepository;
  private final ClassroomRepository classroomRepository;

  private final TimeslotRepository timeslotRepository;

  private long THRESHOLD = 900000; //15 minutes in milliseconds

  public ActivityResponse getActivity(String reference) throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    ActivityResponse response = null;
    //Check if a provided string a valid UUID?
    boolean isValid = validateUUID(reference);
    boolean isCurrent = false;
    if(isValid) {
      // Check the current time
      Date currentDate = new Date();
      LocalDateTime currentTime = LocalDateTime.now(ZoneOffset.UTC);
      isCurrent = allowableDifference(currentDate.getTime(), reference);
    }
    if (isCurrent){
         var activity = activityRepository.findActivityByClassroomReference(reference);
         response = mapper.convertValue(activity, ActivityResponse.class);
    }
    else
      throw new RuntimeException("No Activity Found. Try later.");

    return response;
  }

  private boolean allowableDifference(long input, String reference) {
    boolean allow = false;
    // Get Current timeslot associated with the classroom
    var classroom = classroomRepository.findClassroomByReference(reference);
    var timeslot = timeslotRepository.findById(classroom.getTimeslotId());
    if (timeslot.isPresent()) {
      // Evaluate if it falls within the current time?
      long difference = input - timeslot.get().getFromTime().getTime();
      if (Math.abs(difference) <= THRESHOLD)
        allow = true;
    }
    return allow;
  }

  private boolean validateUUID(String input){
    try{
      UUID.fromString(input);
    } catch (IllegalArgumentException exception) {
      return false;
    }
    return true;
  }

  public ActivityResponse registerCheckin(String reference, String Student){
    return new ActivityResponse();
  }
}
