package app.filter;

import app.filter.custom.SampleFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

  @Bean
  public FilterRegistrationBean<SampleFilter> loggingFilter() {
    FilterRegistrationBean<SampleFilter> registrationBean = new FilterRegistrationBean<>();

    registrationBean.setFilter(new SampleFilter());
    registrationBean.addUrlPatterns("/*");

    return registrationBean;
  }
}
