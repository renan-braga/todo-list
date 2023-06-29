package br.com.ifsp.todolist.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class EncondingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            request.setCharacterEncoding("UTF8");
            response.setCharacterEncoding("UTF8");

            chain.doFilter(request, response);
    }
}
