package controler.servlets.create_bus;

import util.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;

@WebServlet(name = "create_bus", urlPatterns = "/create-bus")
public class CreateBusServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String createFlightForm = "<form name=\"createFlightForm\" method=\"post\" action=\"create-bus\">\n" +
                "    Origin: <input type=\"text\" name=\"origin\"/> <br/>\n" +
                "    Destination: <input type=\"text\" name=\"destination\"/> <br/>\n" +
                "    price: <input type=\"text\" name=\"price\"/> <br/>\n" +
                "    number of available seats: <input type=\"text\" name=\"available seats\"/> <br/>\n" +
                "    date: <input type=\"date\" name=\"date\"/> <br/>\n" +
                "    time: <input type=\"time\" name=\"time\"/> <br/>\n" +
                "    <input type=\"submit\" value=\"create\" />\n" +
                "</form>";

        resp.setContentType("text/html");

        try {

            PrintWriter out = resp.getWriter();

            out.println(createFlightForm);

            out.close();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String origin = req.getParameter("origin");

        String destination = req.getParameter("destination");

        Integer price = Integer.parseInt(req.getParameter("price"));

        Integer availableSeats = Integer.parseInt(req.getParameter("available seats"));

        LocalDate date = LocalDate.parse(req.getParameter("date"));

        LocalTime time = LocalTime.parse(req.getParameter("time"));

        if (ApplicationContext.getBusService().createBus(origin, destination, price, availableSeats, date, time)) {

            resp.setContentType("text/html");

            try {

                PrintWriter out = resp.getWriter();

                out.println("<h2>bus created</h2>");

                out.close();

            } catch (IOException e) {

                e.printStackTrace();

            }


        } else {

            resp.setContentType("text/html");

            try {

                PrintWriter out = resp.getWriter();

                out.println("<h2>unable to create bus</h2>");

                out.close();

            } catch (IOException e) {

                e.printStackTrace();

            }


        }

    }

}
