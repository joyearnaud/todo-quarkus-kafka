package fr.devilnside.taskManager.domain.repositories;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

public interface Repository<E extends PanacheEntity> extends PanacheRepository<E> {
}
