package br.unitins.topicos.projetoa2.service;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import br.unitins.topicos.projetoa2.dto.GameDTO;
import br.unitins.topicos.projetoa2.model.Developer;
import br.unitins.topicos.projetoa2.model.Game;
import br.unitins.topicos.projetoa2.repository.DeveloperRepository;
import br.unitins.topicos.projetoa2.repository.GameRepository;

@Singleton
public class GameService {

    @Inject
    private GameRepository gameRepository;

    @Inject
    private DeveloperRepository developerRepository;

    public List<Game> listarGames() {
        return (List<Game>) gameRepository.findAll();
    }

    public Optional<Game> buscarPorId(Long id) {
        return gameRepository.findById(id);
    }

    public Game salvarGame(GameDTO gameDTO) {
        Developer developer = developerRepository.findById(gameDTO.idDeveloper())
                .orElseThrow(() -> new EntityNotFoundException("Developer não encontrado"));

        Game game = new Game(gameDTO, developer);
        return gameRepository.save(game);
    }

    public Game atualizarGame(Long id, GameDTO gameDTO) {
        Developer developer = developerRepository.findById(gameDTO.idDeveloper())
                .orElseThrow(() -> new EntityNotFoundException("Developer não encontrado"));

        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Game não encontrado"));

        game.setDados(gameDTO, developer);
        return gameRepository.update(game);
    }

    public void deletarGame(Long id) {
        gameRepository.deleteById(id);
    }
}

