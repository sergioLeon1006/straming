package com.streaming.demo.service;

import com.streaming.demo.model.Film;
import com.streaming.demo.model.Tipo;
import com.streaming.demo.repository.FilmRepository;
import com.streaming.demo.service.impl.FilmServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class FilmServiceTest {

    @Mock
    private FilmRepository filmRepository;
    @InjectMocks
    private FilmServiceImpl filmServiceImpl;

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

    @DisplayName("Save film")
    @Test
    void testGuardado(){
        //given
        given(filmRepository.findByNombre("Resident Evil"))
                .willReturn(Optional.empty());
        given(filmRepository.save(film))
                .willReturn(film);
        //when
        Film filmGuardado = filmServiceImpl.saveFilm(film);
        //then
        assertThat(filmGuardado).isNotNull();
    }

    @DisplayName("Show test")
    @Test
    void testListarFilms(){
        //given
        Film film1 =Film.builder()
                .id(10L)
                .nombre("never alone")
                .puntajePromedio(5)
                .visualizaciones(500)
                .tipo(Tipo.SERIE)
                .build();
        given(filmRepository.findAll()).willReturn(List.of(film,film1));
        //when
        List<Film> films =filmServiceImpl.getAllFilm();
        //Trow
        assertThat(films).isNotNull();
        assertThat(films.size()).isEqualTo(2);
    }

}
