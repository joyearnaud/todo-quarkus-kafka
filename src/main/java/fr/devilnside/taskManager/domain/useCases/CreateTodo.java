package fr.devilnside.taskManager.domain.useCases;

import fr.devilnside.taskManager.domain.entities.Todo;
import fr.devilnside.taskManager.handlers.TodoProducer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class CreateTodo implements UseCases.Create<Todo> {
    @Inject
    TodoProducer producer;

    public CompletionStage<Void> execute(Todo todo) {
        return producer.create(todo);
    }
}
