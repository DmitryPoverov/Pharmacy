package by.pharmacy.servlet;

import by.pharmacy.dto.UserDto;
import by.pharmacy.entity.Role;
import by.pharmacy.service.UserService;
import by.pharmacy.util.JspHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("registration"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        UserDto userDto = UserDto.builder()
                .name(req.getParameter("userName"))
                .email(req.getParameter("eMail"))
                .password(req.getParameter("password"))
                .role(Role.USER)
                .build();
        try {userService.create(userDto);
            resp.sendRedirect("/login");
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
