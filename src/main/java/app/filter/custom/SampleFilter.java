package app.filter.custom;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class SampleFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
          throws IOException, ServletException {

    HttpServletRequest req = (HttpServletRequest) servletRequest;
    log.debug("Filter {} was invoked for req : {}", this.getClass().getCanonicalName(), req.getRequestURI());

    filterChain.doFilter(servletRequest, servletResponse);
    log.debug("Filter {} after chain for req : {}", this.getClass().getCanonicalName(), req.getRequestURI());
  }

  @Override
  public void destroy() {

  }
}
