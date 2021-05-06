package ru.job4j.todo.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "item")

public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column (name = "description")
    private String desc;
    @Column (name = "created")
    private Timestamp created;
    @Column (name = "done")
    private Boolean done;

    public Item() {
    }

    public Item(Integer id, String desc, Timestamp created, Boolean done) {
        this.id = id;
        this.desc = desc;
        this.created = created;
        this.done = done;
    }

    public Item(String desc, Timestamp created, Boolean done) {
        this.desc = desc;
        this.created = created;
        this.done = done;
    }

    public Item(Integer id, String desc, Boolean done) {
        this.id = id;
        this.desc = desc;
        this.done = done;
    }

    public Item(String desc) {
        this.desc = desc;
        this.created = new Timestamp(new Date().getTime());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public boolean isDone() {
        return done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id) && Objects.equals(desc, item.desc) && Objects.equals(created, item.created) && Objects.equals(done, item.done);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, desc, created, done);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", desc='" + desc + '\'' +
                ", created=" + created +
                ", done=" + done +
                '}';
    }
}
