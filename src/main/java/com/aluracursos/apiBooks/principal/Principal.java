package com.aluracursos.apiBooks.principal;

import com.aluracursos.apiBooks.model.DatosLibro;
import com.aluracursos.apiBooks.service.ApiKey;
import com.aluracursos.apiBooks.service.ConsumoApi;
import com.aluracursos.apiBooks.service.ConvierteDatos;

import java.util.Scanner;

public class Principal {
    //se pueden buscar más APIS acá : https://publicapis.dev/category/books
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    //private final String API_KEY = ApiKey.getApiKey();
    private Scanner scanner = new Scanner(System.in);
    private ConsumoApi consumoAPI = new ConsumoApi();
    private ConvierteDatos conversor = new ConvierteDatos();



    public void muestraMenu()
    {
        //TODO: preparar la URL_BASE
        System.out.println("Bienvenidos a Alura + Gutendex Libros");
        System.out.println("Ingrese su búsqueda");
        var busqueda = scanner.nextLine();
        //String direccion = URL_BASE + busqueda.replace(" ", "+") + "&apikey=" + API_KEY;
        String direccion = URL_BASE + busqueda.replace(" ", "%20");
        var json = consumoAPI.obtenerDatos(direccion);
        System.out.println(json);
        var datos = conversor.obtenerDatos(json, DatosLibro.class);

        //Como lidiar con resultado que es un array
        //String jsonString = peli.buscaPeliculaPorTitulo(busquedaTitulo);

        //to access values from the JSON -> parse String to Json
        //JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();

        //JsonArray resultsArray = jsonObject.getAsJsonArray("results");
        //int numeroDeResultados = jsonObject.get("count").getAsInt();


    }
}
