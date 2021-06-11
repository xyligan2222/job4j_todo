package ru.job4j.todo.hql;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "baseOfVacancy")
public class BaseVacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true )
    @PrimaryKeyJoinColumn
    private List<Vacancies> vacancies = new ArrayList<>();

    public BaseVacancy(){}

    public BaseVacancy(String name, List<Vacancies> vacancies) {
        this.name = name;
        this.vacancies = vacancies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Vacancies> getVacancies() {
        return vacancies;
    }

    public void setVacancies(List<Vacancies> vacancies) {
        this.vacancies = vacancies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseVacancy that = (BaseVacancy) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(vacancies, that.vacancies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, vacancies);
    }

    @Override
    public String toString() {
        return "\nBaseVacancy{" +
                "id=" + id +
                ", name='" + name +
                '}';
    }
}
