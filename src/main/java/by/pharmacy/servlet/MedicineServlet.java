package by.pharmacy.servlet;

import by.pharmacy.service.MedicineService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/medicines")
public class MedicineServlet extends HttpServlet {

    private final MedicineService medicineService = MedicineService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        try (var printWriter = resp.getWriter()) {
            printWriter.write("<h1>Medicines:</h1>");
            printWriter.write("<ul>");
            medicineService.findAll().forEach(medicineDto -> printWriter.write("""
                    <li>
                        %s %s - %d$
                    </li>
                    """.formatted(medicineDto.getName(), medicineDto.getCountry(), medicineDto.getPrice())));
            printWriter.write("</ul>");
        }
    }
}
