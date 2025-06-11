package br.unitins.topicos.projetoa2;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {
}
