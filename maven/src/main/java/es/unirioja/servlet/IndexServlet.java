package es.unirioja.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "IndexServlet", urlPatterns = {"/index"})
public class IndexServlet extends HttpServlet {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/hello.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        simulateLongOperation();
        
        request.setAttribute("message", "Changes saved successfully!");
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/hello.jsp");
        rd.forward(request, response);
    }

    private void simulateLongOperation() {
        logger.info("Sleeping...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            logger.error("Error sleeping", ex);
        }
        logger.info("Job done!");
    }

}
