package ru.job4j.todo.toMany;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "model")
public class ModelAuto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;

    public ModelAuto() {
    }

    public static ModelAuto of (String name) {
        ModelAuto modelAuto = new ModelAuto();
        modelAuto.name = name;
        return modelAuto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
        ModelAuto modelAuto = (ModelAuto) o;
        return id == modelAuto.id && Objects.equals(name, modelAuto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "\nModelAuto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
