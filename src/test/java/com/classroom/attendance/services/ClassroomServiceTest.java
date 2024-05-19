package com.classroom.attendance.services;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.classroom.attendance.exceptions.ResourceNotFoundException;
import com.classroom.attendance.models.Activity;
import com.classroom.attendance.models.Classroom;
import com.classroom.attendance.models.Timeslot;
import com.classroom.attendance.repositories.ActivityRepository;
import com.classroom.attendance.repositories.ClassroomRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
public class ClassroomServiceTest {

  @InjectMocks
  private ClassroomServiceImpl classroomService;

  @Mock
  private ClassroomRepository classroomRepository;

  @Mock
  private ActivityRepository activityRepository;

  @Mock
  private Classroom mockClassroom;

  @Mock
  private Activity mockActivity;

  @Mock
  private ObjectMapper mockMapper;

  private final String CLASS_REFERENCE = "88a4b94f-569d-48df-95aa-9badf69cb6ef";

  @Test
  void getActivityCode_success(){
    Date currentDate = mock(Date.class);
    Timeslot timeslot = Timeslot.builder()
        .activityCode("ACT001")
        .timeslotId(1L)
        .fromTime(mock(Date.class))
        .toTime(mock(Date.class))
        .build();
    List<Timeslot> lstSlots = List.of(timeslot);
    when(classroomRepository.findClassroomByReference(CLASS_REFERENCE)).thenReturn(
        Optional.of(mockClassroom));
    when(mockClassroom.getTimeslots()).thenReturn(lstSlots);

    String actualCode = classroomService.getActivityCode(currentDate, CLASS_REFERENCE);
    assertEquals("ACT001", actualCode);
  }

  @Test
  void getActivityCode_failure(){
    Date currentDate = mock(Date.class);
    List<Timeslot> lstSlots = List.of();
    when(classroomRepository.findClassroomByReference(CLASS_REFERENCE)).thenReturn(
        Optional.ofNullable(mockClassroom));
    when(mockClassroom.getTimeslots()).thenReturn(lstSlots);
    String actualCode = classroomService.getActivityCode(currentDate, CLASS_REFERENCE);
    assert(actualCode).isBlank();
    assertEquals("", actualCode);
  }

  @Test
  void getCurrentActivity_404NotFound(){
    when(classroomRepository.findClassroomByReference(CLASS_REFERENCE)).thenReturn(
        Optional.ofNullable(mockClassroom));
    when(activityRepository.findActivityByActivityCode(anyString()))
          .thenThrow(new ResourceNotFoundException(HttpStatus.NOT_FOUND, "\nNo Activity Found. Try later!"));
    assertThatThrownBy(() -> classroomService.getActivity(CLASS_REFERENCE))
        .isInstanceOf(ResourceNotFoundException.class)
        .hasMessageContaining("No Activity Found. Try later!");
  }

}
