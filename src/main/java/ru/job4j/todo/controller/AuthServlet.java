package ru.job4j.todo.controller;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.todo.model.User;
import ru.job4j.todo.store.ItemStore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet("/auth.do")
public class AuthServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(AuthServlet.class.getName());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        String json = new Gson().toJson(req.getSession().getAttribute("user"));
        PrintWriter out = new PrintWriter(resp.getOutputStream(), true, StandardCharsets.UTF_8);
        System.out.println("Auth do get " + json);
        out.println(json);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User username = ItemStore.instOf().findUserByEmail(email);
        if (username != null &&
                email.equals(username.getEmail()) && password.equals(username.getPassword())) {
            HttpSession sc = req.getSession();
            sc.setAttribute("user", username);
            LOG.info("AUTH method " + username);
            resp.sendRedirect(req.getContextPath() + "/index.do");
        } else {
            req.setAttribute("error", "Не верный email или пароль");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}