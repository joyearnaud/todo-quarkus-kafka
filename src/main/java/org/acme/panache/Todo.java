package org.acme.panache;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.smallrye.common.constraint.NotNull;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

@Entity
public class Todo extends PanacheEntity {

    @NotBlank(message="Title may not be blank")
    public String title;
    public String description;
    public Date date;
    @ElementCollection
    public List<String> tasks;
    @ElementCollection
    public List<String> tags;
}
