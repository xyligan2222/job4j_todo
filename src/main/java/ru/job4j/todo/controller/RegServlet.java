package ru.job4j.todo.controller;

import com.google.gson.Gson;
import ru.job4j.todo.model.Item;
import ru.job4j.todo.model.User;
import ru.job4j.todo.store.ItemStore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/reg.do")
public class RegServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String email = req.getParameter("email");
        System.out.println(" do Post Reg ");
        User userFromStore = ItemStore.instOf().findUserByEmail(email);
        if (userFromStore != null) {
            resp.sendRedirect(req.getContextPath() + "/reg.jsp");
        } else {
            User user = new User(
                    req.getParameter("login"),
                    email,
                    req.getParameter("password"));
            System.out.println(user);
            ItemStore.instOf().saveUser(user);
            HttpSession sc = req.getSession();
            sc.setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            }
    }
}