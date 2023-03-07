package org.acme.panache;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class TodoStorage implements PanacheRepository<Todo>{

    private final Logger logger = Logger.getLogger(TodoConsumer.class);

    @Transactional
    public void store(Todo todo) {
        persist(todo);
    }

    public List<Todo> findAllTodos () {
        return findAll().stream().toList();
    }

}

