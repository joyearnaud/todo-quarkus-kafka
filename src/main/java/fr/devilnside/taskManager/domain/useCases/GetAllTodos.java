package fr.devilnside.taskManager.domain.useCases;

import fr.devilnside.taskManager.domain.entities.Todo;
import fr.devilnside.taskManager.domain.repositories.Repository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class GetAllTodos implements UseCase.FindAll<Todo> {
    @Inject
    Repository<Todo> repository;

    @Override
    public CompletionStage<List<Todo>> execute() {
        return CompletableFuture.completedStage(repository.findAll().stream().toList());
    }
}
