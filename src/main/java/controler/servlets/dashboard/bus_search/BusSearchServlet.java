package controler.servlets.dashboard.bus_search;

import domain.Bus;
import util.ApplicationContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "bus-search", urlPatterns = "/user/bus-search")
public class BusSearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        List<String> origins = ApplicationContext.getBusService().getAllOrigins();

        List<String> destinations = ApplicationContext.getBusService().getAllDestinations();

        if (origins == null || destinations == null) {

            try {

                resp.sendRedirect("http://localhost:8080/bus_terminal_war_exploded/failed/no_result");

            } catch (IOException e) {

                e.printStackTrace();

            }

        }

        StringBuilder pageHtml = new StringBuilder();

        pageHtml.append("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "   <style>\n" +
                "        .placeholdertext{\n" +
                "        color: gray;\n" +
                "        }\n" +
                "\n" +
                "        .persiantext{\n" +
                "            text-align: right;\n" +
                "        }\n" +
                "\n" +
                "\n" +
                "        .inlineblock{\n" +
                "            display: inline-block;\n" +
                "        }\n" +
                "\n" +
                "        #searchbox{\n" +
                "            \n" +
                "            width: 60%;\n" +
                "            margin: 20%;\n" +
                "\n" +
                "\n" +
                "        }\n" +
                "\n" +
                "        #searchbody{\n" +
                "            background-color: gray;\n" +
                "        }\n" +
                "\n" +
                "        #searchsubmitnutton{\n" +
                "            background-color: darkblue;\n" +
                "        }\n" +
                "\n" +
                "\n" +
                "\n" +
                "    </style>" +
                "    <title>جستوجوی بلیط</title>\n" +
                "</head>\n" +
                "<body id=\"searchbody\" class=\"persiantext\">\n" +
                "\n" +
                "    <div id=\"searchbox\">\n" +
                "\n" +
                "        <form action=\"bus-search\" method=\"post\">\n" +
                "\n" +
                "            <input id=\"searchsubmitnutton\" class=\"inlineblock\" type=\"submit\" value=\"جستوجوی آنلاین\">\n" +
                "\n" +
                "            <div class=\"inlineblock persiantext\">\n" +
                "                \n" +
                "                <label>تاریخ\n" +
                "                    <br>\n" +
                "                    <input type=\"date\" name=\"date\">\n" +
                "                </label>\n" +
                "            </div>\n" +
                "\n" +
                "            <div class=\"inlineblock persiantext\">\n" +
                "\n" +
                "                <label > مقصد\n" +
                "                    <br>\n" +
                "                    <select class=\"persiantext\" name=\"destination\">\n" +
                "                        <option class=\"placeholdertext\" value=\"\" disabled selected hidden>...لطفا مقصد سرویس را وارد کنید</option>");

        for (String destination : destinations) {

            pageHtml.append("    <option value=").append(destination).append(">").append(destination).append("</option>\n");

        }

        pageHtml.append("</select>\n" +
                "\n" +
                "                </label>\n" +
                "\n" +
                "            </div>\n" +
                "\n" +
                "            <div class=\"inlineblock persiantext\">\n" +
                "\n" +
                "                <label > مبدا\n" +
                "                    <br>\n" +
                "                    <select class=\"persiantext\" name=\"origin\" >\n" +
                "                        <option class=\"placeholdertext\" value=\"\" style=\"color:gray;\" disabled selected hidden>...لطفا مبدا سرویس را وارد کنید</option>");

        for (String origin : origins) {

            pageHtml.append("    <option value=").append(origin).append(">").append(origin).append("</option>\n");

        }

        pageHtml.append("</select>\n" +
                "\n" +
                "                </label>\n" +
                "\n" +
                "            </div>\n" +
                "\n" +
                "            \n" +
                "\n" +
                "        </form>\n" +
                "        \n" +
                "    </div>\n" +
                "\n" +
                "\n" +
                "    \n" +
                "</body>\n" +
                "</html>");

        resp.setContentType("text/html");

        resp.setCharacterEncoding("UTF-8");

        try {

            PrintWriter out = resp.getWriter();

            out.println(pageHtml);

            out.close();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        String origin = req.getParameter("origin");

        String destination = req.getParameter("destination");

        LocalDate departureDate = LocalDate.parse(req.getParameter("date"));

        List<Bus> foundBuses = ApplicationContext.getBusService().findWithOriginAndDestinationAndDate(origin, destination, departureDate);

        if (foundBuses == null || foundBuses.size() == 0) {

            try {

                resp.sendRedirect("http://localhost:8080/bus_terminal_war_exploded/failed/no_result");

            } catch (IOException e) {

                e.printStackTrace();

            }

        }

        try {

            StringBuilder pageHtml = new StringBuilder();

            pageHtml.append("<!DOCTYPE html>\n" +
                    "<html lang=\"fa\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <style>\n" +
                    "\n" +
                    "        .firstrow{\n" +
                    "            text-align: right;\n" +
                    "            background-color: gold;\n" +
                    "        }\n" +
                    "        \n" +
                    "        .secondrow{\n" +
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
                    "            height: 55px;\n" +
                    "            text-align: center;\n" +
                    "            background-color: tan;\n" +
                    "        }\n" +
                    "\n" +
                    "        .evenrow{\n" +
                    "            height: 55px;\n" +
                    "            text-align: center;\n" +
                    "            background-color: beige;\n" +
                    "        }\n" +
                    "\n" +
                    "        .button {\n" +
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
                    "            color: rgb(129, 27, 44);\n" +
                    "            box-shadow: 0 5px 10px rgb(129, 27, 44);\n" +
                    "        }\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "    </style>\n" +
                    "    <title>نمایش بلیط</title>\n" +
                    "</head>\n" +
                    "\n" +
                    "<body >\n" +
                    "\n" +
                    "    <table >\n" +
                    "\n" +
                    "        <tr class=\"firstrow\">\n");

            pageHtml.append("<td>").append(departureDate).append(":تاریخ حرکت</td>");

            pageHtml.append("<td colspan=\"2\">").append(origin).append("-").append(destination).append(":مسیر</td>");

            pageHtml.append("  </tr>\n" +
                    "\n" +
                    "        <tr class=\"secondrow\">\n" +
                    "\n" +
                    "            <td>شناسه سفر</td>\n" +
                    "\n" +
                    "            <td>ساعت حرکت</td>\n" +
                    "\n" +
                    "            <td>انتخاب</td>\n" +
                    "\n" +
                    "        </tr>");

            int busNumber = 1;

            for (Bus b : foundBuses) {

                if (busNumber % 2 == 1)
                    pageHtml.append("<tr class=\"oddrow\">\n");
                else
                    pageHtml.append("<tr class=\"evenrow\">\n");

                pageHtml.append("              <td>").append(b.getId()).append("</td>\n").append("              <td>").append(b.getDepartureTime()).append("</td>\n").append("             <td>\n").append("                   <a href=\"http://localhost:8080/bus_terminal_war_exploded/bus-number/").append(b.getId()).append("\"><button class=\"button glow-button\">خرید</button></a>\n").append("             </td>\n").append("         </tr>\n");

                busNumber++;

            }

            pageHtml.append("   </table>\n").append("</body>\n").append("</html>");

            resp.setCharacterEncoding("UTF-8");

            PrintWriter out = resp.getWriter();

            out.println(pageHtml);

            out.close();

        } catch (IOException e) {

            e.printStackTrace();

        }


    }
}
