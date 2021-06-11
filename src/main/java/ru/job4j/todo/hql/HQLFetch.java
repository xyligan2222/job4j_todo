package ru.job4j.todo.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class HQLFetch {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Vacancies vacancies1 = new Vacancies("Java Junior");
            Vacancies vacancies2 = new Vacancies("Java Middle");
            session.save(vacancies1);
            session.save(vacancies2);
            List<Vacancies> list = new ArrayList<>();
            list.add(vacancies1);
            list.add(vacancies2);

            BaseVacancy baseVacancy = new BaseVacancy("Linkedin",list );
            session.save(baseVacancy);

            Candidate four = new Candidate("Vasya", 2, 80000L, baseVacancy);
            session.save(four);

            Query query4 = session.createQuery("select distinct  c from Candidate c "
                    + "join fetch c.baseVacancy b "
                    + "join fetch b.vacancies "
                    + "where c.id = :fId", Candidate.class)
                    .setParameter("fId", 6);
           System.out.println(query4.getSingleResult());

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
