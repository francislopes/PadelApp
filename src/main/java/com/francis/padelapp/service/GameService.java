package com.francis.padelapp.service;

import com.francis.padelapp.model.Game;
import com.francis.padelapp.model.request.GameRequest;
import com.francis.padelapp.repository.GameRepository;
import com.itextpdf.text.DocumentException;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import static com.francis.padelapp.util.BuildCSV.buildCSV;
import static com.francis.padelapp.util.BuildPDF.buildPDF;
import static com.francis.padelapp.util.BuildXLSX.buildXLSX;

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
        String loggedUser = SecurityContextHolder.getContext().getAuthentication().getName();

        var game = new Game();
        game.setDate(request.getDate());
        game.setTime(request.getTime());
        game.setAddress(request.getAddress());
        game.setComments(request.getComments());
        game.setPlayerOne(loggedUser);
        game.setPlayerTwo(request.getPlayerTwo());
        game.setPlayerThree(request.getPlayerThree());
        game.setPlayerFour(request.getPlayerFour());
        return gameRepository.saveAndFlush(game);

    }

    public Game update(Long id, GameRequest request) {
        var game = findByID(id);
        game.setDate(request.getDate());
        game.setTime(request.getTime());
        game.setAddress(request.getAddress());
        game.setComments(request.getComments());
        game.setPlayerOne(request.getPlayerOne());
        game.setPlayerTwo(request.getPlayerTwo());
        game.setPlayerThree(request.getPlayerThree());
        game.setPlayerFour(request.getPlayerFour());
        return gameRepository.saveAndFlush(game);

    }

    public Game replace(Long id, GameRequest request) {
        var game = findByID(id);
        game.setDate(request.getDate());
        game.setTime(request.getTime());
        game.setAddress(request.getAddress());
        game.setComments(request.getComments());
        game.setPlayerOne(request.getPlayerOne());
        game.setPlayerTwo(request.getPlayerTwo());
        game.setPlayerThree(request.getPlayerThree());
        game.setPlayerFour(request.getPlayerFour());
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
                        game.setDate(LocalDate.from(row.getCell(0).getLocalDateTimeCellValue()));
                        game.setTime(LocalTime.from(row.getCell(0).getLocalDateTimeCellValue()));
                        game.setAddress(row.getCell(0).getStringCellValue());
                        game.setComments(row.getCell(0).getStringCellValue());
                        game.setPlayerOne(row.getCell(0).getStringCellValue());
                        game.setPlayerTwo(row.getCell(0).getStringCellValue());
                        game.setPlayerThree(row.getCell(0).getStringCellValue());
                        game.setPlayerFour(row.getCell(0).getStringCellValue());
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

    public void pdf(HttpServletResponse response) throws IOException, DocumentException {
        var report = findAll();
        var currentDateTime = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());

        response.setHeader("Content-Disposition", "attachment; filename=report_" + currentDateTime + ".pdf");
        response.setContentType("application/pdf");

        buildPDF(response, report);
    }

    public void csv(HttpServletResponse response) throws IOException {
        var report = findAll();
        var currentDateTime = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());

        response.setHeader("Content-Disposition", "attachment; filename=report_" + currentDateTime + ".csv");
        response.setContentType("text/csv");

        buildCSV(response, report);
    }

    public void xlsx(HttpServletResponse response) throws IOException {
        var report = findAll();
        var currentDateTime = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());

        response.setHeader("Content-Disposition", "attachment; filename=report_" + currentDateTime + ".xlsx");
        response.setContentType("application/vnd.ms-excel");

        buildXLSX(response, report);
    }

}
