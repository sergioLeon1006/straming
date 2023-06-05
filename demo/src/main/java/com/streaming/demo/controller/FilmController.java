package com.streaming.demo.controller;

import com.streaming.demo.model.Film;
import com.streaming.demo.model.Tipo;
import com.streaming.demo.service.FilmService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/film")
public class FilmController {

    @Autowired
    private FilmService filmService;
    @ApiOperation("search all films")
    @ApiResponses({
            @ApiResponse(code =200, message = "success"),
            @ApiResponse(code = 404, message = "Films not found")
    })
    @GetMapping("/all")
    public List<Film> getAll(){
        return filmService.getAllFilm();
    }

    @ApiOperation("search film by name")
    @ApiResponses({
            @ApiResponse(code =200, message = "success"),
            @ApiResponse(code = 404, message = "Film not found")
    })
    @GetMapping("/name/{nombre}")
    public ResponseEntity<Film> getFilmByNombre(@ApiParam(value = "name",required = true,example = "silent hill")
                                                    @PathVariable("name") String nombre){
        return filmService.findByNombre(nombre)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }
    @ApiOperation("search film by type")
    @ApiResponses({
            @ApiResponse(code =200, message = "success"),
            @ApiResponse(code = 404, message = "Film not found")
    })
    @GetMapping("/type/{tipo}")
    public Optional<List<Film>> getFilmByTipo(@ApiParam(value = "type",required = true,example = "PELICULA")
                                                  @PathVariable("tipo")Tipo tipo){
        return filmService.findByTipo(tipo);
    }


}
