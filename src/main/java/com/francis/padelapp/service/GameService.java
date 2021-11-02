package com.francis.padelapp.service;

import com.francis.padelapp.model.Game;
import com.francis.padelapp.model.request.GameRequest;
import com.francis.padelapp.repository.GameRepository;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
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

    public List<Game> uploadFile(MultipartFile file) {
        checkExtension("xlsx", file);

        try {
            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0); // number of tabs within the file, the first tab was selected

            for (Row row : sheet) {
                if (row.getRowNum() != 0) { // skip header
                    if (row.getCell(0) != null && row.getCell(0).getCellType() == CellType.STRING) {

                        // creating and saving an Event
                        var game = new Game();
                        game.setDate(row.getCell(0).getLocalDateTimeCellValue());
                        game.setTime(row.getCell(0).getLocalDateTimeCellValue());
                        game.setAddress(row.getCell(0).getStringCellValue());
                        game.setComments(row.getCell(0).getStringCellValue());
                        gameRepository.saveAndFlush(game);
                    }
                }
            }

        } catch (IOException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error has occurred");
        }

        return findAll();
    }

    private static void checkExtension(String allowedExtension, MultipartFile file) {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (extension == null || !allowedExtension.toLowerCase().equals(extension.toLowerCase()))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("The file has an extension that is not allowed, please try again using an { %s } file", allowedExtension));
    }

}
