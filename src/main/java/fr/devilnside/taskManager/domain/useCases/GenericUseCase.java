package fr.devilnside.taskManager.domain.useCases;

import java.util.concurrent.CompletionStage;

public interface GenericUseCase<E, CS> {
    CompletionStage<CS> execute(E entity);
}
