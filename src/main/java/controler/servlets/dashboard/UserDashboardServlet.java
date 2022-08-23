package controler.servlets.dashboard;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "dashboard", urlPatterns = "/user")
public class UserDashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        String dashboardHtml = "<!DOCTYPE html>\n" +
                "<html lang=\"fa\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <style>\n" +
                "\n" +
                "        *{\n" +
                "            margin: 0;\n" +
                "            box-sizing: border-box;\n" +
                "        }\n" +
                "\n" +
                "        .pushable {\n" +
                "            background: hsl(340deg 100% 32%);\n" +
                "            border-radius: 12px;\n" +
                "            border: none;\n" +
                "            padding: 0;\n" +
                "            cursor: pointer;\n" +
                "            outline-offset: 4px;\n" +
                "        }\n" +
                "        .front {\n" +
                "            display: block;\n" +
                "            padding: 12px 42px;\n" +
                "            border-radius: 12px;\n" +
                "            font-size: 1.25rem;\n" +
                "            background: hsl(345deg 100% 47%);\n" +
                "            color: white;\n" +
                "            transform: translateY(-6px);\n" +
                "        }\n" +
                "\n" +
                "        .pushable:active .front {\n" +
                "            transform: translateY(-2px);\n" +
                "        }\n" +
                "\n" +
                "        tr , td{\n" +
                "            padding: 20px;\n" +
                "        }\n" +
                "\n" +
                "        table{\n" +
                "            margin-left:25%;\n" +
                "            margin-top: 100px;\n" +
                "        }\n" +
                "\n" +
                "        body{\n" +
                "            background-color: gold;\n" +
                "        }\n" +
                "\n" +
                "    </style>\n" +
                "    <title> داشبورد</title>\n" +
                "</head>\n" +
                "\n" +
                "<body >\n" +
                "\n" +
                "\n" +
                "    <table>\n" +
                "\n" +
                "        <tr>\n" +
                "\n" +
                "            <td><a href=\"http://localhost:8080/bus_terminal_war_exploded/user/select-ticket\"><button class=\"pushable\">\n" +
                "                <span class=\"front\">\n" +
                "                  مشاهده بلیط های خریداری شده\n" +
                "                </span>\n" +
                "              </button></a></td>\n" +
                "\n" +
                "            <td><a href=\"http://localhost:8080/bus_terminal_war_exploded/user/bus-search\"><button class=\"pushable\">\n" +
                "                <span class=\"front\">\n" +
                "                  خرید بلیط\n" +
                "                </span>\n" +
                "              </button></a></td>\n" +
                "\n" +
                "        </tr>\n" +
                "\n" +
                "    </table>\n" +
                "\n" +
                "\n" +
                "</body>\n" +
                "</html>\n";

        resp.setCharacterEncoding("UTF-8");

        resp.setContentType("text/html");

        try {

            PrintWriter out = resp.getWriter();

            out.println(dashboardHtml);

            out.close();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }
}
