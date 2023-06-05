package com.streaming.demo.repository;

import com.streaming.demo.model.Film;
import com.streaming.demo.model.Tipo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class FilmRespositoryTest {

    @Autowired
    private FilmRepository filmRepository;
    private Film film;

    @BeforeEach
    void setup(){
        film = Film.builder()
                .id(10L)
                .nombre("Resident Evil")
                .tipo(Tipo.PELICULA)
                .puntajePromedio(4.5)
                .visualizaciones(100)
                .build();
    }

    @DisplayName("Test save film")
    @Test
    void tesGuardado(){
        //given
       Film film1 = Film.builder()
                .id(10L)
                .nombre("resident evil")
                .puntajePromedio(4)
                .visualizaciones(400)
                .tipo(Tipo.PELICULA)
                .build();
        //when
        Film filmGuardado =filmRepository.save(film1);
        //then
        assertThat(filmGuardado).isNotNull();
        assertThat(filmGuardado.getId()).isGreaterThan(0);
    }

    @DisplayName("Test list Films")
    @Test
    void listarFilms(){
        //given
        Film film1 = Film.builder()
                .id(10L)
                .nombre("never alone")
                .puntajePromedio(5)
                .visualizaciones(500)
                .tipo(Tipo.SERIE)
                .build();
        filmRepository.save(film1);
        filmRepository.save(film);
        //when
        List<Film> listafilms = filmRepository.findAll();
        //then
        assertThat(listafilms).isNotNull();
        assertThat(listafilms.size()).isEqualTo(2);
    }

    @DisplayName("Get film by name")
    @Test
    void getFilmByName(){
        //given
        filmRepository.save(film);
        //when
        Film filmBd =  filmRepository.findByNombre(film.getNombre()).get();
        //then
        assertThat(filmBd).isNotNull();
    }
    @DisplayName("Get film by type")
    @Test
    void getFilmByType(){
        //given
        filmRepository.save(film);
        //when
        List<Film> filmBd =  filmRepository.findByTipo(film.getTipo()).get();
        //then
        assertThat(filmBd).isNotNull();
    }
}
