package br.unitins.topicos.projetoa2.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;

import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import br.unitins.topicos.projetoa2.dto.GameDTO;
import br.unitins.topicos.projetoa2.model.Game;
import br.unitins.topicos.projetoa2.service.GameService;

@Controller("/games")
public class GameController {

    @Inject
    private GameService gameService;

    @Get
    public HttpResponse<List<Game>> listarGames() {
        return HttpResponse.ok(gameService.listarGames());
    }

    @Get("/{id}")
    public HttpResponse<?> buscarPorId(@PathVariable Long id) {

        try {
            Optional<Game> game = gameService.buscarPorId(id);
            if (game.isPresent()) {
                return HttpResponse.ok(game.get());
            } else {
                return HttpResponse.notFound("Jogo não encontrado");
            }
        } catch (Exception e) {
            return HttpResponse.badRequest("Erro ao buscar game: " + e.getMessage());
        }
    }

    @Post
    public HttpResponse<?> salvarGame(@Body @Valid GameDTO gameDTO) {

        try {
            Game game = gameService.salvarGame(gameDTO);
            return HttpResponse.created(game);
        } catch (Exception e) {
            return HttpResponse.badRequest("Erro ao salvar jogo: " + e.getMessage());
        }
    }

    @Put("/{id}")
    public HttpResponse<?> atualizarGame(@PathVariable Long id, @Body @Valid GameDTO gameDTO) {

        try {
            Game game = gameService.atualizarGame(id, gameDTO);
            return HttpResponse.ok(game);
        } catch (EntityNotFoundException e) {
            return HttpResponse.notFound(e.getMessage());
        } catch (Exception e) {
            return HttpResponse.badRequest("Erro ao atualizar jogo: " + e.getMessage());
        }
    }

    @Delete("/{id}")
    public HttpResponse<?> deletarGame(@PathVariable Long id) {

        try {
            gameService.deletarGame(id);
            return HttpResponse.noContent();
        } catch (EntityNotFoundException e) {
            return HttpResponse.notFound(e.getMessage());
        } catch (Exception e) {
            return HttpResponse.badRequest("Erro ao deletar jogo: " + e.getMessage());
        }
    }
}