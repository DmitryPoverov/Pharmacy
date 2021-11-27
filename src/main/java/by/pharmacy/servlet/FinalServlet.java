package by.pharmacy.servlet;

import by.pharmacy.dto.MedicineDto;
import by.pharmacy.service.MedicineService;
import by.pharmacy.util.JspHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/final-servlet")
public class FinalServlet extends HttpServlet {

    public final MedicineService medicineService = MedicineService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("submitReject").equals("1")) {
            req.setAttribute("request", "Покупка была отменена!");
            req.getSession().setAttribute("medicines", medicineService.findAll());
            req.getRequestDispatcher(JspHelper.getPath("user-medicines")).forward(req, resp);
        } else if (req.getParameter("submitReject").equals("2")){
            req.setAttribute("request", "Покупка была совершена!");
            req.getSession().setAttribute("medicines", medicineService.findAll());
            req.getRequestDispatcher(JspHelper.getPath("user-medicines")).forward(req, resp);
        }

    }
}
