package by.pharmacy.servlet;

import by.pharmacy.dto.UserDto;
import by.pharmacy.service.UserService;
import by.pharmacy.util.JspHelper;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var requestDispatcher = req.getRequestDispatcher(JspHelper.getPath("login"));
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        userService.login(req.getParameter("eMail"), req.getParameter("password"))
                .ifPresentOrElse(
                        user -> getOnLoginSuccess(req, resp, user),
                        () -> onLoginFail(req, resp)
                );
    }

    @SneakyThrows
    private void getOnLoginSuccess(HttpServletRequest req, HttpServletResponse resp, UserDto user) {
        req.getSession().setAttribute("user", user); // строка для фильтра
        if (user.getRole().name().equals("ADMIN")) {
            resp.sendRedirect("/admin");
        } else {
            resp.sendRedirect("/user-medicines");
        }
    }

    @SneakyThrows
    private void onLoginFail(HttpServletRequest req, HttpServletResponse resp) {
        resp.sendRedirect("/login?error&email=" + req.getParameter("email"));
    }
}
