package ru.job4j.todo.toMany;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "mark")
public class MarkAuto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ModelAuto> modelAutos = new ArrayList<>();

    public MarkAuto(){
    }

    public static MarkAuto of (String name) {
        MarkAuto auto = new MarkAuto();
        auto.name = name;
        return auto;
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

    public List<ModelAuto> getModelAutos() {
        return modelAutos;
    }

    public void setModelAutos(List<ModelAuto> modelAutos) {
        this.modelAutos = modelAutos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarkAuto markAuto = (MarkAuto) o;
        return id == markAuto.id && Objects.equals(name, markAuto.name) && Objects.equals(modelAutos, markAuto.modelAutos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, modelAutos);
    }

    @Override
    public String toString() {
        return "\nMarkAuto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", modelAutos=" + modelAutos +
                '}';
    }

    public boolean addModel(ModelAuto u) {
        return this.modelAutos.add(u);
    }
}
