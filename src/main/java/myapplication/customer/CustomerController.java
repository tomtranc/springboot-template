package myapplication.customer;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CustomerController {

  @RequestMapping("/")
  public String index(HttpServletRequest request) {
    return "Endpoint was hit: " + request.getServletPath();
  }

}