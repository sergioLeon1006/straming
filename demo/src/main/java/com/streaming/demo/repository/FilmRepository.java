package com.streaming.demo.repository;

import com.streaming.demo.model.Film;
import com.streaming.demo.model.Genero;
import com.streaming.demo.model.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface FilmRepository extends JpaRepository<Film,Long> {

    Optional<Film> findByNombre(String nombre);
    Optional<List<Film>> findByTipo(Tipo tipo);
    Optional<List<Film>> findByGenero(Genero genero);
    List<Film> findAllByOrderByNombreAsc();

}
