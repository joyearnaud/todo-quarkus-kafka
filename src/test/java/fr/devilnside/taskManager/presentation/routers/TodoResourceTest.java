package fr.devilnside.taskManager.presentation.routers;

import fr.devilnside.taskManager.domain.entities.Todo;
import fr.devilnside.taskManager.domain.useCases.UseCases;
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

import static org.mockito.ArgumentMatchers.isA;

@QuarkusTest
class TodoResourceTest {

    @Inject
    TodoResource resource;
    @InjectMock
    UseCases.Create<Todo> create;
    @InjectMock
    UseCases.Find<Todo> find;
    @InjectMock
    UseCases.FindAll<Todo> findAll;

    private Todo todo;
    private List<Todo> todos;

    @BeforeEach
    public void setup() {
        todo = new Todo();
        Todo todo2 = new Todo();
        todos = new ArrayList<>();

        todo.id = 2L;
        todo2.id = 4L;
        todo.title = "todo_title_1";
        todo2.title = "todo_title_2";

        todos.add(todo);
        todos.add(todo2);

        Mockito.when(create.execute(isA(Todo.class))).thenReturn(CompletableFuture.completedFuture(null));
        Mockito.when(find.execute(isA(Long.class))).thenReturn(CompletableFuture.completedFuture(todo));
        Mockito.when(findAll.execute(null)).thenReturn(CompletableFuture.completedFuture(todos));
    }

    @Test
    void createTodo() throws ExecutionException, InterruptedException {
        CompletionStage<Response> create = resource.createTodo(todo);

        Response response = create.toCompletableFuture().get();

        Assertions.assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        Assertions.assertNull(response.getEntity());
    }

    @Test
    void findAllTodos() throws ExecutionException, InterruptedException {
        CompletionStage<Response> findAll = resource.findAllTodos();

        Response response = findAll.toCompletableFuture().get();

        Assertions.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        Assertions.assertEquals(todos, response.getEntity());
    }

    @Test
    void findTodo() throws ExecutionException, InterruptedException {
        CompletionStage<Response> find = resource.findTodo(todo.id);

        Response response = find.toCompletableFuture().get();

        Assertions.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        Assertions.assertEquals(todo, response.getEntity());
    }
}