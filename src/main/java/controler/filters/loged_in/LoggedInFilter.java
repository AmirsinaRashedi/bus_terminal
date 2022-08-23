package controler.filters.loged_in;

import util.SecurityContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebFilter(filterName = "logged_in_checker" , urlPatterns = "/user/*")
public class LoggedInFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {

        if (SecurityContext.getPassenger() != null) {

            try {
                filterChain.doFilter(servletRequest, servletResponse);
            } catch (IOException | ServletException e) {
                e.printStackTrace();
            }

        } else {
            try {
                ((HttpServletResponse) servletResponse).sendRedirect("http://localhost:8080/bus_terminal_war_exploded");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

}




