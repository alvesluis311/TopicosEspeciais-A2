package br.unitins.topicos.projetoa2.repository;

import java.util.Optional;

import br.unitins.topicos.projetoa2.model.Plataforma;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface PlataformaRepository extends JpaRepository<Plataforma, Long> {
    
    Optional<Plataforma> findByNome(String nome);
}
