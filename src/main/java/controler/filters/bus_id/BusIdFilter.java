package controler.filters.bus_id;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.regex.Pattern;

@WebFilter(filterName = "bus_id_getter", urlPatterns = "/bus-number/*")
public class BusIdFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {

        String requestURI = ((HttpServletRequest) servletRequest).getRequestURI();

        String delimiter = "/";
        Pattern pattern = Pattern.compile(
                delimiter, Pattern.CASE_INSENSITIVE);

        String[] result = pattern.split(requestURI);

        ((HttpServletRequest) servletRequest).setAttribute("bus_id", (result[result.length - 1]));

        RequestDispatcher rd = servletRequest.getRequestDispatcher("/user/ticket-sale");

        try {
            rd.forward(servletRequest, servletResponse);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }


    }

}
