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
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Controller("/plataformas")
public class PlataformaController {

    @Inject
    private PlataformaService plataformaService;

    @Get
    public HttpResponse<List<Plataforma>> listarPlataforma() {
        return HttpResponse.ok(plataformaService.listarPlataformas());
    }

    @Get("/{id}")
    public HttpResponse<?> buscarPorId(@PathVariable Long id) {
        try {
            Optional<Plataforma> plataformaOpt = plataformaService.buscarPorId(id);
            if (plataformaOpt.isPresent()) {
                return HttpResponse.ok(plataformaOpt.get());
            } else {
                return HttpResponse.notFound("Plataforma não encontrada");
            }
        } catch (Exception e) {
            return HttpResponse.badRequest("Erro ao buscar plataforma: " + e.getMessage());
        }
    }

    @Post
    public HttpResponse<?> salvarPlataforma(@Body @Valid PlataformaDTO plataformaDTO) {
        try {
            Plataforma plataforma = plataformaService.salvarPlataforma(plataformaDTO);
            return HttpResponse.created(plataforma);
        } catch (Exception e) {
            return HttpResponse.badRequest("Erro ao salvar plataforma: " + e.getMessage());
        }
    }

    @Put("/{id}")
    public HttpResponse<?> atualizarPlataforma(@PathVariable Long id, @Body @Valid PlataformaDTO plataformaDTO) {
        try {
            Plataforma plataforma = plataformaService.atualizarPlataforma(id, plataformaDTO);
            return HttpResponse.ok(plataforma);
        } catch (EntityNotFoundException e) {
            return HttpResponse.notFound(e.getMessage());
        } catch (Exception e) {
            return HttpResponse.badRequest("Erro ao atualizar plataforma: " + e.getMessage());
        }
    }

    @Delete("/{id}")
    public HttpResponse<?> deletarPlataforma(@PathVariable Long id) {
        try {
            plataformaService.deletarPlataforma(id);
            return HttpResponse.noContent();
        } catch (EntityNotFoundException e) {
            return HttpResponse.notFound(e.getMessage());
        } catch (Exception e) {
            return HttpResponse.badRequest("Erro ao deletar plataforma: " + e.getMessage());
        }
    }
}
