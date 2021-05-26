package ru.job4j.todo.controller;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.todo.model.User;
import ru.job4j.todo.store.ItemStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;


public class CheckRegServlet extends HttpServlet{
    private static final Logger LOG = LoggerFactory.getLogger(CheckRegServlet.class.getName());
    private boolean regStatus;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        String emailFromStore;
        if (regStatus) {
            emailFromStore = "true";
        } else {
            emailFromStore = "false";
        }
        LOG.info(emailFromStore);
        String json = new Gson().toJson(emailFromStore);
        PrintWriter out = new PrintWriter(resp.getOutputStream(), true, StandardCharsets.UTF_8);
        out.println(json);
        out.flush();
        regStatus = false;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String email = req.getParameter("email");
        User userFromUser = ItemStore.instOf().findUserByEmail(email);
        LOG.info(String.valueOf(userFromUser));
        LOG.info("email  " + email);
        if (userFromUser != null) {
            regStatus = true;
        }
        resp.sendRedirect(req.getContextPath() + "/reg.jsp");
    }
}
