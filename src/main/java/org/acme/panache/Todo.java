package org.acme.panache;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Todos")
public class Todo extends PanacheEntity {

    @NotBlank(message="Title may not be blank")
    public String title;
    public String description;
    public Date date;
    @OneToMany(targetEntity = Task.class,
//            mappedBy = "todo",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    public List<Task> tasks = new ArrayList<>();
    @ElementCollection
    public List<String> tags;
}
