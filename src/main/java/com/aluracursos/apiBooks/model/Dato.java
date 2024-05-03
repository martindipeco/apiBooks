package com.aluracursos.apiBooks.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record Dato(
        @JsonAlias("results") List<DatosLibro> listaDeLibros
) {
}
