package fr.devilnside.taskManager.presentation.routers;

import fr.devilnside.taskManager.domain.entities.Todo;
import fr.devilnside.taskManager.domain.useCases.UseCases;
import io.smallrye.common.annotation.Blocking;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.CompletionStage;

@Path("/todos")
@OpenAPIDefinition(info = @Info(title = "todos API", version = "1.0"))
public class TodoResource extends ResourceFunc {
    @Inject
    UseCases.Create<Todo> create;
    @Inject
    UseCases.FindAll<Todo> findAll;
    @Inject
    UseCases.Find<Todo> find;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Blocking
    @Transactional
    @Operation(summary = "POST a todo", description = "Create a single todo based on JSON body")
    public CompletionStage<Response> createTodo(@RequestBody @Valid Todo todo) {
        return create.execute(todo)
                .thenApply(getFuncRes(Response.Status.CREATED))
                .exceptionally(throwableResponseFunction);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Blocking
    @Operation(summary = "GET all todos", description = "Returns all todos.")
    public CompletionStage<Response> findAllTodos() {
        return findAll.execute(null)
                .thenApply(getFuncRes(Response.Status.OK))
                .exceptionally(throwableResponseFunction);
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Blocking
    @Operation(summary = "GET specific todo by id", description = "Returns specific todo by id.")
    public CompletionStage<Response> findTodo(Long id) {
        return find.execute(id)
                .thenApply(getFuncRes(Response.Status.OK))
                .exceptionally(throwableResponseFunction);
    }
}
