package ru.job4j.todo.store;

import ru.job4j.todo.model.Item;

import java.util.Collection;


public interface Store {

     Item saveItem(Item item);
     Collection<Item> findAllTasks();
     void updateItem(Item item);
     Item findById(int id);



}
