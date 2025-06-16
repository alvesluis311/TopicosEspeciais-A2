package br.unitins.topicos.projetoa2.service;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import br.unitins.topicos.projetoa2.dto.GameDTO;
import br.unitins.topicos.projetoa2.model.Developer;
import br.unitins.topicos.projetoa2.model.Game;
import br.unitins.topicos.projetoa2.model.Plataforma;
import br.unitins.topicos.projetoa2.repository.DeveloperRepository;
import br.unitins.topicos.projetoa2.repository.GameRepository;
import br.unitins.topicos.projetoa2.repository.PlataformaRepository;

@Singleton
public class GameService {

    @Inject
    private GameRepository gameRepository;

    @Inject
    private DeveloperRepository developerRepository;
    
    @Inject
    private PlataformaRepository plataformaRepository;

    public List<Game> listarGames() {
        return (List<Game>) gameRepository.findAll();
    }

    public Optional<Game> buscarPorId(Long id) {
        return gameRepository.findById(id);
    }

    public Game salvarGame(GameDTO gameDTO) {
        Developer developer = developerRepository.findById(gameDTO.idDeveloper())
                .orElseThrow(() -> new EntityNotFoundException("Developer não encontrado"));
        
        Plataforma plataforma = plataformaRepository.findById(gameDTO.idPlataforma())
                .orElseThrow(() -> new EntityNotFoundException("Plataforma não encontrado"));

        Game game = new Game(gameDTO, developer, plataforma);
        return gameRepository.save(game);
    }

    public Game atualizarGame(Long id, GameDTO gameDTO) {
        Developer developer = developerRepository.findById(gameDTO.idDeveloper())
                .orElseThrow(() -> new EntityNotFoundException("Developer não encontrado"));

        Plataforma plataforma = plataformaRepository.findById(gameDTO.idPlataforma())
                .orElseThrow(() -> new EntityNotFoundException("Plataforma não encontrado"));

        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Game não encontrado"));

        game.setDados(gameDTO, developer, plataforma);
        return gameRepository.update(game);
    }

    public void deletarGame(Long id) {
        gameRepository.deleteById(id);
    }
}

