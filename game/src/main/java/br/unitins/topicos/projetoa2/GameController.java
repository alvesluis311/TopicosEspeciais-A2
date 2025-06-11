package br.unitins.topicos.projetoa2;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;

import jakarta.inject.Inject;
import java.util.List;
import java.util.Optional;

@Controller("/games")
public class GameController {

    @Inject
    private GameService gameService;

    @Get
    public List<Game> listarGames() {
        return gameService.listarGames();
    }

    @Get("/{id}")
    public HttpResponse<Game> buscarPorId(@PathVariable Long id) {
        Optional<Game> game = gameService.buscarPorId(id);
        return game.map(HttpResponse::ok).orElse(HttpResponse.notFound());
    }

    @Post
    public Game salvarGame(@Body GameDTO gameDTO) {
        return gameService.salvarGame(gameDTO);
    }

    @Put("/{id}")
    public Game atualizarGame(@PathVariable Long id, @Body GameDTO gameDTO) {
        return gameService.atualizarGame(id, gameDTO);
    }

    @Delete("/{id}")
    public HttpResponse<Void> deletarGame(@PathVariable Long id) {
        gameService.deletarGame(id);
        return HttpResponse.noContent();
    }
}