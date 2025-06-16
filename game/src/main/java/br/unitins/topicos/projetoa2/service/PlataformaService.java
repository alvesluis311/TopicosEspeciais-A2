package br.unitins.topicos.projetoa2.service;

import java.util.List;
import java.util.Optional;

import br.unitins.topicos.projetoa2.dto.PlataformaDTO;
import br.unitins.topicos.projetoa2.model.Plataforma;
import br.unitins.topicos.projetoa2.repository.PlataformaRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityNotFoundException;

@Singleton
public class PlataformaService {
 
    @Inject
    private PlataformaRepository plataformaRepository;

    public List<Plataforma> listarPlataformas() {
        return plataformaRepository.findAll();
    }

    public Optional<Plataforma> buscarPorId(Long id) {
        return plataformaRepository.findById(id);
    }

    public Plataforma salvarPlataforma(PlataformaDTO plataformaDTO) {
        Plataforma plataforma = new Plataforma(plataformaDTO);
        return plataformaRepository.save(plataforma);
    }

    public Plataforma atualizarPlataforma(Long id, PlataformaDTO plataformaDTO) {
        Plataforma plataforma = plataformaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Plataforma não encontrado"));
        plataforma.setDados(plataformaDTO);
        return plataformaRepository.update(plataforma);
    }

    public void deletarPlataforma(Long id) {
        plataformaRepository.deleteById(id);
    }
}
