package com.aluracursos.apiBooks.service;

public interface IConvierteDatos {
    <T> T obtenerDatos (String json, Class<T> clase);
}
