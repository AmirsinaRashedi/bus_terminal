package controler.servlets.dashboard.view_ticket;

import domain.Bus;
import domain.Passenger;
import domain.Ticket;
import util.ApplicationContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "view_ticket", urlPatterns = "/user/view-ticket")
public class ViewTicketServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        String ticketId = ((String) req.getAttribute("ticket_id"));

        Ticket ticket = ApplicationContext.getTicketService().findById(Long.parseLong(ticketId));

        Passenger passenger = ticket.getPassenger();

        Bus bus = ticket.getBus();

        String gender;

        if (ticket.getGender() == 'm')
            gender = "male";
        else
            gender = "female";

        String viewTicketHtml = "<!DOCTYPE html>\n" +
                "<html lang=\"fa\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <style>\n" +
                "\n" +
                "        .maincolumn{\n" +
                "            text-align: right;\n" +
                "            background-color: gold;\n" +
                "        }\n" +
                "        \n" +
                "        .bigrow{\n" +
                "            text-align: center;\n" +
                "            background-color: brown;\n" +
                "            color: white;\n" +
                "        }\n" +
                "\n" +
                "        td{\n" +
                "            border: 1px solid black;\n" +
                "            width: 50%;\n" +
                "            height: 30px;\n" +
                "        }\n" +
                "\n" +
                "        table{\n" +
                "            width: 60%;\n" +
                "            border-collapse: collapse;\n" +
                "            margin: 20px 20%;\n" +
                "        }\n" +
                "\n" +
                "        .oddrow{\n" +
                "            text-align: left;\n" +
                "            background-color: tan;\n" +
                "        }\n" +
                "\n" +
                "        .evenrow{\n" +
                "            text-align: left;\n" +
                "            background-color: beige;\n" +
                "        }\n" +
                "\n" +
                "\n" +
                "\n" +
                "    </style>\n" +
                "    <title> مشخصات بلیط</title>\n" +
                "</head>\n" +
                "\n" +
                "<body >\n" +
                "\n" +
                "    <form action=\"/bus_terminal_war_exploded/user/view-ticket\" method=\"post\">\n" +
                "    \n" +
                "        <input type=\"hidden\" name=\"ticket_id\" value=\"" +
                ticket.getId() +
                "\">" +
                "        <table >\n" +
                "\n" +
                "            <tr class=\"bigrow\">\n" +
                "                <td colspan=\"2\">بلیط اتوبوس</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td class=\"oddrow\">" + ticket.getId() + "</td>\n" +
                "                <td class=\"maincolumn\"> شناسه بلیط</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td class=\"evenrow\">" + passenger.getFirstname() + " " + passenger.getLastname() + "</td>\n" +
                "                <td class=\"maincolumn\">نام</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td class=\"oddrow\">" + gender + "</td>\n" +
                "                <td class=\"maincolumn\">جنسیت</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td class=\"evenrow\">" + bus.getOrigin() + "</td>\n" +
                "                <td class=\"maincolumn\">مبدا</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td class=\"oddrow\">" + bus.getDestination() + "</td>\n" +
                "                <td class=\"maincolumn\">مقصد</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td class=\"evenrow\">" + bus.getDepartureDate() + "</td>\n" +
                "                <td class=\"maincolumn\">تاریخ حرکت</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td class=\"oddrow\">" + bus.getDepartureTime() + "</td>\n" +
                "                <td class=\"maincolumn\">ساعت حرکت</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td class=\"evenrow\">" + bus.getId() + "</td>\n" +
                "                <td class=\"maincolumn\">شناسه سفر</td>\n" +
                "            </tr>\n" +
                "            <tr class=\"bigrow\">\n" +
                "                <td colspan=\"2\"><label > لغو بلیط<input type=\"submit\" hidden></label></td>\n" +
                "            </tr>\n" +
                "        \n" +
                "        \n" +
                "        </table>\n" +
                "\n" +
                "    </form>\n" +
                "\n" +
                "\n" +
                "</body>\n" +
                "</html>\n";

        try {

            resp.setContentType("text/html");

            resp.setCharacterEncoding("UTF-8");

            PrintWriter out = resp.getWriter();

            out.println(viewTicketHtml);

            out.close();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        Ticket deletedTicket = ApplicationContext.getTicketService().findById(Long.parseLong(req.getParameter("ticket_id")));

        Bus ticketBus = deletedTicket.getBus();

        ApplicationContext.getTicketService().delete(deletedTicket);

        ApplicationContext.getBusService().beginTransaction();

        ticketBus.setAvailableSeats(ticketBus.getAvailableSeats() + 1);

        ApplicationContext.getBusService().commitTransaction();


        try {

            resp.setContentType("text/html");

            resp.setCharacterEncoding("UTF-8");

            PrintWriter out = resp.getWriter();

            out.println("<h2>بلیط با موفقیت حذف شد</h2>");

            out.close();

        } catch (IOException e) {

            e.printStackTrace();

        }


    }

}
