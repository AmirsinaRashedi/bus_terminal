package controler.servlets.message;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "no_result_error", urlPatterns = "/failed/no-result")
public class NoResultFoundServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        resp.setContentType("text/html");

        try {

            PrintWriter out = resp.getWriter();

            out.println("<h1>NO RESULTS FOUND</h1>");

            out.close();


        } catch (IOException e) {

            e.printStackTrace();

        }


    }

}
