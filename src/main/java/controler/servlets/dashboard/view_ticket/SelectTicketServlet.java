package controler.servlets.dashboard.view_ticket;

import domain.Ticket;
import util.ApplicationContext;
import util.SecurityContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "bought_ticket_selector", urlPatterns = "/user/select-ticket")
public class SelectTicketServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        List<Ticket> tickets = ApplicationContext.getTicketService().getByPassenger(SecurityContext.getPassenger());

        StringBuilder pageHtml = new StringBuilder();

        pageHtml.append("<!DOCTYPE html>\n" +
                "<html lang=\"fa\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <style>\n" +
                "\n" +
                "        .secondrow{\n" +
                "            text-align: center;\n" +
                "            background-color: gold;\n" +
                "        }\n" +
                "        \n" +
                "        .firstrow{\n" +
                "            text-align: center;\n" +
                "            background-color: brown;\n" +
                "            color: white;\n" +
                "        }\n" +
                "\n" +
                "        td{\n" +
                "            border: 1px solid black;\n" +
                "            \n" +
                "\n" +
                "\n" +
                "        }\n" +
                "\n" +
                "        table{\n" +
                "            width: 100%;\n" +
                "            border-collapse: collapse;\n" +
                "        }\n" +
                "\n" +
                "        .oddrow{\n" +
                "            height: 40px;\n" +
                "            text-align: center;\n" +
                "            background-color: tan;\n" +
                "        }\n" +
                "\n" +
                "        .evenrow{\n" +
                "            height: 40px;\n" +
                "            text-align: center;\n" +
                "            background-color: beige;\n" +
                "        }\n" +
                "\n" +
                "        a{\n" +
                "            color: black;\n" +
                "        }\n" +
                "\n" +
                "\n" +
                "\n" +
                "    </style>\n" +
                "    <title> نمایش بلیط های خریداری شده</title>\n" +
                "</head>\n" +
                "\n" +
                "<body >\n" +
                "\n" +
                "    <table >\n" +
                "\n" +
                "        <tr class=\"firstrow\">\n" +
                "\n" +
                "            <td colspan=\"3\">لیست بلیط های خریداری شده</td>\n" +
                "            \n" +
                "        </tr>\n" +
                "\n" +
                "        <tr class=\"secondrow\">\n" +
                "\n" +
                "            <td>تاریخ</td>\n" +
                "\n" +
                "            <td>شناسه بلیط</td>\n" +
                "\n" +
                "            <td>انتخاب</td>\n" +
                "\n" +
                "        </tr>");

        int ticketNumber = 1;

        for (Ticket t : tickets) {

            if (ticketNumber % 2 == 1)
                pageHtml.append("<tr class=\"oddrow\">\n");
            else
                pageHtml.append("<tr class=\"evenrow\">\n");

            pageHtml.append("              <td>").append(t.getBus().getDepartureDate()).append("</td>\n").append("              <td>").append(t.getId()).append("</td>\n").append("             <td>\n").append("                  <a href=\"http://localhost:8080/bus_terminal_war_exploded/ticket-number/").append(t.getId()).append("\"").append(">مشاهده بلیط</a>\n").append("             </td>\n").append("         </tr>\n");

            ticketNumber++;

        }

        pageHtml.append("   </table>\n").append("</body>\n").append("</html>");

        try {

            resp.setContentType("text/html");

            resp.setCharacterEncoding("UTF-8");

            PrintWriter out = resp.getWriter();

            out.println(pageHtml);

            out.close();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }
}
