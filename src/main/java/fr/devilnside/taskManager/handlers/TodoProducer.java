package fr.devilnside.taskManager.handlers;

import fr.devilnside.taskManager.domain.entities.Todo;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class TodoProducer {
    @Inject
    @Channel("todos-out")
    Emitter<Todo> emitter;

    public CompletionStage<Void> create(Todo todo) {
        return emitter.send(todo);
    }
}
