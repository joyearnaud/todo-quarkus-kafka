package fr.devilnside.taskManager.domain.useCases;

import fr.devilnside.taskManager.domain.entities.Todo;
import fr.devilnside.taskManager.handlers.TodoProducer;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static org.mockito.ArgumentMatchers.isA;

@QuarkusTest
class CreateTodoTest {

    @Inject
    CreateTodo createTodo;
    @InjectMock
    TodoProducer producer;

    @BeforeEach
    public void setup() {
        Mockito.when(producer.create(isA(Todo.class))).thenReturn(new CompletableFuture<>());
    }

    @Test
    void execute() {
        Todo todo = new Todo();
        todo.title = "todo1";
        CompletionStage<Void> execute = createTodo.execute(todo);

        execute.thenAccept(response -> Assertions.assertInstanceOf(Void.class, response)).exceptionally(throwable -> Assertions.fail());
    }
}