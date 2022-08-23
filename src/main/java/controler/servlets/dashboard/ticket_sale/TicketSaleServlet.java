package controler.servlets.dashboard.ticket_sale;

import domain.Bus;
import domain.Passenger;
import domain.Ticket;
import util.ApplicationContext;
import util.SecurityContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ticket_sale", urlPatterns = "/user/ticket-sale")
public class TicketSaleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        Passenger buyer = SecurityContext.getPassenger();

        String createTicketForm = "<!DOCTYPE html>\n" +
                "<html lang=\"fa\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <style>\n" +
                "\n" +
                "        *{\n" +
                "        margin: 0;\n" +
                "        box-sizing: border-box;\n" +
                "        }\n" +
                "\n" +
                "        body{\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "        table{\n" +
                "            width: 30%;\n" +
                "            border-collapse: collapse;\n" +
                "            margin: 30px 35%;\n" +
                "        }\n" +
                "\n" +
                "        td{\n" +
                "            text-align: center;\n" +
                "            border: 1px solid black;\n" +
                "            height: 50px;\n" +
                "            width: 50%;\n" +
                "            background-color: beige;\n" +
                "        }\n" +
                "\n" +
                "        .button {\n" +
                "            width: 20%;\n" +
                "            text-decoration: none;\n" +
                "            color: rgba(0, 0, 0, 0.8);\n" +
                "            background: rgb(233, 232, 226);\n" +
                "            padding: 4px 7px;\n" +
                "            border-radius: 4px;\n" +
                "            font-weight: normal;\n" +
                "            text-transform: uppercase;\n" +
                "            transition: all 0.2s ease-in-out;\n" +
                "        }\n" +
                "\n" +
                "        .glow-button:hover {\n" +
                "            color: rgb(223, 88, 16);\n" +
                "            box-shadow: 0 0 10px rgb(223, 88, 16);\n" +
                "        }\n" +
                "\n" +
                "\n" +
                "    </style>\n" +
                "    <title>خرید بلیط</title>\n" +
                "</head>\n" +
                "\n" +
                "<body >\n" +
                "\n" +
                "<form action=\"/bus_terminal_war_exploded/user/ticket-sale\" method=\"post\">\n" +
                "    <input type=\"hidden\" name=\"bus_id\" value=\"" +
                ((String) req.getAttribute("bus_id")) +
                "\" > " +
                "    <table>\n" +
                "        \n" +
                "        \n" +
                "            <tr>\n" +
                "                <td>" +
                buyer.getFirstname() +
                " " +
                buyer.getLastname() +
                "</td>\n" +
                "                <td>نام و نام خانوادگی</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>\n" +
                "                    مرد<input type=\"radio\" name=\"gender\" value=\"male\" checked>\n" +
                "                    <input type=\"radio\" name=\"gender\" value=\"female\" >&nbspزن\n" +
                "                </td>\n" +
                "                <td>جنسیت</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td colspan=\"2\"><input class=\"button glow-button\" type=\"submit\" value=\"تایید\"></td>\n" +
                "            </tr>\n" +
                "\n" +
                "\n" +
                "    </table>\n" +
                "</form>\n" +
                "\n" +
                "   \n" +
                "\n" +
                "\n" +
                "</body>\n" +
                "</html>\n";

        resp.setContentType("text/html");

        resp.setCharacterEncoding("UTF-8");

        try {

            PrintWriter out = resp.getWriter();

            out.println(createTicketForm);

            out.close();

        } catch (IOException e) {

            e.printStackTrace();

        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        Bus boughtBus = ApplicationContext.getBusService().findById(Long.parseLong(req.getParameter("bus_id")));

        char gender;

        String fullGender = req.getParameter("gender");

        Passenger buyer = SecurityContext.getPassenger();

        if (fullGender.equals("male"))
            gender = 'm';
        else
            gender = 'f';

        Ticket newTicket = new Ticket();

        newTicket.setPassenger(buyer);

        newTicket.setBus(boughtBus);

        newTicket.setGender(gender);

        ApplicationContext.getTicketService().save(newTicket);

        boughtBus.fillOneSeat();

        if (newTicket.getId() != null) {

            String persianGender;

            if (gender == 'm')
                persianGender = "آقای";
            else
                persianGender = "خانم";

            String ticketApproved = "<!DOCTYPE html>\n" +
                    "<html lang=\"fa\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <style>\n" +
                    "\n" +
                    "        div{\n" +
                    "            background-color: green;\n" +
                    "            color: white;\n" +
                    "            width: 40%;\n" +
                    "            margin: 20px 30%;\n" +
                    "            padding: 20px;\n" +
                    "        }\n" +
                    "\n" +
                    "        body{\n" +
                    "            text-align: center;\n" +
                    "            direction: rtl;\n" +
                    "        }\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "    </style>\n" +
                    "    <title>خرید بلیط موفق</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "\n" +
                    "    <div>\n" +
                    persianGender +
                    "\n" +
                    buyer.getFirstname() +
                    " " +
                    buyer.getLastname() +
                    "،\n" +
                    "\n" +
                    "        خرید بلیط شما با موفقیت انجام شد\n" +
                    "\n" +
                    "        <br>\n" +
                    "        <br>\n" +
                    "\n" +
                    "        شناسه بلیط :\n" +
                    newTicket.getId() +
                    "\n" +
                    "\n" +
                    "\n" +
                    "    </div>\n" +
                    "\n" +
                    "    \n" +
                    "\n" +
                    "    \n" +
                    "</body>\n" +
                    "</html>";

            resp.setContentType("text/html");

            resp.setCharacterEncoding("UTF-8");

            try {

                PrintWriter out = resp.getWriter();

                out.println(ticketApproved);

                out.close();

            } catch (IOException e) {

                e.printStackTrace();

            }


        }


    }

}
