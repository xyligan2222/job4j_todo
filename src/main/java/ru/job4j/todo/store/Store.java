package ru.job4j.todo.store;

import ru.job4j.todo.model.Category;
import ru.job4j.todo.model.Item;
import ru.job4j.todo.model.User;

import java.util.Collection;
import java.util.List;


public interface Store {

     Item saveItem(Item item, String[] categories);
     Collection<Item> findAllTasks();
     void updateItem(Item item);
     List<Category> allCategories();
     Item findById(int id);
     User findUserByEmail (String email);
     User findUserById (int id);
     User saveUser(User user);



}
