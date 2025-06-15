package br.unitins.topicos.projetoa2.repository;

import br.unitins.topicos.projetoa2.model.Developer;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {
}
