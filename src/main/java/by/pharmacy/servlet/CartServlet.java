package by.pharmacy.servlet;

import by.pharmacy.dto.MedicineDto;
import by.pharmacy.service.MedicineService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cart-servlet")
public class CartServlet extends HttpServlet {

    public final MedicineService medicineService = MedicineService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] quantity = req.getParameterValues("quantity");
        String[] ids = req.getParameterValues("id");
        int totalPrice = 0;
        for (int i=0; i<quantity.length; i++) {
            MedicineDto byId = medicineService.findById(Integer.parseInt(ids[i]));
            int totalMedicine = byId.getPrice()*Integer.parseInt(quantity[i]);
            totalPrice+=totalMedicine;
            req.setAttribute("dto", byId);
            req.setAttribute("quantity", quantity[i]);
            req.setAttribute("", byId);

        }
    }
}
