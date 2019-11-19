package app.rest.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.ObjectError;

import java.util.List;

public abstract class BaseRestController {
  protected final Logger LOG = LoggerFactory.getLogger(getClass());
  private static final String EMPTY_ERROR_MESSAGE = "";

  @Autowired
  protected ObjectMapper mapper;

  protected String getErrorMessage(final List<ObjectError> allErrors) {
    if (CollectionUtils.isEmpty(allErrors)) {
      return EMPTY_ERROR_MESSAGE;
    }
    final ObjectError firstError = allErrors.get(0);
    final String errorMessage = String.format("Object '%s', Message: '%s'", firstError.getObjectName(),
            firstError.getDefaultMessage());
    return StringUtils.hasLength(errorMessage) ? errorMessage : EMPTY_ERROR_MESSAGE;
  }
}
