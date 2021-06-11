package ru.job4j.todo.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.Query;
import java.util.List;

public class HqlMain {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
//            Candidate one = new Candidate("Andrew", 3, 55000L);
//            Candidate two = new Candidate("Vadim", 2, 50000L);
//            Candidate three = new Candidate("Nikolay", 1, 45000L);
//
//            session.save(one);
//            session.save(two);
//            session.save(three);
//
//            List<Candidate> list = session.createQuery("from Candidate ").list();
//            for (var st : list) {
//                System.out.println(st);
//            }
//
//            Query query = session.createQuery("from Candidate c where c.id= :fId ")
//                    .setParameter("fId", 3);
//            System.out.println(query.getSingleResult());
//
//            Query query1 = session.createQuery("from Candidate c where c.name= :fName ")
//                    .setParameter("fName", "Andrew");
//
//            System.out.println(query1.getSingleResult());
//            Query query2 = session.createQuery("update Candidate c set c.name =:fName, c.experience = :fExp where c.id = :fId")
//                    .setParameter("fName", "Petr")
//                    .setParameter("fExp", 5)
//                    .setParameter("fId", 2);
//            query2.executeUpdate();
//            Query query3 = session.createQuery("delete from Candidate c where c.id = :fId")
//                        .setParameter("fId", 2);
//            query3.executeUpdate();
            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
