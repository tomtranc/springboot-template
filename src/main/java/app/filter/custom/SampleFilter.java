package app.filter.custom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class SampleFilter implements Filter {

  Logger LOG = LoggerFactory.getLogger(this.getClass());

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
          throws IOException, ServletException {

    HttpServletRequest req = (HttpServletRequest) servletRequest;
    LOG.debug("Filter {} was invoked for req : {}", this.getClass().getCanonicalName(), req.getRequestURI());

    filterChain.doFilter(servletRequest, servletResponse);
    LOG.debug("Filter {} after chain for req : {}", this.getClass().getCanonicalName(), req.getRequestURI());
  }

  @Override
  public void destroy() {

  }
}
