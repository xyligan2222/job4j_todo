package ru.job4j.todo.store;

import ru.job4j.todo.model.Item;
import ru.job4j.todo.model.User;

import java.util.Collection;


public interface Store {

     Item saveItem(Item item);
     Collection<Item> findAllTasks();
     void updateItem(Item item);
     Item findById(int id);
     User findUserByEmail (String email);
     User findUserById (int id);
     User saveUser(User user);



}
