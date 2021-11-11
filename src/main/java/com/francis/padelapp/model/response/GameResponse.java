package com.francis.padelapp.model.response;

import com.francis.padelapp.enums.GameStatus;
import lombok.Data;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class GameResponse {

    private Long id;
    private LocalDate date;
    private String address;
    private GameStatus status;
    private String comments;
    private String playerOne;
    private String playerTwo;
    private String playerThree;
    private String playerFour;

}
