package fr.devilnside.taskManager.domain.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Todos")
public class Todo extends PanacheEntity {

    @NotBlank(message = "Title may not be blank")
    public String title;
    public String description;
    public Date date;
    @Valid
    @OneToMany(targetEntity = Task.class,
//            mappedBy = "todo",
            cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    public List<Task> tasks = new ArrayList<>();
    @Valid
    @ElementCollection
    public List<String> tags;
}
