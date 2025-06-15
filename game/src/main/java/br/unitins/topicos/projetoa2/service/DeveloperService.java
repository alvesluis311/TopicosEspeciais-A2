package br.unitins.topicos.projetoa2.service;

import jakarta.inject.Singleton;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import br.unitins.topicos.projetoa2.dto.DeveloperDTO;
import br.unitins.topicos.projetoa2.model.Developer;
import br.unitins.topicos.projetoa2.repository.DeveloperRepository;

@Singleton
public class DeveloperService {

    private final DeveloperRepository developerRepository;

    public DeveloperService(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    public List<Developer> listarDevelopers() {
        return developerRepository.findAll();
    }

    public Optional<Developer> buscarPorId(Long id) {
        return developerRepository.findById(id);
    }

    public Developer salvarDeveloper(DeveloperDTO developerDTO) {
        Developer developer = new Developer(developerDTO);
        return developerRepository.save(developer);
    }

    public Developer atualizarDeveloper(Long id, DeveloperDTO developerDTO) {
        Developer developer = developerRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Developer não encontrado"));
        developer.setDados(developerDTO);
        return developerRepository.update(developer);
    }

    public void deletarDeveloper(Long id) {
        developerRepository.deleteById(id);
    }
}
