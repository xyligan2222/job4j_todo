package ru.job4j.todo.toMany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

public class HbmRun {
    public static void main(String[] args) {
        List<MarkAuto> list = new ArrayList<>();
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
            MarkAuto audi = MarkAuto.of("Audi");
            session.save(audi);
            ModelAuto modelAuto = ModelAuto.of("Sport",audi);
            session.save(modelAuto);
            ModelAuto modelAuto2 = ModelAuto.of("Бизнес",audi);
            session.save(modelAuto2);
            ModelAuto modelAuto3 = ModelAuto.of("Стандарт",audi);
            session.save(modelAuto3);
            ModelAuto modelAuto4 = ModelAuto.of("Стандарт+",audi);
            session.save(modelAuto4);
            ModelAuto modelAuto5 = ModelAuto.of("Комфорт",audi);
            session.save(modelAuto5);
            list = session.createQuery(
                    "select distinct c from MarkAuto c join fetch c.modelAutos"
            ).list();
            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        for (ModelAuto modelAuto : list.get(0).getModelAutos()) {
            System.out.println(modelAuto);
        }
    }
}
