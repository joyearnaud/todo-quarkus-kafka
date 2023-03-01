package org.acme.panache;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class TodoService {
    @Inject
    TodoProducer producer;

    public CompletionStage<Void> create(Todo todo) {
        return producer.createTodo(todo);
    }
}
