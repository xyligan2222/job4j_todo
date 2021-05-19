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
import ru.job4j.todo.model.User;

import java.util.Collection;
import java.util.function.Function;

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
        return this.tx(session -> {
            session.save(item);
            return item;
        });

    }

    @Override
    public Collection<Item> findAllTasks() {
        return this.tx(
                session -> session.createQuery("FROM Item ").list()
        );
    }

    @Override
    public void updateItem(Item item) {
        this.tx(session -> {
            Item replaceItem = session.get(Item.class, item.getId());
            replaceItem.setDone(item.getDone());
            return replaceItem;
        });
    }


    @Override
    public Item findById(int id) {
        return this.tx(session -> {
            Item item = session.get(Item.class, id);
            return item;
        });
    }

    @Override
    public User findUserByEmail(String email) {
        return this.tx(session -> {
            try {
                Query query = session.createQuery("FROM User as u WHERE u.email = :Email");
                query.setParameter("Email", email);
                User user = (User) query.getSingleResult();
                return user;
            } catch ( Exception exception) {
                return null;
            }

        });
    }

    @Override
    public User findUserById(int id) {
        return this.tx(session -> {
            User user = session.get(User.class, id);
            return user;
        });
    }

    @Override
    public User saveUser(User user) {
        return this.tx(session -> {
            session.save(user);
            return user;
        });

    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }


    /*
    wrapper design pattern
    */
    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
