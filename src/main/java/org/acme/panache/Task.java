package org.acme.panache;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Tasks")
public class Task extends PanacheEntity {
    @NotBlank(message = "Name may not be blank")
    public String name;
}
