package ru.job4j.todo.toMany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
            ModelAuto modelAuto = ModelAuto.of("Sport");
            session.save(modelAuto);
            ModelAuto modelAuto2 = ModelAuto.of("Бизнес");
            session.save(modelAuto2);
            ModelAuto modelAuto3 = ModelAuto.of("Стандарт");
            session.save(modelAuto3);
            ModelAuto modelAuto4 = ModelAuto.of("Стандарт+");
            session.save(modelAuto4);
            ModelAuto modelAuto5 = ModelAuto.of("Комфорт");
            session.save(modelAuto5);
            MarkAuto audi = MarkAuto.of("Audi");
            audi.addModel(session.load(ModelAuto.class, 1));
            audi.addModel(session.load(ModelAuto.class, 2));
            audi.addModel(session.load(ModelAuto.class, 3));
            audi.addModel(session.load(ModelAuto.class, 4));
            audi.addModel(session.load(ModelAuto.class, 5));
            System.out.println(audi.getModelAutos().get(0));
            System.out.println(audi);
            session.save(audi);
            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
