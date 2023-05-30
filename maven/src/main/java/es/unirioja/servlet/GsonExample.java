package es.unirioja.servlet;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import es.unirioja.paw.model.Cliente;
import es.unirioja.paw.model.Direccion;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "GsonExample", urlPatterns = {"/gson"})
public class GsonExample extends HttpServlet {

    private Faker faker = new Faker(new Locale("en"));

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("This is a GET http request");
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("This is a POST http request");
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int delay = parseDelayParameter(request);
        if (delay > 0) {
            simulateLongOperation(delay);
        }

        Cliente cliente = buildCliente();
        logger.info("Cliente {}", cliente.getNombre());

//        String json = buildSimpleJson_page23(cliente);
//        System.out.println(json);

        String json = buildSimpleJson_page24(cliente);
        System.out.println(json);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.println(json);
    }

    private String buildSimpleJson_page23(Cliente cliente) {
        Gson gson = new Gson();
        String json = gson.toJson(cliente);
        return json;
    }

    private String buildSimpleJson_page24(Cliente cliente) {
//        Gson gson = new Gson();
        Gson gson = new GsonBuilder()
                .serializeNulls()
                .setPrettyPrinting().create();
        String json = gson.toJson(cliente);
        return json;
    }

    private Cliente buildCliente() {
        Address address = faker.address();
        Direccion d = new Direccion(address.streetAddress(), address.cityName(), address.state(), address.zipCode());
        Cliente c = new Cliente(
                faker.name().username(),
                faker.code().imei(),
                faker.name().name(),
                faker.internet().emailAddress(),
                null, // el telefono es null
                d);
        return c;
    }

    private void simulateLongOperation(int nSeconds) {
        if (nSeconds <= 0) {
            return;
        }
        logger.info("Sleeping for {} seconds", nSeconds);
        try {
            Thread.sleep(nSeconds * 1000);
        } catch (InterruptedException ex) {
            logger.error("Error sleeping", ex);
        }
        logger.info("Job done!");
    }

    private int parseDelayParameter(HttpServletRequest request) {
        String delay = request.getParameter("delay");
        if (delay == null || delay.isEmpty()) {
            return 0;
        }
        try {
            int delayAsInt = Integer.parseInt(delay);
            return delayAsInt;
        } catch (NumberFormatException ex) {
            logger.error("Error parsing delay", ex);
        }
        logger.info("No delay in request");
        return 0;
    }

}
