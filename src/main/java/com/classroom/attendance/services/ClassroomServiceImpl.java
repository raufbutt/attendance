package com.classroom.attendance.services;

import com.classroom.attendance.dto.ActivityResponse;
import com.classroom.attendance.exceptions.BadRequestException;
import com.classroom.attendance.exceptions.ResourceNotFoundException;
import com.classroom.attendance.models.Timeslot;
import com.classroom.attendance.repositories.ActivityRepository;
import com.classroom.attendance.repositories.ClassroomRepository;
import com.classroom.attendance.repositories.TimeslotRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClassroomServiceImpl implements ClassroomService{

  private final ActivityRepository activityRepository;
  private final ClassroomRepository classroomRepository;

  private final TimeslotRepository timeslotRepository;

  private final ObjectMapper mapper;

  private long THRESHOLD = 900000; //15 minutes in milliseconds

  public ActivityResponse getActivity(String reference) {
    //Check if a provided string a valid UUID?
    if (isValidUUID(reference)) {
      // Get the current Activity code as per the current provided time
      String code = getActivityCode(new Date(), reference);
      var activity = activityRepository.findActivityByActivityCode(code)
          .orElseThrow(
              () -> new ResourceNotFoundException(HttpStatus.NOT_FOUND, "No Activity Found. Try later. (CODE 404)")
          );
      return mapper.convertValue(activity, ActivityResponse.class);
    }
      throw new BadRequestException(HttpStatus.BAD_REQUEST, "Bad Request. Invalid Class reference.");
  }

  protected String getActivityCode(Date input, String reference) {
    String currentActivityCode = "";
    // Get Current timeslot associated with the classroom
    var classroom = classroomRepository.findClassroomByReference(reference)
        .orElseThrow(
            () -> new ResourceNotFoundException(HttpStatus.NOT_FOUND, String.format("No Classroom Found against reference - %s", reference)
        ));

    var timeslots = classroom.getTimeslots();
    for (Timeslot slot : timeslots){
        // Evaluate if it falls within the current time?
        long difference = input.getTime() - slot.getFromTime().getTime();
        if (Math.abs(difference) <= THRESHOLD) {
          currentActivityCode = slot.getActivityCode();
          break;
        }
      }
    return currentActivityCode;
  }

  private boolean isValidUUID(String input){
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
