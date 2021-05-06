package ru.job4j.todo.monitor;



import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.todo.model.Item;
import ru.job4j.todo.store.ItemStore;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;



//@WebServlet("/task")
public class TaskServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(TaskServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        LOG.debug("in doGet ");
        List<Item> list = new ArrayList<Item>(ItemStore.instOf().findAllTasks());
        String json = new Gson().toJson(list);
        LOG.info("Method doGet");
        PrintWriter out = new PrintWriter(resp.getOutputStream(), true, StandardCharsets.UTF_8);
        out.println(json);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOG.info("in doPost descText = " + req.getParameter("desc"));
        String descText = req.getParameter("desc");
        LOG.info("id = " + req.getParameter("id"));
        String id = req.getParameter("id");
        LOG.info("done = " + req.getParameter("done"));
        LocalDateTime localDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String date = localDate.format(formatter);
        Timestamp timestamp = Timestamp.valueOf(localDate);
        LOG.info("Дата: " + date);

        boolean done = Boolean.parseBoolean((req.getParameter("done")));
        if (Integer.parseInt(id) != 0) {
            Item item = new Item(Integer.parseInt(id), descText, done);
            ItemStore.instOf().updateItem(item);
            LOG.info("!!!!!!!!!!!!!!!!!!" + done);
            return;
        }
        LOG.info("Servlet doPost: describe: " + descText);
        Item itemSave = new Item(Integer.parseInt(id), descText, timestamp, true);
        ItemStore.instOf().saveItem(itemSave);
    }
}