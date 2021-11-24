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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/user-medicines")
public class UserMedicineServlet extends HttpServlet {

    private final MedicineService medicineService = MedicineService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] parameterValues = req.getParameterValues("medicineId");
        String[] quantity = req.getParameterValues("quantity");
        List<MedicineDto> medicineDtos = new ArrayList<>();
        for (String s : parameterValues) {
            medicineDtos.add(medicineService.findById(Integer.parseInt(s)));
        }
        req.getSession().setAttribute("medicines" , medicineDtos);
        req.getSession().setAttribute("quantity", quantity);
        req.getRequestDispatcher(JspHelper.getPath("user-medicine-cart")).forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("medicines", medicineService.findAll());
        req.getRequestDispatcher(JspHelper.getPath("user-medicines")).forward(req, resp);
    }
}
