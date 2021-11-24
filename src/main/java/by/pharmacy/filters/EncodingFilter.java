package by.pharmacy.filters;

import lombok.SneakyThrows;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.nio.charset.StandardCharsets;

@WebFilter("/*")
public class EncodingFilter implements Filter {

    @SneakyThrows
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        request.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        chain.doFilter(request, response);
    }
}
