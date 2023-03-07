package org.acme.panache;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Tasks")
public class Task extends PanacheEntity {
//    @NotBlank(message = "Title may not be blank")
    public String name;

//    @ManyToOne(targetEntity = Todo.class, fetch = FetchType.LAZY)
////    @JoinColumn(name="todo_id", nullable = false)
//    @JoinColumn(name = "todo_id")
//    private Todo todo;
}
