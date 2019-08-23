package app.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProjectController.class)
public class TestCustomerController {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private ProjectController customerController;

  @Test
  public void test1() throws Exception {
    String mockedResponse = "endpoint 1 mocked";
    given(customerController.endpoint1(any())).willReturn(mockedResponse);

    mvc.perform(get("/endpoint1"))
            .andExpect(status().isOk())
            .andExpect(content().string(mockedResponse));
  }
}
