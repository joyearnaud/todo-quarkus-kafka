package fr.devilnside.taskManager.presentation.routers;

import fr.devilnside.taskManager.domain.entities.Task;
import fr.devilnside.taskManager.domain.useCases.UseCases;
import io.smallrye.common.annotation.Blocking;
import org.eclipse.microprofile.openapi.annotations.Operation;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.CompletionStage;

@Path("/tasks")
public class TaskResource extends ResourceFunc {
//    @Inject
//    UseCases.Create<Task> create;
    @Inject
    UseCases.FindAll<Task> findAll;
    @Inject
    UseCases.Find<Task> find;

//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    @Blocking
//    @Transactional
//    @Operation(summary = "POST a task", description = "Create a single task based on JSON body")
//    public CompletionStage<Response> createTask(@RequestBody @Valid Task task) {
//        return create.execute(task)
//                .thenApply(getFuncRes(Response.Status.CREATED))
//                .exceptionally(throwableResponseFunction);
//    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Blocking
    @Operation(summary = "GET all tasks", description = "Returns all tasks.")
    public CompletionStage<Response> findAllTasks() {
        return findAll.execute(null)
                .thenApply(getFuncRes(Response.Status.OK))
                .exceptionally(throwableResponseFunction);
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Blocking
    @Operation(summary = "GET specific task by id", description = "Returns specific task by id.")
    public CompletionStage<Response> findTodo(Long id) {
        return find.execute(id)
                .thenApply(getFuncRes(Response.Status.OK))
                .exceptionally(throwableResponseFunction);
    }
}
