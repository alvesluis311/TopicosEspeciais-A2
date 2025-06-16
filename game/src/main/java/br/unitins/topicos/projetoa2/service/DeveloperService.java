package br.unitins.topicos.projetoa2.service;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import br.unitins.topicos.projetoa2.dto.DeveloperDTO;
import br.unitins.topicos.projetoa2.model.Developer;
import br.unitins.topicos.projetoa2.repository.DeveloperRepository;
import br.unitins.topicos.projetoa2.repository.GameRepository;

@Singleton
public class DeveloperService {

    @Inject
    private DeveloperRepository developerRepository;

    @Inject
    private GameRepository gameRepository;

    public List<Developer> listarDevelopers() {
        return developerRepository.findAll();
    }

    public Optional<Developer> buscarPorId(Long id) {
        return developerRepository.findById(id);
    }

    public Developer salvarDeveloper(DeveloperDTO developerDTO) {

        developerRepository.findByNome(developerDTO.nome())
                .ifPresent(p -> {
                    throw new IllegalArgumentException("Já existe uma developer com esse nome.");
                });

        Developer developer = new Developer(developerDTO);
        return developerRepository.save(developer);
    }

    public Developer atualizarDeveloper(Long id, DeveloperDTO developerDTO) {
        Developer developer = developerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Developer não encontrada para atualização"));

        developerRepository.findByNome(developerDTO.nome())
                .filter(p -> !p.getId().equals(id))
                .ifPresent(p -> {
                    throw new IllegalArgumentException("Já existe outra developer com esse nome.");
                });

        developer.setDados(developerDTO);
        return developerRepository.update(developer);
    }

    public void deletarDeveloper(Long id) {

        Developer developer = developerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Developer não encontrada para exclusão"));

        boolean possuiJogos = gameRepository.existsByDeveloper(developer);
        if (possuiJogos) {
            throw new IllegalStateException("Não é possível deletar a developer. Existem jogos associados a ela.");
        }

        developerRepository.delete(developer);
    }
}
