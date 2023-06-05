package com.streaming.demo.service;

import com.streaming.demo.model.Film;
import com.streaming.demo.model.Genero;
import com.streaming.demo.model.Tipo;

import java.util.List;
import java.util.Optional;

public interface FilmService {

    Film saveFilm(Film film);
    List<Film>getAllFilm();
    Optional<Film> findByNombre(String nombre);
    Optional<List<Film>> findByTipo(Tipo tipo);
    Optional<List<Film>> findByGenero(Genero genero);
    List<Film> findAllByOrderByNombreAsc();

}
