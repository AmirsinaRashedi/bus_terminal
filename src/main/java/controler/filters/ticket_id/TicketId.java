package controler.filters.ticket_id;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.regex.Pattern;

@WebFilter(filterName = "ticket_id_catcher", urlPatterns = "/ticket-number/*")
public class TicketId implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {

        String requestURI = ((HttpServletRequest) servletRequest).getRequestURI();

        String delimiter = "/";
        Pattern pattern = Pattern.compile(
                delimiter, Pattern.CASE_INSENSITIVE);

        String[] result = pattern.split(requestURI);

        ((HttpServletRequest) servletRequest).setAttribute("ticket_id", (result[result.length - 1]));

        RequestDispatcher rd = servletRequest.getRequestDispatcher("/user/view-ticket");

        try {
            rd.forward(servletRequest, servletResponse);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }


    }

}
