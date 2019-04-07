package app.soap.runtime.generated;

import my.namespace.jaxb.scorecard.PMMLInput;
import my.namespace.jaxb.scorecard.PMMLOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ScorecardEndpoint {

  private static final String NAMESPACE_URI = "http://namespace.my/jaxb/scorecard/";

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "PMMLInput")
  @ResponsePayload
  public PMMLOutput getScore(@RequestPayload PMMLInput request) {
    PMMLOutput response = new PMMLOutput();
    response.setFinalScore(69);
    response.setReasonCode1("good");
    response.setReasonCode2("good");
    response.setReasonCode3("good");
    response.setReasonCode4("good");

    return response;
  }
}