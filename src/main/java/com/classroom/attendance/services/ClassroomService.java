package com.classroom.attendance.services;

import com.classroom.attendance.dto.ActivityResponse;
import com.classroom.attendance.models.Timeslot;
import com.classroom.attendance.repositories.ActivityRepository;
import com.classroom.attendance.repositories.ClassroomRepository;
import com.classroom.attendance.repositories.TimeslotRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

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

  public ActivityResponse getActivity(String reference) {
    ObjectMapper mapper = new ObjectMapper();
    ActivityResponse response = null;
    //Check if a provided string a valid UUID?
    boolean isValid = validateUUID(reference);
    boolean isCurrent = false;
    if (isValid) {
      // Get the current Activity code as per the current time
      var activity = activityRepository.findActivityByActivityCode(getActivityCode(new Date(), reference));
      response = mapper.convertValue(activity, ActivityResponse.class);
    }
    return response;
  }

  protected String getActivityCode(Date input, String reference) {
    boolean allow = false;
    String currentActivityCode = "";
    // Get Current timeslot associated with the classroom
    var classroom = classroomRepository.findClassroomByReference(reference);
    //var timeslot = timeslotRepository.findById(classroom.getTimeslotId());
    var timeslots = classroom.getTimeslots();
    for (Timeslot slot : timeslots){
      //if (timeslot.isPresent()) {
        // Evaluate if it falls within the current time?
        long difference = input.getTime() - slot.getFromTime().getTime();
        if (Math.abs(difference) <= THRESHOLD) {
          allow = true;
          currentActivityCode = slot.getActivityCode();
        }
      }
    return currentActivityCode;
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
