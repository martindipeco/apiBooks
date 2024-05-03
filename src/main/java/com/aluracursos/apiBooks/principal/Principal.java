package com.aluracursos.apiBooks.principal;

import com.aluracursos.apiBooks.model.Dato;
import com.aluracursos.apiBooks.model.DatosLibro;
import com.aluracursos.apiBooks.service.ApiKey;
import com.aluracursos.apiBooks.service.ConsumoApi;
import com.aluracursos.apiBooks.service.ConvierteDatos;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    //se pueden buscar más APIS acá : https://publicapis.dev/category/books
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    //private final String API_KEY = ApiKey.getApiKey();
    private Scanner scanner = new Scanner(System.in);
    private ConsumoApi consumoAPI = new ConsumoApi();
    private ConvierteDatos conversor = new ConvierteDatos();



    public void muestraMenu()
    {
        System.out.println("Bienvenidos a Alura + Gutendex Libros");
        System.out.println("Ingrese su búsqueda");
        var busqueda = scanner.nextLine();
        //String direccion = URL_BASE + busqueda.replace(" ", "+") + "&apikey=" + API_KEY;
        String direccion = URL_BASE + busqueda.replace(" ", "%20");
        var json = consumoAPI.obtenerDatos(direccion);
        System.out.println(json);
        var datos = conversor.obtenerDatos(json, Dato.class);
        System.out.println(datos);

        //top 5 de descargas
        System.out.println("Top 5 de descargas: ");
        datos.listaDeLibros().stream()
                .sorted(Comparator.comparing(DatosLibro::descargas).reversed())
                .limit(5)
                //.map(libro -> libro.titulo().toUpperCase())
                .forEach(System.out::println);

        //método para saber si existe al menos una referencia de determinado título
        System.out.println("Ingrese su búsqueda para saber si existe al menos una referencia");
        var busquedaAny = scanner.nextLine();

        Optional<DatosLibro> libroBuscado = datos.listaDeLibros().stream()
                .filter(libro -> libro.titulo().toUpperCase().contains(busquedaAny.toUpperCase()))
                .findAny();
        if(libroBuscado.isPresent())
        {
            System.out.println("Encontrado");
            System.out.println(libroBuscado.get());
        }
        else {
            System.out.println("No se encontró ninguna referencia");
        }

        //hacemos estadísticas
        IntSummaryStatistics est = datos.listaDeLibros().stream()
                .filter(d -> d.descargas()>0)
                .collect(Collectors.summarizingInt(DatosLibro::descargas));

        System.out.println("Promedio de descargas: " + est.getAverage());
        System.out.println("Cantidad de casos: " + est.getCount());
        System.out.println("Mínima de descargas: " + est.getMin());
        System.out.println("Máxima de descargas: " + est.getMax());
    }
}
