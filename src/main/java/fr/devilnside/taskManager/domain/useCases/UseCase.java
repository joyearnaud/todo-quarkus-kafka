package fr.devilnside.taskManager.domain.useCases;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import java.util.List;
import java.util.concurrent.CompletionStage;

public interface UseCase {
    interface Create<E extends PanacheEntity> {
        CompletionStage<Void> execute(E entity);
    }
    interface Find<E extends PanacheEntity> {
        CompletionStage<E> execute(Long id);
    }

    interface FindAll<E extends PanacheEntity> {
        CompletionStage<List<E>> execute();
    }
}
