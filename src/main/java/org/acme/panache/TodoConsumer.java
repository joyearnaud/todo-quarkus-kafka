package org.acme.panache;

import io.smallrye.reactive.messaging.annotations.Blocking;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class TodoConsumer {
    private final Logger logger = Logger.getLogger(TodoConsumer.class);

    @Inject
    TodoStorage storage;

    @Incoming("todos-in")
    @Blocking
    public void receive(Todo todo) {
        logger.infof("Got a todo: '%s'", todo.title);
        storage.store(todo);
    }
}
