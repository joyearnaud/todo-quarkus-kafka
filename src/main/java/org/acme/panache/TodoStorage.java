package org.acme.panache;

import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class TodoStorage {

    private final Logger logger = Logger.getLogger(TodoConsumer.class);
    @Transactional
    public void store(Todo todo) {
        try {
            todo.persist();
        } catch (Exception e) {
            logger.error(e);
        }
    }

}

