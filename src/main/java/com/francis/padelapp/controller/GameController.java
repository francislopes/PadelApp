package com.francis.padelapp.controller;

import com.francis.padelapp.model.Game;
import com.francis.padelapp.model.request.GameRequest;
import com.francis.padelapp.service.GameService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "Game Controller", tags = "Game Controller")
@RestController
@RequestMapping(value = "/game", produces = MediaType.APPLICATION_JSON_VALUE)
public class GameController {

    @Autowired
    private GameService gameService;

    @ApiOperation("Find all games.")
    @GetMapping
    public List<Game> findAll() {
        return gameService.findAll();
    }

    @ApiOperation("Find a game by Id.")
    @GetMapping("/{id}")
    public Game findById(@PathVariable Long id) {
        return gameService.findByID(id);
    }

    @ApiOperation("Create a game.")
    @PostMapping
    public Game create(@RequestBody @Valid GameRequest request) {
        return gameService.create(request);
    }

    @ApiOperation("Update a game.")
    @PatchMapping("/{id}")
    public Game update(@PathVariable Long id, @RequestBody GameRequest request) {
        return gameService.update(id, request);
    }

    @ApiOperation("Replace a game.")
    @PutMapping("/{id}")
    public Game replace(@PathVariable Long id, @RequestBody GameRequest request) {
        return gameService.replace(id, request);
    }

    @ApiOperation("Delete a game.")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        gameService.delete(id);
    }





}
