package ru.job4j.todo.controller;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.todo.model.Category;
import ru.job4j.todo.model.Item;
import ru.job4j.todo.store.ItemStore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/category.do")
public class CategoryServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(CategoryServlet.class.getName());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("Class CategoryServlet");
        List<Category> allCategories = ItemStore.instOf().allCategories();
        req.setAttribute("categoryList", allCategories);
        req.getRequestDispatcher("/create.jsp").forward(req, resp);
    }

}
