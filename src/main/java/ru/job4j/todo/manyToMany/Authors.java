package ru.job4j.todo.manyToMany;

import ru.job4j.todo.toMany.MarkAuto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "authors")
public class Authors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @ManyToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Books> books = new ArrayList<>();

    public Authors(){
    }

    public static Authors of (String name) {
        Authors authors = new Authors();
        authors.name = name;
        return authors;
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

    public List<Books> getBooks() {
        return books;
    }

    public void setBooks(List<Books> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authors authors = (Authors) o;
        return id == authors.id && Objects.equals(name, authors.name) && Objects.equals(books, authors.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, books);
    }

    @Override
    public String toString() {
        return "\nAuthors{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", books=" + books +
                '}';
    }
}
