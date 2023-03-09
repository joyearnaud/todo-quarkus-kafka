package fr.devilnside.taskManager.domain.useCases;

import fr.devilnside.taskManager.domain.entities.Task;
import fr.devilnside.taskManager.domain.repositories.Repository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class FindAllTasks implements UseCases.FindAll<Task> {
    @Inject
    Repository<Task> repository;

    @Override
    public CompletionStage<List<Task>> execute(Void entity) {
        return CompletableFuture.completedStage(repository.findAll().stream().toList());
    }
}
