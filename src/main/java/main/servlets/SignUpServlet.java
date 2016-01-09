package main.servlets;

import main.models.User;
import main.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SignUpServlet extends HttpServlet {
    @Autowired AccountService accountService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        User user = accountService.signUp(login, password);

        resp.setContentType("text/html;charset=utf-8");

        if (user != null) {
            resp.getWriter().println("SignedUp");
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.getWriter().println("User already exists");
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
        }
    }
}
