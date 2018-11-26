package app.rest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CustomerController {

  @RequestMapping("/")
  public String index(HttpServletRequest request) {
    return "Endpoint was hit: " + request.getServletPath();
  }

  @RequestMapping("/endpoint1")
  public String endpoint1(HttpServletRequest request) {
    return "Endpoint was hit: " + request.getServletPath();
  }

}