package app.soap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

  @Bean
  public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
    MessageDispatcherServlet servlet = new MessageDispatcherServlet();
    servlet.setApplicationContext(applicationContext);
    servlet.setTransformWsdlLocations(true);
    return new ServletRegistrationBean(servlet, "/ws/*");
  }

  @Bean(name = "countries")
  public DefaultWsdl11Definition defaultWsdl1Definition(XsdSchema countriesSchema, @Value("${wsdl.target.ns.countries}") String wsdlTargetNs) {
    DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
    wsdl11Definition.setPortTypeName("CountriesPort");
    wsdl11Definition.setLocationUri("/ws/countries");
    wsdl11Definition.setTargetNamespace(wsdlTargetNs);
    wsdl11Definition.setSchema(countriesSchema);
    return wsdl11Definition;
  }

  @Bean(name = "scorecard")
  public DefaultWsdl11Definition defaultWsdl2Definition(XsdSchema scorecardSchema, @Value("${wsdl.target.ns.scorecard}") String wsdlTargetNs) {
    DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
    wsdl11Definition.setPortTypeName("ScorecardPort");
    wsdl11Definition.setLocationUri("/ws/scorecard");
    wsdl11Definition.setTargetNamespace(wsdlTargetNs);
    wsdl11Definition.setSchema(scorecardSchema);
    return wsdl11Definition;
  }

  @Bean
  public XsdSchema countriesSchema() {
    return new SimpleXsdSchema(new ClassPathResource("wsdl/countries.xsd"));
  }

  @Bean
  public XsdSchema scorecardSchema() {
    return new SimpleXsdSchema(new ClassPathResource("wsdl/DemoScorecard.xsd"));
  }
}