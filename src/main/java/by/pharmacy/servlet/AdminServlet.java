package by.pharmacy.servlet;

import by.pharmacy.service.MedicineService;
import by.pharmacy.util.JspHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    private final MedicineService medicineService = MedicineService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var requestDispatcher = req.getRequestDispatcher(JspHelper.getPath("admin"));
        requestDispatcher.forward(req, resp);
    }
}