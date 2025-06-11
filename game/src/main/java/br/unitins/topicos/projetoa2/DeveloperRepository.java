package br.unitins.topicos.projetoa2;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {
}
