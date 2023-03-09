package fr.devilnside.taskManager.domain.useCases;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import java.util.List;

public interface UseCases {

    interface Create<E extends PanacheEntity> extends GenericUseCase<E, Void> {
    }
    interface Find<E extends PanacheEntity> extends GenericUseCase<Long, E> {
    }
    interface FindAll<E extends PanacheEntity> extends GenericUseCase<Void, List<E>> {
    }
}
