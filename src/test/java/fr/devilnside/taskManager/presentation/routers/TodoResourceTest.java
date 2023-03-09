package fr.devilnside.taskManager.presentation.routers;

import fr.devilnside.taskManager.domain.entities.Todo;
import fr.devilnside.taskManager.domain.useCases.UseCase;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;

@QuarkusTest
class TodoResourceTest {

    @Inject
    TodoResource resource;
    @InjectMock
    UseCase.Create<Todo> create;
    @InjectMock
    UseCase.Find<Todo> find;
    @InjectMock
    UseCase.FindAll<Todo> findAll;

    private Todo todo;
    private List<Todo> todos;

    @BeforeEach
    public void setup() {
        todo = new Todo();
        todos = new ArrayList<>();

        todo.title = "todo_title";
        todos.add(todo);

        Mockito.when(create.execute(isA(Todo.class))).thenReturn(CompletableFuture.completedFuture(null));
        Mockito.when(find.execute(isA(Long.class))).thenReturn(CompletableFuture.completedFuture(todo));
        Mockito.when(findAll.execute()).thenReturn(CompletableFuture.completedFuture(todos));
    }

    @Test
    void createTodo() throws ExecutionException, InterruptedException {
        CompletionStage<Response> create = resource.createTodo(todo);

        Response response = create.toCompletableFuture().get();

        Assertions.assertEquals(201, response.getStatus());
        Assertions.assertNull(response.getEntity());
    }

    @Test
    void findAllTodos() throws ExecutionException, InterruptedException {
        CompletionStage<Response> findAll = resource.findAllTodos();

        Response response = findAll.toCompletableFuture().get();

        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertEquals(todos, response.getEntity());
    }

    @Test
    void findTodo() throws ExecutionException, InterruptedException {
        CompletionStage<Response> find = resource.findTodo(2L);

        Response response = find.toCompletableFuture().get();

        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertEquals(todo, response.getEntity());
    }
}