package br.unitins.topicos.projetoa2.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import br.unitins.topicos.projetoa2.dto.DeveloperDTO;
import br.unitins.topicos.projetoa2.model.Developer;
import br.unitins.topicos.projetoa2.service.DeveloperService;

@Controller("/developers")
public class DeveloperController {

    @Inject
    private DeveloperService developerService;

    @Get
    public HttpResponse<List<Developer>> listarDevelopers() {
        return HttpResponse.ok(developerService.listarDevelopers());
    }

    @Get("/{id}")
    public HttpResponse<?> buscarPorId(@PathVariable Long id) {

        try {
            Optional<Developer> developer = developerService.buscarPorId(id);
            if (developer.isPresent()) {
                return HttpResponse.ok(developer.get());
            } else {
                return HttpResponse.notFound("Developer não encontrada");
            }
        } catch (Exception e) {
            return HttpResponse.badRequest("Erro ao buscar developer: " + e.getMessage());
        }
    }

    @Post
    public HttpResponse<?> salvarDeveloper(@Body @Valid DeveloperDTO developerDTO) {

        try {
            Developer developer = developerService.salvarDeveloper(developerDTO);
            return HttpResponse.created(developer);
        } catch (Exception e) {
            return HttpResponse.badRequest("Erro ao salvar developer: " + e.getMessage());
        }
    }

    @Put("/{id}")
    public HttpResponse<?> atualizarDeveloper(@PathVariable Long id, @Body @Valid DeveloperDTO developerDTO) {

        try {
            Developer developer = developerService.atualizarDeveloper(id, developerDTO);
            return HttpResponse.ok(developer);
        } catch (EntityNotFoundException e) {
            return HttpResponse.notFound(e.getMessage());
        } catch (Exception e) {
            return HttpResponse.badRequest("Erro ao atualizar developer: " + e.getMessage());
        }
    }

    @Delete("/{id}")
    public HttpResponse<?> deletarDeveloper(@PathVariable Long id) {

        try {
            developerService.deletarDeveloper(id);
            return HttpResponse.noContent();
        } catch (EntityNotFoundException e) {
            return HttpResponse.notFound(e.getMessage());
        } catch (Exception e) {
            return HttpResponse.badRequest("Erro ao deletar developer: " + e.getMessage());
        }
    }
}
