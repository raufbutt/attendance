package com.classroom.attendance.services;

import com.classroom.attendance.dto.ActivityResponse;
import com.classroom.attendance.exceptions.BadRequestException;
import com.classroom.attendance.exceptions.ResourceConflictException;
import com.classroom.attendance.exceptions.ResourceNotFoundException;
import com.classroom.attendance.models.Checkin;
import com.classroom.attendance.models.Timeslot;
import com.classroom.attendance.repositories.ActivityRepository;
import com.classroom.attendance.repositories.ClassroomRepository;
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
  private final CheckinService checkinService;

  private final ObjectMapper mapper = new ObjectMapper();

  private final long THRESHOLD = 15; //15 minutes
  private final long THRESHOLD_MILLIS = 15*60*1000; //15 minutes

  public ActivityResponse getActivity(String reference) {
    //Check if a provided string a valid UUID?
    if (isValidUUID(reference)) {
      // Get the current Activity code as per the current provided time
      String code = getActivityCode(new Date(), reference);
      var activity = activityRepository.findActivityByActivityCode(code)
          .orElseThrow(
              () -> new ResourceNotFoundException(HttpStatus.NOT_FOUND, "\nNo Activity Found. Try later!")
          );
      return mapper.convertValue(activity, ActivityResponse.class);
    }
      throw new BadRequestException(HttpStatus.BAD_REQUEST, "\nBad Request. Invalid Class reference.");
  }

  public String getActivityCode(Date input, String reference) {
    String currentActivityCode = "";
    // Get Current timeslot associated with the classroom
    var classroom = classroomRepository.findClassroomByReference(reference)
        .orElseThrow(
            () -> new ResourceNotFoundException(HttpStatus.NOT_FOUND, String.format("\nNo Classroom Found against reference - %s", reference)
        ));

    var timeslots = classroom.getTimeslots();

    for (Timeslot slot : timeslots){
        // Evaluate if it falls within the current time?
        long slotMins = ((slot.getFromTime().getTime() % 86400000) / 60000);
        long currenMins = ((input.getTime() % 86400000) / 60000);
        long difference = slotMins - currenMins;

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
    // ASSUMPTION: A student is authenticated and a security token would come in the header
    // from where the student Id can be obtained.
    String user = "Student_Id";

    /* NOTE: To avoid forgery and to maintain security, we would implement solutions
    // such as Authentication via Single SignOn, Custom Request Header, CSRF token
    */

    //Fetch the available activity
    ActivityResponse response = getActivity(reference);
    response.setStudent(user);
    Date currentDate = new Date();

    // Check for an existing check-in
    // Using checkin service, get checkin for the current Activity and Student and logTime is today's
    if (checkinService.isAlreadyCheckedin(response.getActivityCode(), user, currentDate)){
      throw new ResourceConflictException(HttpStatus.CONFLICT, "Already Checked in. Contact Support.");
    }

    //Prepare the checkin
    Checkin attendance = Checkin.builder()
          .activityCode(response.getActivityCode())
          .reference(reference)
          .checkedinBy(user)
          .logTime(currentDate)
          .build();

    checkinService.create(attendance);
    return response;
  }
}
