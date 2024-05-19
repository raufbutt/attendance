package com.classroom.attendance.controllers;


import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.classroom.attendance.services.ClassroomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ClassroomControllerIntegrationTest {

  @MockBean
  private ClassroomService classroomService;

  private final String CLASS_REFERENCE = "88a4b94f-569d-48df-95aa-9badf69cb6ef";

  @Autowired
  private MockMvc mockMvc;

  @BeforeEach
  public void setup() throws Exception {
  }

  @Test
  public void givenClassReference_whenMockMVC_thenVerifyResponse() throws Exception {
    mockMvc.perform(get("/v1/api/classroom/{reference}", CLASS_REFERENCE))
        .andDo(print()).andExpect(status().isOk())
        .andReturn();
  }

  @Test
  public void givenCurrentActivity_whenMockMVC_thenRegisterCheckin() throws Exception {
    mockMvc.perform(post("/v1/api/classroom/{reference}/checkin", CLASS_REFERENCE))
        .andDo(print()).andExpect(status().isCreated())
        .andReturn();
  }

}
