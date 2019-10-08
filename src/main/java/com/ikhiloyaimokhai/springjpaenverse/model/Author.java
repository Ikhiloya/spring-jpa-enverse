package com.ikhiloyaimokhai.springjpaenverse.model;

import com.ikhiloyaimokhai.springjpaenverse.audit.AuditModel;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Table(name = "AUTHOR")
@Audited
public class Author extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_generator")
    @SequenceGenerator(name = "author_generator", sequenceName = "author_seq", allocationSize = 50)
    @Column(name = "ID", updatable = false, nullable = false)
    private Integer id;

    public Author() {
    }

    public Author(String name) {
        this.name = name;
    }

    @Column(name = "NAME", nullable = true, length = 255)
    private String name;

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
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
