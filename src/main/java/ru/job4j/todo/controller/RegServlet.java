package ru.job4j.todo.controller;

import ru.job4j.todo.model.User;
import ru.job4j.todo.store.ItemStore;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/reg.do")
public class RegServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String email = req.getParameter("email");
        if (email != null && !email.equals("") &&
                email.equals(ItemStore.instOf().findUserByEmail(email).getEmail())){
            resp.sendRedirect(req.getContextPath() + "/reg.jsp");
        } else if (email != null && !email.equals("") ) {
            User user = new User(
                    req.getParameter("login"),
                    email,
                    req.getParameter("password"));
            System.out.println(user);
            ItemStore.instOf().saveUser(user);
            HttpSession sc = req.getSession();
            sc.setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        } else {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        }


    }
}