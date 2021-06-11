package ru.job4j.todo.hql;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "vacancy")
public class Vacancies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;

    public Vacancies(){
    }

    public Vacancies(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacancies vacancies = (Vacancies) o;
        return Objects.equals(id, vacancies.id) && Objects.equals(name, vacancies.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "\nVacancies{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
