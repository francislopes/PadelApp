package com.francis.padelapp.service;

import com.francis.padelapp.model.Game;
import com.francis.padelapp.model.request.GameRequest;
import com.francis.padelapp.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    public Game findByID(Long id) {
        return gameRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found."));
    }

    public Game create(GameRequest request) {
        var game = new Game();
        game.setDate(request.getDate());
        game.setTime(request.getTime());
        game.setAddress(request.getAddress());
        game.setComments(request.getComments());
        return gameRepository.saveAndFlush(game);

    }

    public Game update(Long id, GameRequest request) {
        var game = findByID(id);
        game.setDate(request.getDate());
        game.setTime(request.getTime());
        game.setAddress(request.getAddress());
        game.setComments(request.getComments());
        return gameRepository.saveAndFlush(game);

    }

    public Game replace(Long id, GameRequest request) {
        var game = findByID(id);
        game.setDate(request.getDate());
        game.setTime(request.getTime());
        game.setAddress(request.getAddress());
        game.setComments(request.getComments());
        return gameRepository.saveAndFlush(game);

    }

    public void delete(Long id) {
        gameRepository.deleteById(id);
    }
}
