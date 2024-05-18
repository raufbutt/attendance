package com.classroom.attendance.services;

import com.classroom.attendance.dto.ActivityResponse;
import com.classroom.attendance.exceptions.BadRequestException;
import com.classroom.attendance.exceptions.ResourceConflictException;
import com.classroom.attendance.exceptions.ResourceNotFoundException;
import com.classroom.attendance.models.Checkin;
import com.classroom.attendance.models.Timeslot;
import com.classroom.attendance.repositories.ActivityRepository;
import com.classroom.attendance.repositories.CheckinRepository;
import com.classroom.attendance.repositories.ClassroomRepository;
import com.classroom.attendance.repositories.TimeslotRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClassroomServiceImpl implements ClassroomService{

  private final ActivityRepository activityRepository;
  private final ClassroomRepository classroomRepository;
  private final CheckinRepository checkinRepository;
  private final TimeslotRepository timeslotRepository;

  private final ObjectMapper mapper = new ObjectMapper();

  private final long THRESHOLD = 900000; //15 minutes in milliseconds

  public ActivityResponse getActivity(String reference) {
    //Check if a provided string a valid UUID?
    if (isValidUUID(reference)) {
      // Get the current Activity code as per the current provided time
      String code = getActivityCode(new Date(), reference);
      var activity = activityRepository.findActivityByActivityCode(code)
          .orElseThrow(
              () -> new ResourceNotFoundException(HttpStatus.NOT_FOUND, "\nNo Activity Found. Try later.")
          );
      return mapper.convertValue(activity, ActivityResponse.class);
    }
      throw new BadRequestException(HttpStatus.BAD_REQUEST, "\nBad Request. Invalid Class reference.");
  }

  protected String getActivityCode(Date input, String reference) {
    String currentActivityCode = "";
    // Get Current timeslot associated with the classroom
    var classroom = classroomRepository.findClassroomByReference(reference)
        .orElseThrow(
            () -> new ResourceNotFoundException(HttpStatus.NOT_FOUND, String.format("\nNo Classroom Found against reference - %s", reference)
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

  @Transactional()
  public ActivityResponse registerCheckin(String reference){
    // ASSUMPTION: A student is authenticated and a security token would come in header
    // from where the student Id can be obtained.
    String user = "Student_Id";
    //Fetch the available activity
    ActivityResponse response = getActivity(reference);
    response.setStudent(user);
    Date currentDate = new Date();

    // Check for an existing check-in
    //Using checkin service, get checkin for the current Activity and Student and loggingdate is today
    var existingCheckin = checkinRepository.findCheckinByActivityCodeAndCheckedinByAndLoggingTimeBetween(
        response.getActivityCode(), user, currentDate, currentDate);

    if (existingCheckin.isPresent()){
      throw new ResourceConflictException(HttpStatus.CONFLICT, "Already Checked in. Contact Support");
    }
    else {
      //Prepare the checkin
      Checkin obj = Checkin.builder()
          .activityCode(response.getActivityCode())
          .checkedinBy(user)
          .loggingTime(currentDate)
          .build();

      checkinRepository.saveAndFlush(obj);
    }
    return response;
  }
}
