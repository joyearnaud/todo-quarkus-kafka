package org.acme.panache;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class TodoProducer {
    @Inject @Channel("todos-out")
    Emitter<Todo> emitter;

    public CompletionStage<Void> createTodo(Todo todo) {
         return emitter.send(todo);
    }
}
