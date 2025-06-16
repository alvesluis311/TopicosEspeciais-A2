package br.unitins.topicos.projetoa2.service;

import java.util.List;
import java.util.Optional;

import br.unitins.topicos.projetoa2.dto.PlataformaDTO;
import br.unitins.topicos.projetoa2.model.Plataforma;
import br.unitins.topicos.projetoa2.repository.GameRepository;
import br.unitins.topicos.projetoa2.repository.PlataformaRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityNotFoundException;

@Singleton
public class PlataformaService {

    @Inject
    private PlataformaRepository plataformaRepository;

    @Inject
    private GameRepository gameRepository;

    public List<Plataforma> listarPlataformas() {
        return plataformaRepository.findAll();
    }

    public Optional<Plataforma> buscarPorId(Long id) {
        return plataformaRepository.findById(id);
    }

    public Plataforma salvarPlataforma(PlataformaDTO plataformaDTO) {

        plataformaRepository.findByNome(plataformaDTO.nome())
                .ifPresent(p -> {
                    throw new IllegalArgumentException("Já existe uma plataforma com esse nome.");
                });

        Plataforma plataforma = new Plataforma(plataformaDTO);
        return plataformaRepository.save(plataforma);
    }

    public Plataforma atualizarPlataforma(Long id, PlataformaDTO plataformaDTO) {
        Plataforma plataforma = plataformaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Plataforma não encontrada para atualização"));

        plataformaRepository.findByNome(plataformaDTO.nome())
                .filter(p -> !p.getId().equals(id))
                .ifPresent(p -> {
                    throw new IllegalArgumentException("Já existe outra plataforma com esse nome.");
                });

        plataforma.setDados(plataformaDTO);
        return plataformaRepository.update(plataforma);
    }

    public void deletarPlataforma(Long id) {

        Plataforma plataforma = plataformaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Plataforma não encontrada para exclusão"));

        boolean possuiJogos = gameRepository.existsByPlataforma(plataforma);
        if (possuiJogos) {
            throw new IllegalStateException("Não é possível deletar a plataforma. Existem jogos associados a ela.");
        }

        plataformaRepository.delete(plataforma);
    }
}
