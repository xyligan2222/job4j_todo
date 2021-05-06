package ru.job4j.todo.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.todo.model.Item;

import java.util.Collection;
import java.util.List;

public class ItemStore implements Store, AutoCloseable {

    private static final class Lazy {
        private static final ItemStore INST = new ItemStore();

    }

    public static ItemStore instOf() {
        return Lazy.INST;
    }

    private static final Logger LOG = LoggerFactory.getLogger(ItemStore.class.getName());

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public Item saveItem(Item item) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();
        LOG.info("save" + item);
        return item;
    }

    @Override
    public Collection<Item> findAllTasks() {
        Session session = sf.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Item ");
        List<Item> list = query.getResultList();
        session.getTransaction().commit();
        session.close();
        LOG.info("Task size" + (list.size()));
        list.stream().forEach(item -> LOG.info(item.getDesc()));
        return list;
    }

    @Override
    public void updateItem(Item item) {
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Item replaceItem = session.get(Item.class, item.getId());
            replaceItem.setDone(item.getDone());
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }


    @Override
    public Item findById(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        Item item = session.get(Item.class, id);
        session.getTransaction().commit();
        session.close();
        return item;
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
