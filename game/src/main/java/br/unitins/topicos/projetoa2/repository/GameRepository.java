package br.unitins.topicos.projetoa2.repository;

import java.time.LocalDate;
import java.util.Optional;

import br.unitins.topicos.projetoa2.model.Developer;
import br.unitins.topicos.projetoa2.model.Game;
import br.unitins.topicos.projetoa2.model.Plataforma;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    boolean existsByPlataforma(Plataforma plataforma);
    
    boolean existsByDeveloper(Developer developer);

    Optional<Game> findByNomeAndDeveloperAndDataLancamento(String nome, Developer developer, LocalDate dataLancamento);
}
