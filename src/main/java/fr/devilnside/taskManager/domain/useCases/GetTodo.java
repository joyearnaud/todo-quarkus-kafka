package fr.devilnside.taskManager.domain.useCases;

import fr.devilnside.taskManager.domain.entities.Todo;
import fr.devilnside.taskManager.domain.repositories.Repository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class GetTodo implements UseCase.Find<Todo> {
    @Inject
    Repository<Todo> repository;

    @Override
    public CompletionStage<Todo> execute(Long id) {
        return CompletableFuture.completedStage(repository.findById(id));
    }
}
