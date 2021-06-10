package ru.job4j.todo.hql;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "candidate")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "experience")
    private Integer experience;
    @Column(name = "salary")
    private Long salary;

    public Candidate() {
    }

    public Candidate(Integer id, String name, Integer experience, Long salary) {
        this.id = id;
        this.name = name;
        this.experience = experience;
        this.salary = salary;
    }

    public Candidate(String name, Integer experience, Long salary) {
        this.name = name;
        this.experience = experience;
        this.salary = salary;
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

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidate candidate = (Candidate) o;
        return Objects.equals(id, candidate.id)
                && Objects.equals(name, candidate.name)
                && Objects.equals(experience, candidate.experience)
                && Objects.equals(salary, candidate.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, experience, salary);
    }

    @Override
    public String toString() {
        return "\nCandidate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", experience='" + experience + '\'' +
                ", salary='" + salary + '\'' +
                '}';
    }
}
