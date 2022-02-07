package by.pharmacy.filters;

import by.pharmacy.dto.UserDto;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {

    private static final Set<String> PUBLIC_PATH = Set.of("/login", "/registration", "/locale");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        var uri = ((HttpServletRequest) request).getRequestURI();
        if (isPublicPath(uri) || isUserLoggedIn(request)) {
            chain.doFilter(request, response);
        } else {
            var prevPage = ((HttpServletResponse) response).getHeader("referer");
            ((HttpServletResponse)response).sendRedirect(prevPage != null ? prevPage : "/login");
        }
    }

    private boolean isUserLoggedIn(ServletRequest servletRequest) {
        var user = (UserDto) ((HttpServletRequest) servletRequest).getSession().getAttribute("user");
        return user != null;
    }

    private boolean isPublicPath(String uri) {
        return PUBLIC_PATH.stream().anyMatch(uri::startsWith);
    }
}
