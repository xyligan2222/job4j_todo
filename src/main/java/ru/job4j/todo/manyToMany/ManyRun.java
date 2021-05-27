package ru.job4j.todo.manyToMany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class ManyRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Authors pushkin = Authors.of("Pushkin");
            Authors lermontov = Authors.of("Lermontov");

            Books books1 = Books.of("one");
            Books books2 = Books.of("two");
            Books books3 = Books.of("three");
            Books books4 = Books.of("four");

            pushkin.getBooks().add(books1);
            pushkin.getBooks().add(books2);
            pushkin.getBooks().add(books3);

            lermontov.getBooks().add(books1);
            lermontov.getBooks().add(books4);

            session.persist(pushkin);
            session.persist(lermontov);
            session.remove(lermontov);
            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
