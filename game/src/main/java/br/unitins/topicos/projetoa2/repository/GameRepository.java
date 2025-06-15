package br.unitins.topicos.projetoa2.repository;

import br.unitins.topicos.projetoa2.model.Game;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {
}
