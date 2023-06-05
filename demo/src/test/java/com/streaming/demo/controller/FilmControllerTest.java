package com.streaming.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.streaming.demo.model.Film;
import com.streaming.demo.model.Tipo;
import com.streaming.demo.service.FilmService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class FilmControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FilmService filmService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testguardarFilm() throws Exception {
        //given
        Film film1 =Film.builder()
                .id(10L)
                .nombre("never alone")
                .puntajePromedio(5)
                .visualizaciones(500)
                .tipo(Tipo.SERIE)
                .build();
        given(filmService.saveFilm(any(Film.class)))
                .willAnswer((invocation)->invocation.getArgument(0));
        //when
        ResultActions response = mockMvc.perform(post("/film")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(film1)));
        //then
        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre",is(film1.getNombre())))
                .andExpect(jsonPath("$.tipo",is(film1.getTipo())))
                .andExpect(jsonPath("$.puntaje",is(film1.getPuntajePromedio())));
    }

    @Test
    void getFilmByName() throws Exception {
        //given
        String name = "never alone";
        Film film1 = Film.builder()
                .id(10L)
                .nombre("never alone")
                .puntajePromedio(5)
                .visualizaciones(500)
                .tipo(Tipo.SERIE)
                .build();
        //when
        ResultActions resultActions = mockMvc.perform(get("film/name/{nombre}",name));
        //then
        resultActions.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.nombre",is(film1.getNombre())))
                .andExpect(jsonPath("$.tipo",is(film1.getTipo())))
                .andExpect(jsonPath("$.puntaje",is(film1.getPuntajePromedio())));
    }

    @Test
    void getFilmByType() throws Exception {
        //given
        Tipo tipo =  Tipo.SERIE;
        Film film1 = Film.builder()
                .id(10L)
                .nombre("never alone")
                .puntajePromedio(5)
                .visualizaciones(500)
                .tipo(Tipo.SERIE)
                .build();
        //when
        ResultActions resultActions = mockMvc.perform(get("film/type/{tipo}",tipo));
        //then
        resultActions.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.nombre",is(film1.getNombre())))
                .andExpect(jsonPath("$.tipo",is(film1.getTipo())))
                .andExpect(jsonPath("$.puntaje",is(film1.getPuntajePromedio())));
    }
}
