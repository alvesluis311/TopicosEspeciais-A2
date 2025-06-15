package br.unitins.topicos.projetoa2.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;

import java.util.List;
import java.util.Optional;

import br.unitins.topicos.projetoa2.dto.DeveloperDTO;
import br.unitins.topicos.projetoa2.model.Developer;
import br.unitins.topicos.projetoa2.service.DeveloperService;

@Controller("/developers")
public class DeveloperController {

    private final DeveloperService developerService;

    public DeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @Get
    public List<Developer> listarDevelopers() {
        return developerService.listarDevelopers();
    }

    @Get("/{id}")
    public HttpResponse<Developer> buscarPorId(@PathVariable Long id) {
        Optional<Developer> developer = developerService.buscarPorId(id);
        return developer.map(HttpResponse::ok)
                        .orElse(HttpResponse.notFound());
    }

    @Post
    public Developer salvarDeveloper(@Body DeveloperDTO developerDTO) {
        return developerService.salvarDeveloper(developerDTO);
    }

    @Put("/{id}")
    public Developer atualizarDeveloper(@PathVariable Long id, @Body DeveloperDTO developerDTO) {
        return developerService.atualizarDeveloper(id, developerDTO);
    }

    @Delete("/{id}")
    public HttpResponse<Void> deletarDeveloper(@PathVariable Long id) {
        developerService.deletarDeveloper(id);
        return HttpResponse.noContent();
    }
}
