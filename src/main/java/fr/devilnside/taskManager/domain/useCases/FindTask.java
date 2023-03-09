package fr.devilnside.taskManager.domain.useCases;

import fr.devilnside.taskManager.domain.entities.Task;
import fr.devilnside.taskManager.domain.repositories.Repository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class FindTask implements UseCases.Find<Task> {
    @Inject
    Repository<Task> repository;

    @Override
    public CompletionStage<Task> execute(Long id) {
        return CompletableFuture.completedStage(repository.findById(id));
    }
}
