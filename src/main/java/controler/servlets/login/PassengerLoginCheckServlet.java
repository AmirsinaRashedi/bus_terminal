package controler.servlets.login;

import domain.Passenger;
import util.ApplicationContext;
import util.SecurityContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "passenger_login", urlPatterns = "/login")
public class PassengerLoginCheckServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Passenger chosenPassenger = ApplicationContext.getPassengerService().findByUsername(username);

        if (chosenPassenger != null && chosenPassenger.getPassword().equals(password)) {

            SecurityContext.logout();

            SecurityContext.setPassenger(chosenPassenger);

            try {

                resp.sendRedirect("http://localhost:8080/bus_terminal_war_exploded/user");

            } catch (IOException e) {

                e.printStackTrace();

            }

        } else {

            try {

                resp.sendRedirect("http://localhost:8080/bus_terminal_war_exploded/failed/login");

            } catch (IOException e) {

                e.printStackTrace();

            }

        }


    }

}
