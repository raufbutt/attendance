package com.classroom.attendance.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.classroom.attendance.dto.ActivityResponse;
import com.classroom.attendance.enums.Status;
import com.classroom.attendance.models.Activity;
import com.classroom.attendance.models.Classroom;
import com.classroom.attendance.models.Timeslot;
import com.classroom.attendance.repositories.ActivityRepository;
import com.classroom.attendance.repositories.ClassroomRepository;
import com.classroom.attendance.repositories.TimeslotRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ClassroomServiceTest {

  @InjectMocks
  private ClassroomServiceImpl classroomService;

  @Mock
  private ClassroomRepository classroomRepository;

  @Mock
  private TimeslotRepository timeslotRepository;

  @Mock
  private ActivityRepository activityRepository;

  @Test
  void getCurrentActivity_successful() {
    String classReference = "88a4b94f-569d-48df-95aa-9badf69cb6ef";

    ActivityResponse response = new ActivityResponse();
    response.setActivityCode("ACT001");
    response.setStatus(Status.ACTIVE.name());
    response.setClassroomReference(classReference);

    Timeslot timeslot = Timeslot.builder()
        .activityCode("ACT001")
        .timeslotId(1L)
        .fromTime(mock(Date.class))
        .toTime(mock(Date.class))
        .build();
    List<Timeslot> list = List.of(timeslot);
    Classroom mockClassroom = mock(Classroom.class);
    when(classroomRepository.findClassroomByReference(classReference)).thenReturn(
        Optional.ofNullable(mockClassroom));
    when(mockClassroom.getTimeslots()).thenReturn(list);

    ObjectMapper mapper = mock(ObjectMapper.class);
    Activity mockActivity = mock(Activity.class);
    when(classroomService.getActivityCode(mock(Date.class), classReference)).thenReturn(anyString());
    when(activityRepository.findActivityByActivityCode(anyString())).thenReturn(
        Optional.ofNullable(mockActivity));

    when(mapper.convertValue(mockActivity, ActivityResponse.class)).thenReturn(response);

    var actualResponse = classroomService.getActivity(classReference);

    assertNotNull(actualResponse);
    assertEquals("ACT001", actualResponse.getActivityCode());
    assertEquals("ACTIVE", actualResponse.getStatus());
  }

  @Test
  void getActivityCode_success(){
    String classReference = "88a4b94f-569d-48df-95aa-9badf69cb6ef";
    Date currentDate = mock(Date.class);
    Timeslot timeslot = Timeslot.builder()
        .activityCode("ACT001")
        .timeslotId(1L)
        .fromTime(mock(Date.class))
        .toTime(mock(Date.class))
        .build();
    List<Timeslot> list = List.of(timeslot);
    Classroom mockClassroom = mock(Classroom.class);
    when(classroomRepository.findClassroomByReference(classReference)).thenReturn(
        Optional.ofNullable(mockClassroom));
    when(mockClassroom.getTimeslots()).thenReturn(list);
    String activityCode = classroomService.getActivityCode(currentDate, classReference);
    assertEquals("ACT001", activityCode);
  }

  @Test
  void getActivityCode_failure(){
    String classReference = "88a4b94f-569d-48df-95aa-9badf69cb6ef";
    Date currentDate = mock(Date.class);
    List<Timeslot> list = List.of();
    Classroom mockClassroom = mock(Classroom.class);
    when(classroomRepository.findClassroomByReference(classReference)).thenReturn(
        Optional.ofNullable(mockClassroom));
    when(mockClassroom.getTimeslots()).thenReturn(list);
    String activityCode = classroomService.getActivityCode(currentDate, classReference);
    assert(activityCode).isBlank();
    assertEquals("", activityCode);
  }

  @Test
  void getCurrentActivity_404NotFound(){

  }

}
