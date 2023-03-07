package fr.devilnside.taskManager.handlers;

import fr.devilnside.taskManager.domain.entities.Todo;
import fr.devilnside.taskManager.domain.repositories.Repository;
import io.smallrye.reactive.messaging.annotations.Blocking;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class TodoConsumer {
    private final Logger logger = Logger.getLogger(TodoConsumer.class);

    @Inject
    Repository<Todo> repository;

    @Incoming("todos-in")
    @Transactional
    @Blocking
    public void receive(Todo todo) {
        logger.infof("Got a todo: '%s'", todo.title);
        repository.persist(todo);
    }
}
