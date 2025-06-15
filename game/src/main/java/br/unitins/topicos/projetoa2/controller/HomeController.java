package br.unitins.topicos.projetoa2.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.uri.UriBuilder;

@Controller
public class HomeController {
    
    @Get("/")
    public HttpResponse<?> redirecionarParaSwagger() {
        return HttpResponse.redirect(UriBuilder.of("/swagger-ui").build());
    }
}
