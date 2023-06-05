package com.streaming.demo.service.impl;

import com.streaming.demo.exception.ResourceNotFoundException;
import com.streaming.demo.model.Film;
import com.streaming.demo.model.Genero;
import com.streaming.demo.model.Tipo;
import com.streaming.demo.repository.FilmRepository;
import com.streaming.demo.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    FilmRepository filmRepository;


    @Override
    public Film saveFilm(Film film) {
        Optional<Film> filmGuardado =filmRepository.findByNombre(film.getNombre());
        if (filmGuardado.isPresent()){
            throw new ResourceNotFoundException("La serie o pelicula ya se encuentra guardada: " + film.getNombre());
        }
        return filmRepository.save(film);
    }

    @Override
    public List<Film> getAllFilm() {
        return filmRepository.findAll();
    }

    @Override
    public Optional<Film> findByNombre(String nombre) {
        return filmRepository.findByNombre(nombre);
    }

    @Override
    public Optional<List<Film>> findByTipo(Tipo tipo) {
        Optional<List<Film>> films =  filmRepository.findByTipo(tipo);
        return films;
    }

    @Override
    public Optional<List<Film>> findByGenero(Genero genero) {
        Optional<List<Film>> films =  filmRepository.findByGenero(genero);
        return films;
    }

    @Override
    public List<Film> findAllByOrderByNombreAsc() {
        return filmRepository.findAllByOrderByNombreAsc();
    }
}
