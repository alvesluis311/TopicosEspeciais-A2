package br.unitins.topicos.projetoa2.controller;

import java.util.List;
import java.util.Optional;

import br.unitins.topicos.projetoa2.dto.PlataformaDTO;
import br.unitins.topicos.projetoa2.model.Plataforma;
import br.unitins.topicos.projetoa2.service.PlataformaService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import jakarta.inject.Inject;

@Controller("/plataformas")
public class PlataformaController {
    
    @Inject
    private PlataformaService plataformaService;

    @Get
    public List<Plataforma> listarPlataforma() {
        return plataformaService.listarPlataformas();
    }

    @Get("/{id}")
    public HttpResponse<Plataforma> buscarPorId(@PathVariable Long id) {
        Optional<Plataforma> plataforma = plataformaService.buscarPorId(id);
        return plataforma.map(HttpResponse::ok)
                        .orElse(HttpResponse.notFound());
    }

    @Post
    public Plataforma salvarPlataforma(@Body PlataformaDTO developerDTO) {
        return plataformaService.salvarPlataforma(developerDTO);
    }

    @Put("/{id}")
    public Plataforma atualizarPlataforma(@PathVariable Long id, @Body PlataformaDTO developerDTO) {
        return plataformaService.atualizarPlataforma(id, developerDTO);
    }

    @Delete("/{id}")
    public HttpResponse<Void> deletarPlataforma(@PathVariable Long id) {
        plataformaService.deletarPlataforma(id);
        return HttpResponse.noContent();
    }
}
