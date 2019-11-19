package app.rest;

import app.model.Customer;
import app.rest.base.BaseRestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class CustomerController extends BaseRestController {

  @GetMapping(value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public String index(HttpServletRequest request) {
    return "Application is up!";
  }

  @GetMapping(value = "/customers", produces = MediaType.TEXT_PLAIN_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public String endpoint1(HttpServletRequest request) {
    return "Endpoint was hit: " + request.getServletPath();
  }

  @PostMapping(value = "/customers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public String postEndpoint(@Valid @RequestBody Customer entity, Errors errors) {
    if (errors.hasErrors()) {
      final String errorMessage = getErrorMessage(errors.getAllErrors());
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage);
    }

    try {
      return mapper.writeValueAsString(entity);
    }
    catch (JsonProcessingException e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to serialize response", e);
    }
  }

  @PutMapping(value = "/customers/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public String putEndpoint(@PathVariable String id, @Valid @RequestBody Customer entity, Errors errors) {
    if (errors.hasErrors()) {
      final String errorMessage = getErrorMessage(errors.getAllErrors());
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage);
    }

    try {
      return mapper.writeValueAsString(entity);
    }
    catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  @DeleteMapping(value = "/customers/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public String deleteEndpoint(@PathVariable String id) {
    return "Deleted: " + id;
  }
}